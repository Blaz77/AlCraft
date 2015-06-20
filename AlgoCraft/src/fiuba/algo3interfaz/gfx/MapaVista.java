package fiuba.algo3interfaz.gfx;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.RescaleOp;
import java.util.Random;

import javax.swing.JPanel;

import fiuba.algo3.juego.Color;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.MapaReal;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;
import fiuba.algo3.terreno.Terreno;

public class MapaVista {

	private static final int ANCHO_CELDA = SpriteSheet.spritesTierra.getAncho();
	private static final int ALTO_CELDA = SpriteSheet.spritesTierra.getAlto();
	private static final float RESCALE_FACTOR = 0.1f;
	private static final BufferedImageOp RESCALE_OP = new RescaleOp(RESCALE_FACTOR, 0, null);
	
	private CeldaVista[][] mapaVista;
	private int ancho;
	private int alto;
	
	private int filSeleccionada = 0;
	private int colSeleccionada = 0;
	private Mapa datosMapa;
	private JPanel panel;

	private Camara camara;
	
	private BufferedImage marcaMapa = SpriteSheet.getSpriteMarcaMapa();
	
	public MapaVista(Mapa mapa, int anchoVentana, int altoVentana, JPanel panel){
		
		this.datosMapa = new MapaReal(2);
		this.panel = panel;

		this.ancho = datosMapa.ancho();
		this.alto = datosMapa.alto();
		this.mapaVista = new CeldaVista[ancho][alto];
		this.camara = new Camara(0, 0, 0, 0, ancho*32 - anchoVentana, alto*32 - altoVentana);
		
		Random miRNG = new Random();
		for (int j = 0; j < this.alto; j++)
			for (int i = 0; i < this.ancho; i++)
				mapaVista[i][j] = new CeldaVista(miRNG.nextInt());
		
	}
	
	public BufferedImage spriteTerreno(int x, int y){
		BufferedImage bi = null;
		int randomIndex = mapaVista[x][y].getIndex();
		int indexEspacio;
		Terreno terreno = datosMapa.getTerreno(new Posicion(x, y));
		
		
		if (terreno == Terreno.SOMBRA)
			return null;
		
		
		if (terreno == Terreno.ESPACIO) {
			indexEspacio = getIndexHibrido(x, y);
			if (indexEspacio == -1) 
				bi = SpriteSheet.spritesEspacio.get(randomIndex);
			else
				bi = SpriteSheet.spritesHibrido.get(indexEspacio);
		}
				
		if (terreno == Terreno.TIERRA)
			bi =  SpriteSheet.spritesTierra.get(randomIndex);
		

		return bi;
		//return oscurecer(bi);
		
	}

	public void tick() {

	}
	
	public void render(Graphics g) {
				
		int anchoSprite = SpriteSheet.spritesTierra.getAncho();
		int altoSprite = SpriteSheet.spritesTierra.getAlto();
		
		
		// Para renderizar nomas lo q podemos ver
		int xInicio = Math.max(0, camara.getxOffset() / anchoSprite);
		int xFin = Math.min(ancho, 1 + (camara.getxOffset() + panel.getWidth()) / anchoSprite);
		int yInicio = Math.max(0, camara.getyOffset() / altoSprite);
		int yFin = Math.min(alto, 1 + (camara.getyOffset() + panel.getHeight()) / altoSprite);
		
		
		Graphics2D g2 = (Graphics2D) g;
		for (int y = yInicio; y < yFin; y++)
			for (int x = xInicio; x < xFin; x++){
				BufferedImage spriteTerreno = spriteTerreno(x, y);
				
				boolean celdaDesconocida = datosMapa.getOcupante(new Posicion(x,y)).getTipo() == TipoOcupante.DESCONOCIDO;
				
				if (celdaDesconocida)
					g2.drawImage(spriteTerreno, RESCALE_OP, 
							anchoSprite * x - camara.getxOffset(), altoSprite * y - camara.getyOffset());
				else{
					dibujarEnCelda(g, spriteTerreno, x, y);
				
					dibujarOcupante(g, x, y);
				}
			}
		// Lindo terran :D
		SpriteSheet terranSprites = new SpriteSheet("/textures/unidadesTerran.png");
		for (int c = 0; c < 8; c++)
			for (int u = 0; u < 5; u++){
				SpriteSheet.swapColors(terranSprites.get(u), Color.values()[c]);
				dibujarEnCelda(g, terranSprites.get(u), 2*c, 2*u);
				terranSprites = new SpriteSheet("/textures/unidadesTerran.png");

			}
		//dibujarEnCelda(g, terran, ancho-1, alto-1);
				
		ubicarMarcaMapaEn(g);
		
	}

	private void dibujarEnCelda(Graphics g, BufferedImage sprite, int x, int y) {

		g.drawImage(sprite, 
				ANCHO_CELDA * x - camara.getxOffset() + (ANCHO_CELDA - sprite.getWidth())/2, 
				ALTO_CELDA  * y - camara.getyOffset() +  (ALTO_CELDA - sprite.getHeight())/2, null);
	}

	

	private void dibujarOcupante(Graphics g, int x, int y) {
				
		TipoOcupante tipoOcupante = datosMapa.getOcupante(new Posicion(x, y)).getTipo();
		if (tipoOcupante == TipoOcupante.CELDA_VACIA)
			return;

		BufferedImage bi = SpriteSheet.getSprites(tipoOcupante).get(mapaVista[x][y].getIndex());
		dibujarEnCelda(g, bi, x, y);
		//g.drawImage(ImageLoader.loadImage("/textures/terran.png"), 
		//		anchoSprite * x - camara.getxOffset(), 	altoSprite * y - camara.getyOffset(), null);

	}

	private void ubicarMarcaMapaEn(Graphics g) {
		
		g.drawImage(marcaMapa, 
				filSeleccionada * marcaMapa.getWidth() - camara.getxOffset(), 
				colSeleccionada * marcaMapa.getHeight() - camara.getyOffset(), null);
	}
	
	public Posicion getPosicionCeldaSeleccionada() {
		// Deberia ser al reves
		return new Posicion(this.filSeleccionada, this.colSeleccionada);
	}

	public void setCeldaSeleccionada(int x, int y) {
		this.filSeleccionada = (x + camara.getxOffset()) / marcaMapa.getWidth();
		this.colSeleccionada = (y + camara.getyOffset()) / marcaMapa.getHeight();
	}
	
	public void moverCeldaSeleccionada(int i, int j) {
		
		int x = this.filSeleccionada;
		this.filSeleccionada = Math.min(Math.max(x + i, 0), ancho-1);
		
		int y = this.colSeleccionada;
		this.colSeleccionada = Math.min(Math.max(y + j, 0), alto-1);
		
	}
	
	public void moverCamara(int i, int j){
		camara.move(i, j);
	}
	
	/* Obtiene una de las celdas 
	 * */
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
