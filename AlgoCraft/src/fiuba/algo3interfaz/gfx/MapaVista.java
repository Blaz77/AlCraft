package fiuba.algo3interfaz.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.terreno.TipoTerreno;

public class MapaVista {
	private CeldaVista[][] mapaVista;
	private int ancho;
	private int alto;
	
	private int filSeleccionada = 0;
	private int colSeleccionada = 0;
	private Mapa datosMapa;
	
	public MapaVista(Mapa mapa){
		
		this.ancho = mapa.ancho();
		this.alto = mapa.alto();
		this.datosMapa = mapa;
		this.mapaVista = new CeldaVista[ancho][alto];
		
		Random miRNG = new Random();
		for (int j = 0; j < this.alto; j++)
			for (int i = 0; i < this.ancho; i++)
				mapaVista[i][j] = new CeldaVista(miRNG.nextInt());
	}
	
	public MapaVista(int ancho, int alto){
		this.mapaVista = new CeldaVista[ancho][alto];
		this.ancho = ancho;
		this.alto = alto;
		
		Random miRNG = new Random();
		for (int j = 0; j < this.alto; j++)
			for (int i = 0; i < this.ancho; i++)
				mapaVista[i][j] = new CeldaVista(miRNG.nextInt());
	}
	
	public BufferedImage obtenerSprite(int x, int y){
		int randomIndex = (mapaVista[x][y].getIndex()) % SpriteSheet.CANTIDAD_SPRITES;
		if (datosMapa.getTerreno(new Posicion(x, y)).getTipo() == TipoTerreno.TIERRA)
			return SpriteSheet.spritesTierra[randomIndex];
		
		int indexEspacio = getIndexHibrido(x, y);

		if (indexEspacio == -1)
			return SpriteSheet.spritesEspacio[randomIndex];
		
		return SpriteSheet.spritesHibrido[indexEspacio];
		
	}

	// Horrible :D
	private int getIndexHibrido(int x, int y) {
		int indexEspacio = 0;
		Posicion posicionAMirar = new Posicion(x+1, y);
		if (datosMapa.celdaValida(posicionAMirar) && datosMapa.getTerreno(posicionAMirar).getTipo() != TipoTerreno.ESPACIO)
			indexEspacio = indexEspacio + 1;
		posicionAMirar = new Posicion(x, y-1);
		if (datosMapa.celdaValida(posicionAMirar) && datosMapa.getTerreno(posicionAMirar).getTipo() != TipoTerreno.ESPACIO)
			indexEspacio = indexEspacio + 2;
		posicionAMirar = new Posicion(x-1, y);
		if (datosMapa.celdaValida(posicionAMirar) && datosMapa.getTerreno(posicionAMirar).getTipo() != TipoTerreno.ESPACIO)
			indexEspacio = indexEspacio + 4;
		posicionAMirar = new Posicion(x, y+1);
		if (datosMapa.celdaValida(posicionAMirar) && datosMapa.getTerreno(posicionAMirar).getTipo() != TipoTerreno.ESPACIO)
			indexEspacio = indexEspacio + 8;
		return indexEspacio - 1;
	}

	public void tick() {
		
	}
		
	public void render(Graphics g) {
		int anchoSprite = SpriteSheet.ANCHO_SPRITE;
		int altoSprite = SpriteSheet.ALTO_SPRITE;
		int borde = 0;
		
		for (int y = 0; y < alto; y++)
			for (int x = 0; x < ancho; x++)
				// Dibujo el en la celda x,y el sprite indicado en mapaGrafico[x][y]
				g.drawImage(obtenerSprite(x, y), borde + anchoSprite * x, borde + altoSprite * y, null);
		
		// Lindo terran :D
		//g.drawImage(ImageLoader.loadImage("/textures/terran.png"), 0, 0, null);
				
		ubicarMarcaMapaEn(g);
	}

	
	private void ubicarMarcaMapaEn(Graphics g) {
		BufferedImage bi = ImageLoader.loadImage("/textures/marcaMapa.png");
		
		g.drawImage(bi, filSeleccionada * SpriteSheet.ANCHO_SPRITE, colSeleccionada * SpriteSheet.ALTO_SPRITE, null);
	}

	public void setCeldaSeleccionada(int x, int y) {
		this.filSeleccionada = x / SpriteSheet.ANCHO_SPRITE;
		this.colSeleccionada = y / SpriteSheet.ALTO_SPRITE;
	}
	
	public void moverCeldaSeleccionada(int i, int j) {
		
		this.filSeleccionada += i;
		if (filSeleccionada < 0) filSeleccionada = 0;
		if (filSeleccionada >= ancho) filSeleccionada = ancho - 1;
		
		this.colSeleccionada += j;
		if (colSeleccionada < 0) colSeleccionada = 0;
		if (colSeleccionada >= alto) colSeleccionada = alto - 1;
	}	
}
