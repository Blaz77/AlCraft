package fiuba.algo3interfaz.gfx;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.recurso.Tipo;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3interfaz.input.btnCancelarMouseListener;
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

	private JButton btnCancelar;
	private JButton btnConstruir;
	private JPanel panel;
	private JPanel botonera;
	private JLabel[] huecosLabel;
	private JButton btnConstruirEdificioRecolectorMineral;
	private JButton btnConstruirEdificioRecolectorGasVespeno;
	private JButton btnConstruirEdificioIncrementadorPoblacion;
	
	/* Precarga de imagenes */
	private static BufferedImage picConstruir = ImageLoader.loadImage("/textures/construir.png");
	private static BufferedImage picCancelar = ImageLoader.loadImage("/textures/cancelar.png");


	public HudVista(Jugador jugador, int anchoVentana, int altoVentana, JPanel panel){
		this.raza = jugador.getRaza();
		this.color = jugador.getColor();
		this.jugador = jugador;
		this.panel = panel; //Experimental
		this.mapaVisible = jugador.getMapa();
		
		this.anchoVentana = anchoVentana;
		this.altoVentana = altoVentana;
		this.setPreferredSize(new Dimension(640, 480));
		this.setFocusable(false);
		this.setVisible(false);
		this.setOpaque(false);
		
		setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 354));
		botonera = new JPanel(new InvisibleGridLayout(0, 3, 14, 8));
		botonera.setOpaque(false);
		add(botonera);
		
		
		/* Botones utiles inicialmente invisibles */
		
		btnConstruir = new JButton(new ImageIcon(picConstruir));
		btnConstruir.addMouseListener(new btnConstruirMouseListener(this));
		btnConstruir.setVisible(false);
		btnConstruir.setBorder(null);
		btnConstruir.setFocusPainted(false);
		btnConstruir.setContentAreaFilled(false);
		botonera.add(btnConstruir);
		
		
		// Edificios construibles
		btnConstruirEdificioRecolectorMineral = new JButton(new ImageIcon(
				CopyOfSpriteSheet.spritesTerran.get(jugador.getAtributos().getRecolectorMineral().getTipo().ordinal())));
		btnConstruirEdificioRecolectorMineral.setToolTipText(jugador.getAtributos().getRecolectorMineral().getNombre());
		btnConstruirEdificioRecolectorMineral.addMouseListener(new btnConstruirEdificioRecolectorMineralMouseListener(this, this.jugador));
		btnConstruirEdificioRecolectorMineral.setVisible(false);
		btnConstruirEdificioRecolectorMineral.setBorder(null);
		btnConstruirEdificioRecolectorMineral.setFocusPainted(false);
		btnConstruirEdificioRecolectorMineral.setContentAreaFilled(false);
		botonera.add(btnConstruirEdificioRecolectorMineral);
		
		btnConstruirEdificioRecolectorGasVespeno = new JButton(new ImageIcon(
				CopyOfSpriteSheet.spritesTerran.get(jugador.getAtributos().getRecolectorGasVespeno().getTipo().ordinal())));
		btnConstruirEdificioRecolectorGasVespeno.setToolTipText(jugador.getAtributos().getRecolectorGasVespeno().getNombre());
		btnConstruirEdificioRecolectorGasVespeno.addMouseListener(new btnConstruirEdificioRecolectorGasVespenoMouseListener(this, this.jugador));
		btnConstruirEdificioRecolectorGasVespeno.setVisible(false);
		btnConstruirEdificioRecolectorGasVespeno.setBorder(null);
		btnConstruirEdificioRecolectorGasVespeno.setFocusPainted(false);
		btnConstruirEdificioRecolectorGasVespeno.setContentAreaFilled(false);
		botonera.add(btnConstruirEdificioRecolectorGasVespeno);
		
		btnConstruirEdificioIncrementadorPoblacion = new JButton(new ImageIcon(
				CopyOfSpriteSheet.spritesTerran.get(jugador.getAtributos().getIncrementadorPoblacion().getTipo().ordinal())));
		btnConstruirEdificioIncrementadorPoblacion.setToolTipText(jugador.getAtributos().getIncrementadorPoblacion().getNombre());
		btnConstruirEdificioIncrementadorPoblacion.addMouseListener(new btnConstruirEdificioIncrementadorPoblacionMouseListener(this, this.jugador));
		btnConstruirEdificioIncrementadorPoblacion.setVisible(false);
		btnConstruirEdificioIncrementadorPoblacion.setBorder(null);
		btnConstruirEdificioIncrementadorPoblacion.setFocusPainted(false);
		btnConstruirEdificioIncrementadorPoblacion.setContentAreaFilled(false);
		botonera.add(btnConstruirEdificioIncrementadorPoblacion);
		
		// por ahora hasta ahi...
		
		/* Huecos para estilizar el layout */
		huecosLabel = new JLabel[9];
		for (int i=0; i<9; i++) {
			huecosLabel[i] = new JLabel();
			huecosLabel[i].setBorder(null);
			huecosLabel[i].setVisible(false);
			botonera.add(huecosLabel[i]);
		}
		
		btnCancelar = new JButton(new ImageIcon(picCancelar));
		btnCancelar.addMouseListener(new btnCancelarMouseListener(this));
		btnCancelar.setVisible(false);
		btnCancelar.setBorder(null);
		btnCancelar.setFocusPainted(false);
		btnCancelar.setContentAreaFilled(false);
		botonera.add(btnCancelar);
	}
	
	// Al mejor estilo VisualBasic
	private void msgbox(String s){
	   JOptionPane.showMessageDialog(null, s);
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g) {
		
		dibujarHUD(g);
		dibujarDatosJugador(g);
		//dibujarMinimapa(g); Tal vez en otra vista
		dibujarDatosCeldaSeleccionada(g);
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
	
	public void actualizarCeldaSeleccionada(Posicion nuevaCeldaSeleccionada) {
		this.celdaSeleccionada = nuevaCeldaSeleccionada;
		restablecerOpciones();
		mostrarOpcionesSegunCelda();
	}

	private void mostrarOpcionesSegunCelda() {
		Tipo tipo = mapaVisible.getOcupante(this.celdaSeleccionada).getTipo();
		if (tipo == Tipo.CELDA_VACIA || 
				tipo == Tipo.MINERAL ||
				tipo == Tipo.VESPENO) {
			btnConstruir.setVisible(true);
		}
		// if (tipoOcupante == TipoOcupante.RECURSO) .... es equivalente a los de arriba
	}

	public Posicion getCeldaSeleccionada() {
		return celdaSeleccionada;
	}

	public void mostrarOpcionesConstruccion() {
		btnConstruir.setVisible(false);
		btnConstruirEdificioRecolectorMineral.setVisible(true);
		btnConstruirEdificioRecolectorGasVespeno.setVisible(true);
		btnConstruirEdificioIncrementadorPoblacion.setVisible(true);
		for (int i=3; i<8; i++) {
			huecosLabel[i].setVisible(true);
		}
		btnCancelar.setVisible(true);
	}

	public void mostrarOpcionesColocacion() {
		
		// btnCancelar ?
	}

	public void mostrarMensaje(String mensaje) {
		msgbox(mensaje);
	}

	public void restablecerOpciones() {
		btnConstruirEdificioRecolectorMineral.setVisible(false);
		btnConstruirEdificioRecolectorGasVespeno.setVisible(false);
		btnConstruirEdificioIncrementadorPoblacion.setVisible(false);
		for (int i=0; i<9; i++) {
				huecosLabel[i].setVisible(false);
		}
		btnCancelar.setVisible(false);
		btnConstruir.setVisible(false);
		
		mostrarOpcionesSegunCelda();
	}
	
	@Override
	public void requestFocus() {
		this.panel.requestFocus();
	}
}
