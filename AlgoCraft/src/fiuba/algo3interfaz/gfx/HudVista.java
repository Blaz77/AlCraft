package fiuba.algo3interfaz.gfx;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
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
import fiuba.algo3.juego.Opciones;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.TipoOcupante;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3interfaz.Game;
import fiuba.algo3interfaz.input.btnCancelarMouseListener;
import fiuba.algo3interfaz.input.btnConstruirEdificioEntrenadorUnidadesBasicasMouseListener;
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

	private JButton btnCancelar;
	private JButton btnConstruir;
	private JPanel panel;
	private JPanel botonera;
	private JLabel[] huecosLabel;
	private JButton btnConstruirEdificioRecolectorMineral;
	private JButton btnConstruirEdificioRecolectorGasVespeno;
	private JButton btnConstruirEdificioIncrementadorPoblacion;
	private JButton btnConstruirEdificioEntrenadorUnidadesBasicas;
	private JPanel ubicadorBotonera;
	private JPanel centrante;
	private JButton btnPasarTurno;
	private Game game;
	private JPanel ubicadorPasarTurno;
	private JPanel espaciadorIzquierdo;
	private JPanel espaciadorSuperior;
	private JPanel controlCentral;
	
	/* Precarga de imagenes */
	private static BufferedImage hudTest = ImageLoader.loadImage("/HUDs/HUDterran2.png"); //HUDzerg2.png, HUDprotoss2.png

	private static BufferedImage picConstruir = ImageLoader.loadImage("/textures/construir.png");
	private static BufferedImage picCancelar = ImageLoader.loadImage("/textures/cancelar.png");

	
	public static final int ANCHO = hudTest.getWidth();
	public static final int ALTO = hudTest.getHeight();
	
	public HudVista(Jugador jugador, JPanel panel, Game game){
		this.raza = jugador.getRaza();
		this.color = jugador.getColor();
		this.jugador = jugador;
		this.panel = panel; //Experimental
		this.game = game;
		this.mapaVisible = jugador.getMapa();
		
		this.setFocusable(false);
		this.setVisible(false);
		this.setOpaque(false);
		
		setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(hudTest.getWidth(), hudTest.getHeight()));
		centrante = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		//centrante.setBackground(new java.awt.Color(255, 255, 255, 50));
		centrante.setOpaque(false);
		add(centrante, java.awt.BorderLayout.SOUTH);
		
		espaciadorIzquierdo = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		espaciadorIzquierdo.setPreferredSize(new Dimension(hudTest.getWidth() / 4, hudTest.getHeight()));
		//espaciadorIzquierdo.setBackground(new java.awt.Color(255, 255, 255, 100));
		espaciadorIzquierdo.addMouseListener(new MouseAdapter() {} );
		espaciadorIzquierdo.setOpaque(false);
		centrante.add(espaciadorIzquierdo);
		
		controlCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		controlCentral.setPreferredSize(new Dimension(hudTest.getWidth() / 2, hudTest.getHeight()));
		//controlCentral.setBackground(new java.awt.Color(255, 255, 255, 20));
		controlCentral.setOpaque(false);
		centrante.add(controlCentral);

		espaciadorSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		espaciadorSuperior.setPreferredSize(new Dimension(hudTest.getWidth() / 2, 50));
		//espaciadorSuperior.setBackground(new java.awt.Color(255, 255, 255, 100));
		espaciadorSuperior.setOpaque(false);
		controlCentral.add(espaciadorSuperior);
		
		ubicadorPasarTurno = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		ubicadorPasarTurno.setPreferredSize(new Dimension(hudTest.getWidth() / 2, hudTest.getHeight() - 50));
		//ubicadorPasarTurno.setBackground(new java.awt.Color(255, 255, 255, 150));
		ubicadorPasarTurno.addMouseListener(new MouseAdapter() {} );
		ubicadorPasarTurno.setOpaque(false);
		controlCentral.add(ubicadorPasarTurno);
		
		
		ubicadorBotonera = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 70));
		ubicadorBotonera.setPreferredSize(new Dimension(hudTest.getWidth() / 4, hudTest.getHeight()));
		//ubicadorBotonera.setBackground(new java.awt.Color(255, 255, 255, 50));
		ubicadorBotonera.setOpaque(false);
		ubicadorBotonera.addMouseListener(new MouseAdapter() {} );
		centrante.add(ubicadorBotonera);
		
		botonera = new JPanel(new InvisibleGridLayout(0, 3, 14, 8));
		botonera.setOpaque(false);
		ubicadorBotonera.add(botonera);
		
		btnPasarTurno = new JButton("PASAR TURNO");
		btnPasarTurno.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPasarTurnoActionPerformed(evt);
			}
		});
		ubicadorPasarTurno.add(btnPasarTurno);
		
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
		
		btnConstruirEdificioEntrenadorUnidadesBasicas = new JButton(new ImageIcon(
				CopyOfSpriteSheet.spritesTerran.get(jugador.getAtributos().getEntrenadorUnidadesBasicas().getTipo().ordinal())));
		btnConstruirEdificioEntrenadorUnidadesBasicas.setToolTipText(jugador.getAtributos().getEntrenadorUnidadesBasicas().getNombre());
		btnConstruirEdificioEntrenadorUnidadesBasicas.addMouseListener(new btnConstruirEdificioEntrenadorUnidadesBasicasMouseListener(this, this.jugador));
		btnConstruirEdificioEntrenadorUnidadesBasicas.setVisible(false);
		btnConstruirEdificioEntrenadorUnidadesBasicas.setBorder(null);
		btnConstruirEdificioEntrenadorUnidadesBasicas.setFocusPainted(false);
		btnConstruirEdificioEntrenadorUnidadesBasicas.setContentAreaFilled(false);
		botonera.add(btnConstruirEdificioEntrenadorUnidadesBasicas);
		
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
	
	private void btnPasarTurnoActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	this.game.tick();
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
	
	private void dibujarHUD(Graphics g) {
		
		g.drawImage(hudTest, (panel.getWidth() - hudTest.getWidth())/2, panel.getHeight() - hudTest.getHeight(), null);
		
	}
	
	private void dibujarDatosJugador(Graphics g) {

		g.setFont(new Font("SansSerif", Font.PLAIN, 36));
		g.setColor(java.awt.Color.WHITE);
		g.drawString(this.jugador.getNombre(), 50, 36);
		
	}
	
	private void dibujarDatosCeldaSeleccionada(Graphics g) {
		g.setFont(new Font("Serif", Font.PLAIN, 18));
		g.setColor(java.awt.Color.WHITE);
		
		final int ORIGEN_X = panel.getWidth() / 2 - 150;
		final int ORIGEN_Y = panel.getHeight() - 75;
		final int ANCHO_VIDA = 50;
		
		g.drawString(this.mapaVisible.getOcupante(celdaSeleccionada).getTipo().getNombre(), ORIGEN_X, ORIGEN_Y);
		if (this.mapaVisible.getOcupante(celdaSeleccionada).getTipo().getTipoOcupante() == TipoOcupante.EDIFICIO ||
				this.mapaVisible.getOcupante(celdaSeleccionada).getTipo().getTipoOcupante() == TipoOcupante.UNIDAD) {
			ObjetoVivo entidad = (ObjetoVivo) this.mapaVisible.getOcupante(celdaSeleccionada);
			
			g.fillRect(ORIGEN_X, ORIGEN_Y + 10, ANCHO_VIDA, 10);
			g.setColor(java.awt.Color.GREEN);
			g.fillRect(ORIGEN_X, ORIGEN_Y + 10, entidad.getVida() / entidad.getVidaMaxima() * ANCHO_VIDA, 10);
		}
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
		btnConstruirEdificioEntrenadorUnidadesBasicas.setVisible(true);
		for (int i=4; i<8; i++) {
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
		btnConstruirEdificioEntrenadorUnidadesBasicas.setVisible(false);
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
