package fiuba.algo3interfaz.gfx;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.RescaleOp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import javax.swing.JPanel;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.TipoOcupante;
import fiuba.algo3.terreno.Terreno;

/* Muestra el mapa personal de un jugador.
 * 
 * */
public class MapaVista {

	private static final int ANCHO_CELDA = SpriteSheet.spritesTierra.getAncho();
	private static final int ALTO_CELDA = SpriteSheet.spritesTierra.getAlto();
	private static final float RESCALE_FACTOR = 0.5f;
	private static final BufferedImageOp RESCALE_OP = new RescaleOp(RESCALE_FACTOR, 0, null);
	private static BufferedImage marcaMapa = SpriteSheet.getSpriteMarcaMapa();
	private static BufferedImage mediaSombra = SpriteSheet.getSpriteMediaSombra();
	
	private Jugador jugador;
	
	private Mapa datosMapa;
	private int ancho;
	private int alto;

	private JPanel panel;
	private CeldaVista[][] mapaVista;
	private Camara camara;
	private int filSeleccionada;
	private int colSeleccionada;
	private ArrayList<Posicion> celdasTonalizadas;
	private BufferedImage tonalizador;	

	public MapaVista(Jugador jugador, JPanel panel){
		
		this.jugador = jugador;
		this.datosMapa = jugador.getMapa(); // new MapaReal(2)
		this.panel = panel;

		this.ancho = datosMapa.ancho();
		this.alto = datosMapa.alto();
		this.filSeleccionada = datosMapa.getPosicionInicial().getX();
		this.colSeleccionada = datosMapa.getPosicionInicial().getY();
		this.mapaVista = new CeldaVista[ancho][alto];
		this.celdasTonalizadas = new ArrayList<Posicion>();
		this.camara = new Camara(filSeleccionada*ANCHO_CELDA - panel.getWidth() / 2, 
								colSeleccionada*ANCHO_CELDA - panel.getHeight() / 2, 
								ancho*ANCHO_CELDA, alto*ALTO_CELDA, panel);
		
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
		
		SpriteSheet spritesTierra = SpriteSheet.getSpritesTierra();
		SpriteSheet spritesEspacio = SpriteSheet.getSpritesEspacio();
		SpriteSheet spritesHibrido = SpriteSheet.getSpritesHibrido();
		
		if (terreno == Terreno.SOMBRA)
			return null;
		
		if (terreno == Terreno.ESPACIO) {
			indexEspacio = getIndexHibrido(x, y);
			if (indexEspacio == -1) 
				bi = spritesEspacio.get(randomIndex);
			else
				bi = spritesHibrido.get(indexEspacio);
		}
				
		if (terreno == Terreno.TIERRA)
			bi =  spritesTierra.get(randomIndex);
		
		return bi;
	}

	public void tick() {

	}
	
	public void render(Graphics g) {		
		
		// Para renderizar nomas lo q podemos ver
		int xInicio = Math.max(0, camara.getxOffset() / ANCHO_CELDA);
		int xFin = Math.min(ancho, 1 + (camara.getxOffset() + panel.getWidth()) / ANCHO_CELDA);
		int yInicio = Math.max(0, camara.getyOffset() / ALTO_CELDA);
		int yFin = Math.min(alto, 1 + (camara.getyOffset() + panel.getHeight()) / ALTO_CELDA);
		
		
		Graphics2D g2 = (Graphics2D) g;
		for (int y = yInicio; y < yFin; y++)
			for (int x = xInicio; x < xFin; x++){
				BufferedImage spriteTerreno = spriteTerreno(x, y);
				
				boolean celdaDesconocida = datosMapa.getOcupante(new Posicion(x,y)).getTipo() == Tipo.DESCONOCIDO;
				
				if (celdaDesconocida)
					dibujarEnCeldaOscura(g2, spriteTerreno, x, y);
				else{
					dibujarEnCelda(g, spriteTerreno, x, y);
				
					dibujarOcupante(g, x, y);
				}
			}
		// Lindo terran :D
		/*SpriteSheet terranSprites = new SpriteSheet("/textures/edificiosTerran.png");
		for (int c = 0; c < 8; c++)
			for (int u = 0; u < 6; u++){
				SpriteSheet.swapColors(terranSprites.get(u), Color.values()[c]);
				dibujarEnCelda(g, terranSprites.get(u), 2*c, 2*u);
				terranSprites = new SpriteSheet("/textures/edificiosTerran.png");

			}*/
		//dibujarEnCelda(g, terran, ancho-1, alto-1);
		
		dibujarTonalizadores(g);
		ubicarMarcaMapaEn(g);
		
	}


	private void dibujarEnCelda(Graphics g, BufferedImage sprite, int x, int y) {
		if (sprite == null) return;
		g.drawImage(sprite, 
				ANCHO_CELDA * x - camara.getxOffset() + (ANCHO_CELDA - sprite.getWidth())/2, 
				ALTO_CELDA  * y - camara.getyOffset() +  (ALTO_CELDA - sprite.getHeight())/2, null);
	}

