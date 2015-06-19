package fiuba.algo3interfaz.gfx;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3interfaz.input.btnConstruirEdificioIncrementadorPoblacionMouseListener;
import fiuba.algo3interfaz.input.btnConstruirEdificioRecolectorGasVespenoMouseListener;
import fiuba.algo3interfaz.input.btnConstruirEdificioRecolectorMineralMouseListener;
import fiuba.algo3interfaz.input.btnConstruirMouseListener;


public class HudVista extends JPanel {
	
	private TipoRaza raza;
	private Color color;
	private Jugador jugador; // dale que va, esto ya es cualquier cosa
	private Mapa mapaVisible;
	private Posicion celdaSeleccionada = new Posicion(0,0);
	

	private int anchoVentana;
	private int altoVentana;
	
	private JButton btnConstruir;
	private JPanel panel;
	private JButton btnConstruirEdificioRecolectorMineral;
	private JButton btnConstruirEdificioRecolectorGasVespeno;
	private JButton btnConstruirEdificioIncrementadorPoblacion;
	
	/* Precarga de imagenes */
	private static BufferedImage picConstruir = ImageLoader.loadImage("/textures/construir.png");


	public HudVista(Jugador jugador, int anchoVentana, int altoVentana, JPanel panel){
		this.raza = jugador.getRaza();
		this.color = jugador.getColor();
		this.jugador = jugador;
		this.panel = panel; //Experimental
		this.mapaVisible = jugador.getMapa();
		
		this.anchoVentana = anchoVentana;
		this.altoVentana = altoVentana;
		this.setVisible(false);
		
		
		/* Botones utiles inicialmente invisibles */
		btnConstruir = new JButton(new ImageIcon(picConstruir));
		btnConstruir.addMouseListener(new btnConstruirMouseListener(this));
		btnConstruir.setVisible(true);
		add(btnConstruir);
		
		// Edificios construibles
		btnConstruirEdificioRecolectorMineral = new JButton(new ImageIcon(
				/* Extraer de sprite con el diccionario de sprites de los atributos del jugador */));
		btnConstruirEdificioRecolectorMineral.addMouseListener(new btnConstruirEdificioRecolectorMineralMouseListener(this));
		btnConstruirEdificioRecolectorMineral.setVisible(false);
		add(btnConstruirEdificioRecolectorMineral);
		
		btnConstruirEdificioRecolectorGasVespeno = new JButton(new ImageIcon(
				/* Extraer de sprite con el diccionario de sprites de los atributos del jugador */));
		btnConstruirEdificioRecolectorGasVespeno.addMouseListener(new btnConstruirEdificioRecolectorGasVespenoMouseListener(this));
		btnConstruirEdificioRecolectorGasVespeno.setVisible(false);
		add(btnConstruirEdificioRecolectorGasVespeno);
		
		btnConstruirEdificioIncrementadorPoblacion = new JButton(new ImageIcon(
				/* Extraer de sprite con el diccionario de sprites de los atributos del jugador */));
		btnConstruirEdificioIncrementadorPoblacion.addMouseListener(new btnConstruirEdificioIncrementadorPoblacionMouseListener(this));
		btnConstruirEdificioIncrementadorPoblacion.setVisible(false);
		add(btnConstruirEdificioIncrementadorPoblacion);
		
		// por ahora hasta ahi...
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g) {
		
		dibujarHUD(g);
		dibujarDatosJugador(g);
		//dibujarMinimapa(g); Tal vez en otra vista
		dibujarDatosCeldaSeleccionada(g);
		dibujarAccionesPosibles(g);
	}
	
	private static BufferedImage hudTest = ImageLoader.loadImage("/HUDs/HUDterran2.png"); //HUDzerg2.png, HUDprotoss2.png
	private void dibujarHUD(Graphics g) {
		
		g.drawImage(hudTest, (panel.getWidth() - hudTest.getWidth())/2, panel.getHeight() - hudTest.getHeight(), null);
		
	}
	
	private void dibujarDatosJugador(Graphics g) {

		g.setFont(new Font("Serif", Font.PLAIN, 36));
		g.setColor(java.awt.Color.WHITE);
		g.drawString(this.jugador.getNombre(), 120, 380);
		
	}
	
	private void dibujarDatosCeldaSeleccionada(Graphics g) {
		g.setFont(new Font("Serif", Font.PLAIN, 18));
		g.setColor(java.awt.Color.WHITE);
		
		g.drawString(this.mapaVisible.getTerreno(celdaSeleccionada).name(),
				120, 400);
		g.drawString(this.mapaVisible.getOcupante(celdaSeleccionada).getTipo().name(),
				120, 420);
	}

	private void dibujarAccionesPosibles(Graphics g) {
		if (this.mapaVisible.getOcupante(this.celdaSeleccionada).getTipo() == TipoOcupante.CELDA_VACIA) {
			g.drawImage(picConstruir, 400, 400, null);
		}
		
	}
	
	public void actualizar(Posicion nuevaCeldaSeleccionada) {
		this.celdaSeleccionada = nuevaCeldaSeleccionada;
	}


	public void recibirClick(int x, int y) {
		// Obsoleto
	}

	public void mostrarOpcionesConstruccion() {
		btnConstruir.setVisible(false);
		btnConstruirEdificioRecolectorMineral.setVisible(true);
		btnConstruirEdificioRecolectorGasVespeno.setVisible(true);
		btnConstruirEdificioIncrementadorPoblacion.setVisible(true);
	}

	public void mostrarOpcionesColocacion() {
		
		// btnCancelar ?
	}
}
