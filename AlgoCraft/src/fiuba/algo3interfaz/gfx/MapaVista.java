package fiuba.algo3interfaz.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.terreno.Terreno;
import fiuba.algo3.terreno.TipoTerreno;

public class MapaVista {
	private CeldaVista[][] mapaVista;
	private int ancho;
	private int alto;
	private int anchoVentana;
	private int altoVentana;
	
	private int filSeleccionada = 0;
	private int colSeleccionada = 0;
	private Mapa datosMapa;
	private Camara camara;
	
	public MapaVista(Mapa mapa, int anchoVentana, int altoVentana){
		
		this.anchoVentana = anchoVentana;
		this.altoVentana = altoVentana;
		
		this.ancho = mapa.ancho();
		this.alto = mapa.alto();
		this.datosMapa = mapa;
		this.mapaVista = new CeldaVista[ancho][alto];
		this.camara = new Camara(0, 0, 0, 0, anchoVentana, altoVentana);
		
		Random miRNG = new Random();
		for (int j = 0; j < this.alto; j++)
			for (int i = 0; i < this.ancho; i++)
				mapaVista[i][j] = new CeldaVista(miRNG.nextInt());
		
	}
		
	public BufferedImage spriteTerreno(int x, int y){
		int randomIndex = (mapaVista[x][y].getIndex()) % SpriteSheet.CANTIDAD_SPRITES;
		if (datosMapa.getTerreno(new Posicion(x, y)) == Terreno.TIERRA)
			return SpriteSheet.spritesTierra[randomIndex];
		
		int indexEspacio = getIndexHibrido(x, y);

		if (indexEspacio == -1)
			return SpriteSheet.spritesEspacio[randomIndex];
		
		return SpriteSheet.spritesHibrido[indexEspacio];
		
	}

	public void tick() {

	}
		
	public void render(Graphics g) {
		int anchoSprite = SpriteSheet.ANCHO_SPRITE;
		int altoSprite = SpriteSheet.ALTO_SPRITE;
		
		
		// Para renderizar nomas lo q podemos ver
		int xInicio = Math.max(0, camara.getxOffset() / anchoSprite);
		int xFin = Math.min(ancho, 1 + (camara.getxOffset() + anchoVentana) / anchoSprite);
		int yInicio = Math.max(0, camara.getyOffset() / altoSprite);
		int yFin = Math.min(alto, 1 + (camara.getyOffset() + altoVentana) / altoSprite);
		
		for (int y = yInicio; y < yFin; y++)
			for (int x = xInicio; x < xFin; x++){
				BufferedImage spriteTerreno = spriteTerreno(x, y);
				//datosMapa.getVisibilidad();
				g.drawImage(spriteTerreno, anchoSprite * x - camara.getxOffset(), 
						altoSprite * y - camara.getyOffset(), null);
			}
		// Lindo terran :D
		//g.drawImage(ImageLoader.loadImage("/textures/terran.png"), 0, 0, null);
				
		ubicarMarcaMapaEn(g);
	}

	
	private void ubicarMarcaMapaEn(Graphics g) {
		BufferedImage bi = ImageLoader.loadImage("/textures/marcaMapa.png");
		
		g.drawImage(bi, 
				filSeleccionada * SpriteSheet.ANCHO_SPRITE - camara.getxOffset(), 
				colSeleccionada * SpriteSheet.ALTO_SPRITE - camara.getyOffset(), null);
	}

	public void setCeldaSeleccionada(int x, int y) {
		this.filSeleccionada = (x + camara.getxOffset()) / SpriteSheet.ANCHO_SPRITE;
		this.colSeleccionada = (y + camara.getyOffset()) / SpriteSheet.ALTO_SPRITE;
	}
	
	public void moverCeldaSeleccionada(int i, int j) {
		
		int x = this.filSeleccionada;
		this.filSeleccionada = Math.min(Math.max(x + i, 0), ancho);
		
		int y = this.colSeleccionada;
		this.colSeleccionada = Math.min(Math.max(y + j, 0), alto);
		
	}
	
	public void moverCamara(int i, int j){
		camara.move(i, j);
	}
	
	// Horrible :D
	private int getIndexHibrido(int x, int y) {
		int indexEspacio = 0;
		Posicion posicionAMirar = new Posicion(x+1, y);
		if (datosMapa.celdaValida(posicionAMirar) && datosMapa.getTerreno(posicionAMirar) != Terreno.ESPACIO)
			indexEspacio = indexEspacio + 1;
		posicionAMirar = new Posicion(x, y-1);
		if (datosMapa.celdaValida(posicionAMirar) && datosMapa.getTerreno(posicionAMirar) != Terreno.ESPACIO)
			indexEspacio = indexEspacio + 2;
		posicionAMirar = new Posicion(x-1, y);
		if (datosMapa.celdaValida(posicionAMirar) && datosMapa.getTerreno(posicionAMirar) != Terreno.ESPACIO)
			indexEspacio = indexEspacio + 4;
		posicionAMirar = new Posicion(x, y+1);
		if (datosMapa.celdaValida(posicionAMirar) && datosMapa.getTerreno(posicionAMirar) != Terreno.ESPACIO)
			indexEspacio = indexEspacio + 8;
		return indexEspacio - 1;
	}
	
}
