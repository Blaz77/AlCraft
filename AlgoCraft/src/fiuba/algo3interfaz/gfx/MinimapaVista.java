package fiuba.algo3interfaz.gfx;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.Ocupante;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.TipoOcupante;
import fiuba.algo3.terreno.Terreno;

/* Muestra el mapa personal de un jugador.
 * 
 * */
public class MinimapaVista extends JPanel implements MouseListener {

	private static final int ANCHO_CELDA = SpriteSheet.spritesTierra.getAncho();
	private static final int ALTO_CELDA = SpriteSheet.spritesTierra.getAlto();
	private static final int ANCHO_MAPA = 100;
	private int ALTO_MAPA;
	private int DISTANCIA_INFERIOR_MAPA;	// Sirve para centrar el mapa de altura variable
	private static final int ANCHO_HUD = 640;
	private static final int ALTURA_HUD = 165;
	
	private int ancho;
	private int alto;
	
	private Mapa datosMapa;
	private JPanel panel;

	private Camara camara;
	
	private BufferedImage bufferMinimapa;
	private JPanel centrante;
	private JPanel areaMinimapa;
	private float factorEscala; 
	private JPanel espaciadorDerecho;
	
	public MinimapaVista(Jugador jugador, JPanel panel, Camara camara){
		
		this.datosMapa = jugador.getMapa(); // new MapaReal(2);
		this.camara = camara;
		this.panel = panel;

		this.ancho = datosMapa.ancho();
		this.alto = datosMapa.alto();
		
		this.factorEscala = (float) this.ancho / (float) ANCHO_MAPA;
		this.ALTO_MAPA = this.alto;
		this.DISTANCIA_INFERIOR_MAPA = 65 + ALTO_MAPA / 2 ;
		
		this.bufferMinimapa = new BufferedImage(ANCHO_MAPA, ALTO_MAPA, BufferedImage.TYPE_INT_RGB);
		
		this.setFocusable(false);
		this.setVisible(false);
		this.setOpaque(false);
		
		setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(ANCHO_HUD, ALTURA_HUD));
		centrante = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		//centrante.setBackground(new java.awt.Color(255, 255, 255, 50));
		centrante.setOpaque(false);
		add(centrante, java.awt.BorderLayout.SOUTH);
		
		areaMinimapa = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		areaMinimapa.setPreferredSize(new Dimension(ANCHO_HUD / 4, ALTURA_HUD));
		//areaMinimapa.setBackground(new java.awt.Color(255, 255, 255, 100));
		areaMinimapa.addMouseListener(this);
		areaMinimapa.setOpaque(false);
		centrante.add(areaMinimapa);
		
		espaciadorDerecho = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		espaciadorDerecho.setPreferredSize(new Dimension(ANCHO_HUD * 3 / 4, ALTURA_HUD));
		//espaciadorDerecho.setBackground(new java.awt.Color(255, 255, 255, 20));
		espaciadorDerecho.setOpaque(false);
		centrante.add(espaciadorDerecho);

		
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
			return new java.awt.Color(100, 140, 70); // Verde claro
		
		
		return null;
		
	}

	public void tick() {

	}
	
	public void render(Graphics g) {
		
		for (int y_mini = 0; y_mini < ALTO_MAPA; y_mini++) {
			for (int x_mini = 0; x_mini < ANCHO_MAPA; x_mini++){
				guardarContenidoCeldaEnBuffer(x_mini, y_mini);
			}
		}
		dibujarBuffer(g);
				
		dibujarCuadroCamara(g);
		
	}


	private void guardarContenidoCeldaEnBuffer(int x_mini, int y_mini) {
		int x = (int) Math.floor(x_mini * this.factorEscala);
		int y = y_mini;
		this.bufferMinimapa.setRGB(x_mini, y_mini, getColorTerreno(x, y).getRGB());
	}


	private void dibujarBuffer(Graphics g) {
		int x = (panel.getWidth() / 2) - 300;
		int y = panel.getHeight() - DISTANCIA_INFERIOR_MAPA;
		
		g.drawImage(bufferMinimapa, x, y, null);
		
	}

	private void dibujarCuadroCamara(Graphics g) {
		int x_mini = (panel.getWidth() / 2) - 300;
		x_mini += (int) Math.floor((this.camara.getxOffset() / ANCHO_CELDA) / this.factorEscala);
		int y_mini = panel.getHeight() - DISTANCIA_INFERIOR_MAPA;
		y_mini += (int) Math.floor((this.camara.getyOffset() / ALTO_CELDA));
		int ancho_mini = (int) Math.floor((panel.getWidth() / (datosMapa.ancho()*32.0f) * ANCHO_MAPA));
		int alto_mini = (int) Math.floor((panel.getHeight() / (datosMapa.alto()*32.0f) * ALTO_MAPA));
		
		g.setColor(java.awt.Color.YELLOW);
		g.drawRect(x_mini, y_mini, ancho_mini, alto_mini);
	}
	
	/* Seccion activa */
	
	public void moverCamara(int i, int j){
		camara.move(i, j);
	}

	public void mouseClicked(MouseEvent e) {
		int ORIGEN_X = (panel.getWidth() / 2) - 300;
		int ORIGEN_Y = panel.getHeight() - DISTANCIA_INFERIOR_MAPA;
		int x = e.getX() + (panel.getWidth() - ANCHO_HUD) / 2;
		int y = e.getY() + (panel.getHeight() - ALTURA_HUD);
		
		if (x >= ORIGEN_X && x <= ORIGEN_X + ANCHO_MAPA) {
			if (y >= ORIGEN_Y && y <= ORIGEN_Y + ALTO_MAPA) {
				System.out.print(String.format("aas %f", this.factorEscala));
				camara.move(
						(int) Math.floor((x - ORIGEN_X) * 32f * factorEscala)- camara.getxOffset() - panel.getWidth() / 2,
						(y - ORIGEN_Y)*32 - camara.getyOffset() - panel.getHeight() / 2
						);
			}
		}
		
		panel.repaint();
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
	
}
