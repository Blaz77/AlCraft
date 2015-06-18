package fiuba.algo3interfaz.gfx;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.util.Random;

import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;
import fiuba.algo3.terreno.Terreno;

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
		this.camara = new Camara(0, 0, 0, 0, ancho*32 - anchoVentana, alto*32 - altoVentana);
		
		Random miRNG = new Random();
		for (int j = 0; j < this.alto; j++)
			for (int i = 0; i < this.ancho; i++)
				mapaVista[i][j] = new CeldaVista(miRNG.nextInt());
		
	}
		
	public BufferedImage spriteTerreno(int x, int y){
		BufferedImage bi = null;
		int randomIndex = (mapaVista[x][y].getIndex()) % SpriteSheet.CANTIDAD_SPRITES;
		int indexEspacio;
		Terreno terreno = datosMapa.getTerreno(new Posicion(x, y));
		
		
		if (terreno == Terreno.SOMBRA)
			return SpriteSheet.spritesEspacio[1];
		
		
		if (terreno == Terreno.ESPACIO) {
			indexEspacio = getIndexHibrido(x, y);
			if (indexEspacio == -1) 
				bi = SpriteSheet.spritesEspacio[randomIndex];
			else
				bi = SpriteSheet.spritesHibrido[indexEspacio];
		}
				
		if (terreno == Terreno.TIERRA)
			bi =  SpriteSheet.spritesTierra[randomIndex];
		

		return bi;
		//return oscurecer(bi);
		
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
		
		
		Graphics2D g2 = (Graphics2D) g;
		for (int y = yInicio; y < yFin; y++)
			for (int x = xInicio; x < xFin; x++){
				BufferedImage spriteTerreno = spriteTerreno(x, y);
				
				if (datosMapa.getOcupante(new Posicion(x,y)).getTipo() == TipoOcupante.DESCONOCIDO)
					g2.drawImage(spriteTerreno, new RescaleOp(0.1f, 0, null), 
							anchoSprite * x - camara.getxOffset(), altoSprite * y - camara.getyOffset());
				else{
					g.drawImage(spriteTerreno, 
							anchoSprite * x - camara.getxOffset(), 	altoSprite * y - camara.getyOffset(), null);
					
					dibujarOcupante(g, x, y);
				}
			}
		// Lindo terran :D
		//g.drawImage(ImageLoader.loadImage("/textures/terran.png"), 0, 0, null);
				
		ubicarMarcaMapaEn(g);
		
	}

	

	private void dibujarOcupante(Graphics g, int x, int y) {
		
		TipoOcupante tipoOcupante = datosMapa.getOcupante(new Posicion(x, y)).getTipo();
		if (tipoOcupante == TipoOcupante.CELDA_VACIA)
			return;

		if (tipoOcupante == TipoOcupante.MINERAL)
			g.drawImage(ImageLoader.loadImage("/textures/cristal2.png"), 
					SpriteSheet.ANCHO_SPRITE * x - camara.getxOffset(), 	SpriteSheet.ALTO_SPRITE * y - camara.getyOffset(), null);
			
		else if (tipoOcupante == TipoOcupante.VESPENO)
			g.drawImage(ImageLoader.loadImage("/textures/volcan2.png"), 
					SpriteSheet.ANCHO_SPRITE * x - camara.getxOffset(), 	SpriteSheet.ALTO_SPRITE * y - camara.getyOffset(), null);
		else
			
		g.drawImage(ImageLoader.loadImage("/textures/terran.png"), 
				SpriteSheet.ANCHO_SPRITE * x - camara.getxOffset(), 	SpriteSheet.ALTO_SPRITE * y - camara.getyOffset(), null);

		
	}

	private void ubicarMarcaMapaEn(Graphics g) {
		BufferedImage bi = ImageLoader.loadImage("/textures/marcaMapa.png");
		
		g.drawImage(bi, 
				filSeleccionada * SpriteSheet.ANCHO_SPRITE - camara.getxOffset(), 
				colSeleccionada * SpriteSheet.ALTO_SPRITE - camara.getyOffset(), null);
	}
	
	public Posicion getPosicionCeldaSeleccionada() {
		// Deberia ser al reves
		return new Posicion(this.filSeleccionada, this.colSeleccionada);
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
