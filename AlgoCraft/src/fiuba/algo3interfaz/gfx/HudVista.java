package fiuba.algo3interfaz.gfx;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.TipoOcupante;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3interfaz.Game;
import fiuba.algo3interfaz.input.BotonBotonera;
import fiuba.algo3interfaz.input.UtilizadorDeCeldas;
import fiuba.algo3interfaz.input.btnAtacar;
import fiuba.algo3interfaz.input.btnCancelarMouseListener;
import fiuba.algo3interfaz.input.btnConstruirEdificioEntrenadorUnidadesBasicasMouseListener;
import fiuba.algo3interfaz.input.btnConstruirEdificioIncrementadorPoblacionMouseListener;
import fiuba.algo3interfaz.input.btnConstruirEdificioRecolectorGasVespenoMouseListener;
import fiuba.algo3interfaz.input.btnConstruirEdificioRecolectorMineralMouseListener;
import fiuba.algo3interfaz.input.btnConstruirMouseListener;
import fiuba.algo3interfaz.input.btnEntrenar;


public class HudVista extends JPanel implements UtilizadorDeCeldas {
	
	private TipoRaza raza;
	private Color color;
	private Jugador jugador; // dale que va, esto ya es cualquier cosa
	private Mapa mapaVisible;
	private Posicion celdaSeleccionada = new Posicion(0,0);
	private BufferedImage hudImage;

	private BotonBotonera btnCancelar;
	private BotonBotonera btnConstruir;
	private JPanel panel;
	private Botonera botoneraActual;
	private Botonera botoneraConstrucciones;
	private Botonera botoneraCeldaVacia;
	private JLabel[] huecosLabel;
	private BotonBotonera btnConstruirEdificioRecolectorMineral;
	private BotonBotonera btnConstruirEdificioRecolectorGasVespeno;
	private BotonBotonera btnConstruirEdificioIncrementadorPoblacion;
	private BotonBotonera btnConstruirEdificioEntrenadorUnidadesBasicas;
	private JPanel ubicadorBotonera;
	private JPanel centrante;
	private JButton btnPasarTurno;
	private Game game;
	private JPanel ubicadorPasarTurno;
	private JPanel espaciadorIzquierdo;
	private JPanel espaciadorSuperior;
	private JPanel controlCentral;
	
	private UtilizadorDeCeldas utilizadorDeCeldasActual = this;
	private HashMap<Tipo, Botonera> botoneras;
	private Botonera botoneraNull;
	private Botonera botoneraEdificioEntrenador;
	
	/* Precarga de imagenes */ //ImageLoader.loadImage("/HUDs/HUDterran2.png"); //HUDzerg2.png, HUDprotoss2.png
	private static HashMap<TipoRaza, BufferedImage> huds = new HashMap<TipoRaza, BufferedImage>();

	public static BufferedImage picConstruir = ImageLoader.loadImage("/textures/construir.png");
	public static BufferedImage picCancelar = ImageLoader.loadImage("/textures/cancelar.png");
	//public static BufferedImage picEntrenar = ImageLoader.loadImage("/textures/entrenar.png");
	public static BufferedImage picEntrenar = ImageLoader.loadImage("/textures/terran.png");
	
	public static final int ANCHO = 640;
	public static final int ALTO = 192;
	
	public HudVista(Jugador jugador, JPanel panel, Game game){
		this.raza = jugador.getRaza();
		this.color = jugador.getColor();
		this.jugador = jugador;
		this.panel = panel; //Experimental
		this.game = game;
		this.mapaVisible = jugador.getMapa();
		
		this.hudImage = loadHUD(this.raza);//ImageLoader.loadImage("/HUDs/HUDterran2.png");
		
		this.setFocusable(false);
		this.setVisible(false);
		this.setOpaque(false);
		
		setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(hudImage.getWidth(), hudImage.getHeight()));
		centrante = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		//centrante.setBackground(new java.awt.Color(255, 255, 255, 50));
		centrante.setOpaque(false);
		add(centrante, java.awt.BorderLayout.SOUTH);
		
		espaciadorIzquierdo = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		espaciadorIzquierdo.setPreferredSize(new Dimension(hudImage.getWidth() / 4, hudImage.getHeight()));
		//espaciadorIzquierdo.setBackground(new java.awt.Color(255, 255, 255, 100));
		espaciadorIzquierdo.addMouseListener(new MouseAdapter() {} );
		espaciadorIzquierdo.setOpaque(false);
		centrante.add(espaciadorIzquierdo);
		
		controlCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		controlCentral.setPreferredSize(new Dimension(hudImage.getWidth() / 2, hudImage.getHeight()));
		//controlCentral.setBackground(new java.awt.Color(255, 255, 255, 20));
		controlCentral.setOpaque(false);
		centrante.add(controlCentral);

		espaciadorSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		espaciadorSuperior.setPreferredSize(new Dimension(hudImage.getWidth() / 2, 50));
		//espaciadorSuperior.setBackground(new java.awt.Color(255, 255, 255, 100));
		espaciadorSuperior.setOpaque(false);
		controlCentral.add(espaciadorSuperior);
		
		ubicadorPasarTurno = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		ubicadorPasarTurno.setPreferredSize(new Dimension(hudImage.getWidth() / 2, hudImage.getHeight() - 50));
		//ubicadorPasarTurno.setBackground(new java.awt.Color(255, 255, 255, 150));
		ubicadorPasarTurno.addMouseListener(new MouseAdapter() {} );
		ubicadorPasarTurno.setOpaque(false);
		controlCentral.add(ubicadorPasarTurno);
		
		
		ubicadorBotonera = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 70));
		ubicadorBotonera.setPreferredSize(new Dimension(hudImage.getWidth() / 4, hudImage.getHeight()));
		//ubicadorBotonera.setBackground(new java.awt.Color(255, 255, 255, 50));
		ubicadorBotonera.setOpaque(false);
		ubicadorBotonera.addMouseListener(new MouseAdapter() {} );
		centrante.add(ubicadorBotonera);
		
		/*botonera = new JPanel(new InvisibleGridLayout(0, 3, 14, 8));
		botonera.setOpaque(false);
		ubicadorBotonera.add(botonera);*/
		
		btnPasarTurno = new JButton("PASAR TURNO");
		btnPasarTurno.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPasarTurnoActionPerformed(evt);
			}
		});
		ubicadorPasarTurno.add(btnPasarTurno);
		
		// Botoneras:
		this.botoneraNull = new Botonera();
		this.botoneraCeldaVacia = new Botonera(new btnConstruirMouseListener(this, this.jugador, picConstruir),
				new btnAtacar(this, ImageLoader.loadImage("/textures/terran.png")));
		
		this.botoneraEdificioEntrenador = new Botonera(new btnEntrenar(this, this.jugador, picEntrenar));
		
		this.botoneras = new HashMap<Tipo, Botonera>();
		
		for (Tipo tipo : new Tipo[]{Tipo.CELDA_VACIA, Tipo.MINERAL, Tipo.VESPENO}) {
			this.botoneras.put(tipo, botoneraCeldaVacia);
		}
		
		for (Tipo tipo : new Tipo[]{Tipo.BARRACA, Tipo.FABRICA, Tipo.PUERTO_ESTELAR_TERRAN}){
			this.botoneras.put(tipo, botoneraEdificioEntrenador);
		}
		
		
		
	}
	
	private BufferedImage loadHUD(TipoRaza raza) {
		if ( ! huds.containsKey(raza))
			huds.put(raza, ImageLoader.loadImage("/HUDs/HUD" + raza.toString().toLowerCase() +"2.png"));
		return huds.get(raza);
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
		
		g.drawImage(hudImage, (panel.getWidth() - hudImage.getWidth())/2, panel.getHeight() - hudImage.getHeight(), null);
		
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
		this.utilizadorDeCeldasActual.actualizar();	
	}
	
	public void setUtilizadorDeCeldas(UtilizadorDeCeldas util){
		this.utilizadorDeCeldasActual = util;
	}
	
	public void actualizar(){
		mostrarOpcionesSegunCelda();
	}

	private void mostrarOpcionesSegunCelda() {
		Tipo tipo = mapaVisible.getOcupante(this.celdaSeleccionada).getTipo();
		setBotonera(botoneras.getOrDefault(tipo, botoneraNull));
	}

	public Posicion getCeldaSeleccionada() {
		return celdaSeleccionada;
	}

	public void setBotonera(Botonera nuevaBotonera) {
		ubicadorBotonera.removeAll();
		ubicadorBotonera.add(nuevaBotonera);
		ubicadorBotonera.revalidate(); //validate?
	}

	public void mostrarOpcionesColocacion() {
		
		// btnCancelar ?
	}

	public void mostrarMensaje(String mensaje) {
		msgbox(mensaje);
	}

	public void restablecerOpciones() {
		this.utilizadorDeCeldasActual = this;
		mostrarOpcionesSegunCelda();
	}
	
	@Override
	public void requestFocus() {
		this.panel.requestFocus();
	}
}
