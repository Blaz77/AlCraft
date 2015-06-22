package fiuba.algo3interfaz.gfx;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.RescaleOp;
import java.util.Random;

import javax.swing.JPanel;

import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.Ocupante;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.TipoOcupante;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.terreno.Terreno;

/* Muestra el mapa personal de un jugador.
 * 
 * */
public class MinimapaVista extends JPanel implements MouseListener {

	private static final int ANCHO_CELDA = SpriteSheet.spritesTierra.getAncho();
	private static final int ALTO_CELDA = SpriteSheet.spritesTierra.getAlto();
	private static final float RESCALE_FACTOR = 0.1f;
	private static final BufferedImageOp RESCALE_OP = new RescaleOp(RESCALE_FACTOR, 0, null);
	private static final int ANCHO_MAPA = 100;
	private static final int ALTO_MAPA = 100;
	private static final int ALTURA_HUD = 100;
	private static final int ANCHO_HUD = 100;
	
	private CeldaVista[][] mapaVista;
	private int ancho;
	private int alto;
	
	private int filSeleccionada;
	private int colSeleccionada;
	private Mapa datosMapa;
	private JPanel panel;

	private Camara camara;
	
	private BufferedImage marcaMapa = SpriteSheet.getSpriteMarcaMapa();
	private JPanel centrante;
	private JPanel areaMinimapa;
	private int factorEscala;
	
	public MinimapaVista(Mapa mapa, JPanel panel, Camara camara){
		
		this.datosMapa = mapa; // new MapaReal(2);
		this.camara = camara;
		this.panel = panel;

		this.ancho = datosMapa.ancho();
		this.alto = datosMapa.alto();
		this.mapaVista = new CeldaVista[ancho][alto];
		
		this.factorEscala = ANCHO_MAPA / this.ancho;
		
		this.setFocusable(false);
		this.setVisible(false);
		this.setOpaque(false);
		
		setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(ANCHO_HUD, ALTURA_HUD));
		centrante = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		centrante.setBackground(new java.awt.Color(255, 255, 255, 50));
		centrante.setOpaque(false);
		add(centrante, java.awt.BorderLayout.SOUTH);
		
		areaMinimapa = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		areaMinimapa.setPreferredSize(new Dimension(ANCHO_HUD / 4, ALTURA_HUD));
		areaMinimapa.setBackground(new java.awt.Color(255, 255, 255, 100));
		areaMinimapa.addMouseListener(this);
		areaMinimapa.setOpaque(false);
		centrante.add(areaMinimapa);
		
		/*controlCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		controlCentral.setPreferredSize(new Dimension(hudImage.getWidth() / 2, hudImage.getHeight()));
		//controlCentral.setBackground(new java.awt.Color(255, 255, 255, 20));
		controlCentral.setOpaque(false);
		centrante.add(controlCentral);*/

		
	}
	
	public java.awt.Color getColorTerreno(int x, int y){
		Ocupante ocupante = datosMapa.getOcupante(new Posicion(x, y));
		
		if (ocupante.getTipoOcupante() == TipoOcupante.RECURSO) {
			if (ocupante.getTipo() == Tipo.MINERAL)
				return java.awt.Color.CYAN;
			else if (ocupante.getTipo() == Tipo.VESPENO)
				return java.awt.Color.GREEN;
		}
		else if (ocupante.getTipoOcupante() == TipoOcupante.UNIDAD ||
				ocupante.getTipoOcupante() == TipoOcupante.EDIFICIO) {
			// Masacre de enums
			return AdaptadorColor.values()[((ObjetoVivo) ocupante).getPropietario().getColor().ordinal()].getColorAwtAsociado();
			
		}
		
		Terreno terreno = datosMapa.getTerreno(new Posicion(x, y));
		
		if (terreno == Terreno.SOMBRA)
			return java.awt.Color.BLACK;
		
		if (terreno == Terreno.ESPACIO)
			return java.awt.Color.DARK_GRAY;
				
		if (terreno == Terreno.TIERRA)
			return java.awt.Color.getHSBColor(210, 250, 180); // Verde claro
		

		return null;
		//return oscurecer(bi);
		
	}

	public void tick() {

	}
	
	public void render(Graphics g) {
		
		/*// Para renderizar nomas lo q podemos ver
		int xInicio = Math.max(0, camara.getxOffset() / anchoSprite);
		int xFin = Math.min(ancho, 1 + (camara.getxOffset() + panel.getWidth()) / anchoSprite);
		int yInicio = Math.max(0, camara.getyOffset() / altoSprite);
		int yFin = Math.min(alto, 1 + (camara.getyOffset() + panel.getHeight()) / altoSprite);
		*/
		
		Graphics2D g2 = (Graphics2D) g;
		for (int y = 0; y < ALTO_MAPA; y++) {
			for (int x = 0; x < ANCHO_MAPA; x++){
				dibujarContenidoCelda(g, x, y);
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
				
		ubicarMarcaMapaEn(g);
		
	}

	private void dibujarContenidoCelda(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	private void dibujarEnCelda(Graphics g, BufferedImage sprite, int x, int y) {

		g.drawImage(sprite, 
				ANCHO_CELDA * x - camara.getxOffset() + (ANCHO_CELDA - sprite.getWidth())/2, 
				ALTO_CELDA  * y - camara.getyOffset() +  (ALTO_CELDA - sprite.getHeight())/2, null);
	}

	

	private void dibujarOcupante(Graphics g, int x, int y) {
				
		Tipo tipo = datosMapa.getOcupante(new Posicion(x, y)).getTipo();
		if (tipo == Tipo.CELDA_VACIA)
			return;

		if (tipo.getTipoOcupante() == TipoOcupante.EDIFICIO) {
			Edificio edificio = (Edificio) datosMapa.getOcupante(new Posicion(x, y));
			BufferedImage bi = CopyOfSpriteSheet.getSprites(edificio.getPropietario().getRaza())
					.get(edificio.getTipo().ordinal());
			dibujarEnCelda(g, bi, x, y);
			return;
		}
		
		if (tipo.getTipoOcupante() == TipoOcupante.UNIDAD) {
			//
			BufferedImage bi = CopyOfSpriteSheet.getSprites(tipo.getTipoRaza()).get(tipo.ordinal());
			dibujarEnCelda(g, bi, x, y);
			return;
		}
		
		BufferedImage bi = SpriteSheet.getSprites(tipo).get(mapaVista[x][y].getIndex());
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

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