	private void dibujarEnCeldaOscura(Graphics g, BufferedImage sprite, int x, int y) {
		if (sprite == null) return;
		g.drawImage(sprite, 
				ANCHO_CELDA * x - camara.getxOffset() + (ANCHO_CELDA - sprite.getWidth())/2, 
				ALTO_CELDA  * y - camara.getyOffset() +  (ALTO_CELDA - sprite.getHeight())/2, null);
		g.drawImage(SpriteSheet.getSpriteMediaSombra(), 
				ANCHO_CELDA * x - camara.getxOffset() + (ANCHO_CELDA - sprite.getWidth())/2, 
				ALTO_CELDA  * y - camara.getyOffset() +  (ALTO_CELDA - sprite.getHeight())/2, null);
		
		/*if (sprite == null)
			return;*/
		/*((Graphics2D) g).drawImage(sprite, RESCALE_OP, 
				ANCHO_CELDA * x - camara.getxOffset() + (ANCHO_CELDA - sprite.getWidth())/2, 
				ALTO_CELDA  * y - camara.getyOffset() +  (ALTO_CELDA - sprite.getHeight())/2);*/
	}


	private void dibujarOcupante(Graphics g, int x, int y) {
				
		Tipo tipo = datosMapa.getOcupante(new Posicion(x, y)).getTipo();
		if (tipo == Tipo.CELDA_VACIA)
			return;
		if (tipo.getTipoOcupante() == TipoOcupante.UNIDAD || tipo.getTipoOcupante() == TipoOcupante.EDIFICIO)
			dibujarObjetoVivo(g, x, y, tipo);
		else if (tipo.getTipoOcupante() == TipoOcupante.RECURSO)
			dibujarRecurso(g, x, y, tipo);
		else
			throw new RuntimeException();

	}

	private void dibujarObjetoVivo(Graphics g, int x, int y, Tipo tipo) {
		Jugador propietario = ((ObjetoVivo)(datosMapa.getOcupante(new Posicion(x, y)))).getPropietario();
		BufferedImage bi = SpriteSheet.getSpritesJugador(propietario).get(tipo.ordinal());
		dibujarEnCelda(g, bi, x, y);
	}

	private void dibujarRecurso(Graphics g, int x, int y, Tipo tipo) {
		BufferedImage bi = SpriteSheet.getSpritesRecurso(tipo).get(mapaVista[x][y].getIndex());
		dibujarEnCelda(g, bi, x, y);
	}

	private void ubicarMarcaMapaEn(Graphics g) {
		dibujarEnCelda(g, marcaMapa, filSeleccionada, colSeleccionada);
	}
	
	private void dibujarTonalizadores(Graphics g) {
		for (Posicion posicion : this.celdasTonalizadas) {
			dibujarEnCelda(g, tonalizador, posicion.getX(), posicion.getY());
		}
	}
	
	public Posicion getPosicionCeldaSeleccionada() {
		// Deberia ser al reves
		return new Posicion(this.filSeleccionada, this.colSeleccionada);
	}

	public void setCeldaSeleccionada(int x, int y) {
		
		int fil = (x + camara.getxOffset()) / marcaMapa.getWidth();
		int col = (y + camara.getyOffset()) / marcaMapa.getHeight();
		
		if (fil < 0 || fil >= ancho || col < 0 || col >= alto)
			return;
		this.filSeleccionada = fil;
		this.colSeleccionada = col;
	}
	
	public void moverCeldaSeleccionada(int i, int j) {
		
		if (i == 0 && j == 0) return;
		int x = this.filSeleccionada;
		this.filSeleccionada = Math.min(Math.max(x + i, 0), ancho-1);
		
		int y = this.colSeleccionada;
		this.colSeleccionada = Math.min(Math.max(y + j, 0), alto-1);
		
		if (this.filSeleccionada * ANCHO_CELDA <= camara.getxOffset())
			camara.move(this.filSeleccionada * ANCHO_CELDA - camara.getxOffset(), 0);
		if ((this.filSeleccionada) * ANCHO_CELDA >= camara.getxOffset() + panel.getWidth())
			camara.move((this.filSeleccionada + 1) * ANCHO_CELDA - (camara.getxOffset() + panel.getWidth()), 0);
		if (this.colSeleccionada * ALTO_CELDA <= camara.getyOffset())
			camara.move(0, this.colSeleccionada * ALTO_CELDA - camara.getyOffset());
		if ((this.colSeleccionada) * ALTO_CELDA >= camara.getyOffset() + panel.getHeight())
			camara.move(0, (this.colSeleccionada + 1) * ALTO_CELDA - (camara.getyOffset() + panel.getHeight()));
		
	}
	
	public Camara getCamara() {
		return camara;
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

	public void definirTonalizadores(BufferedImage picTonalizadorMovimiento,
			HashSet<Posicion> posiblesMovimientos) {
		this.celdasTonalizadas.clear();
		this.tonalizador = picTonalizadorMovimiento;
		for (Posicion posicion : posiblesMovimientos) {
			this.celdasTonalizadas.add(posicion);
		}
	}
	
}
