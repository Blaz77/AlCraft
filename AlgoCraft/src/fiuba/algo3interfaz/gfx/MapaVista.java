package fiuba.algo3interfaz.gfx;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;
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
	private static BufferedImage marcaMapa = SpriteSheet.getSpriteMarcaMapa();
	
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
				
				if (celdaDesconocida) {
					dibujarEnCeldaOscura(g2, spriteTerreno, x, y);
					suavizarBordes(g, x, y);
				}
				else{
					dibujarEnCelda(g, spriteTerreno, x, y);
					suavizarBordes(g, x, y);
				
					dibujarOcupante(g, x, y);
				}
			}
		
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
		dibujarEnCelda(g, sprite, x, y);
		dibujarEnCelda(g, SpriteSheet.getSpriteMediaSombra(), x, y);
	}
	
	private void suavizarEsquina(Graphics2D g2, int x, int y, int destinoX, int destinoY) {
		int centerDestX_screen = ANCHO_CELDA * destinoX - camara.getxOffset() + ANCHO_CELDA / 2;
		int centerDestY_screen = ALTO_CELDA  * destinoY - camara.getyOffset() + ALTO_CELDA / 2;
		
		int x_screen = ANCHO_CELDA * x - camara.getxOffset();
		int y_screen = ALTO_CELDA * y - camara.getyOffset();
		
		java.awt.Color colores[] = new java.awt.Color[] {java.awt.Color.BLACK, new java.awt.Color(0, 0, 0, 0)};
		
		try {
			if (spriteTerreno(destinoX, destinoY) == null) {
				if (spriteTerreno(x, destinoY) == null && spriteTerreno(destinoX, y) == null) {
					// Esquina externa
					g2.setPaint(new RadialGradientPaint( centerDestX_screen, centerDestY_screen,
														ANCHO_CELDA * 2, new float[] {0.5f, 1.0f}, colores));
					g2.fillRect(x_screen, y_screen, ANCHO_CELDA, ALTO_CELDA);
				}
				else if	(spriteTerreno(x, destinoY) != null && spriteTerreno(destinoX, y) != null) {
					// Esquina interna
					g2.setPaint(new RadialGradientPaint( centerDestX_screen, centerDestY_screen,
														ANCHO_CELDA * 2, new float[] {0.3f, 0.6f}, colores));
					g2.fillRect(x_screen, y_screen, ANCHO_CELDA, ALTO_CELDA);
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	/* Pre: Las posiciones deben 'tocarse' */
	private void suavizarBorde(Graphics2D g2, int x, int y, int destinoX, int destinoY) {
		int centerX_screen = ANCHO_CELDA * x - camara.getxOffset() + ANCHO_CELDA / 2;
		int centerY_screen = ALTO_CELDA  * y - camara.getyOffset() + ALTO_CELDA / 2;
		
		try {
			if (spriteTerreno(destinoX, destinoY) == null) {
				g2.setPaint(new GradientPaint( centerX_screen + (destinoX - x) * ANCHO_CELDA / 2, 
												centerY_screen + (destinoY - y) * ALTO_CELDA / 2, 
												java.awt.Color.BLACK, 
												centerX_screen, 
												centerY_screen, 
												new java.awt.Color(0, 0, 0, 0)));
				g2.fillRect(centerX_screen - ANCHO_CELDA / 2, centerY_screen - ALTO_CELDA / 2, 
							ANCHO_CELDA, ALTO_CELDA);
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
		}
	}
	
	private void suavizarBordes(Graphics g, int x, int y) {
		if (spriteTerreno(x, y) == null) return;
		Graphics2D g2 = (Graphics2D) g;
		
		// Izquierda
		suavizarBorde(g2, x, y, x-1, y);
		
		// Derecha
		suavizarBorde(g2, x, y, x+1, y);
		
		// Arriba
		suavizarBorde(g2, x, y, x, y-1);
		
		// Abajo
		suavizarBorde(g2, x, y, x, y+1);
		
		// Esquinas
		suavizarEsquina(g2, x, y, x-1, y-1);
		suavizarEsquina(g2, x, y, x-1, y+1);
		suavizarEsquina(g2, x, y, x+1, y-1);
		suavizarEsquina(g2, x, y, x+1, y+1);
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
