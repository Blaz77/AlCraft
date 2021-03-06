package fiuba.algo3interfaz;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import fiuba.algo3.juego.Juego;
import fiuba.algo3.juego.Opciones;
import fiuba.algo3interfaz.states.State.StateEnum;
import fiuba.algo3interfaz.gfx.SpriteSheet;
import fiuba.algo3interfaz.states.State;

public class Game implements Runnable { // Permite correr un thread

	private static final int MIN_FPS = 1;
	private static final int MAX_FPS = 42;
	
	/** Atributos del Thread **/
	private boolean running = false;
	private Thread thread;

	/** Frame (el JPanel es si mismo y despues se a�ade) **/
	private JFrame frame = new JFrame("AlgoCraft");
	private JPanel panel = new JPanel() {
		
		private double timePerTick = 1000000000 / MAX_FPS;
		private double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		public void repaint(){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			if (delta >= 1){
				super.repaint();
				delta -= Math.floor(delta);
			}
		}

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			State.getState().render(g);
		}
	};
	
	int ancho;
	int alto;
	
	/** Modelo de juego armado segun el MenuState **/
	private Opciones opciones;
	private Juego modeloJuego;
	
	/** States **/
	
	/** Constructor **/
	public Game(int anchoVentana, int altoVentana){
		
		
		this.ancho = anchoVentana;
		this.alto = altoVentana;
		
		// Inicializar los datos requeridos
		SpriteSheet.inicializar();
		//MusicaAlgunDia.inicializar();
		State.inicializar(this);
		State.setState(StateEnum.MENUSTATE);

		// Inicializo el frame y el JPanel
		inicializarPanel(anchoVentana, altoVentana);
        inicializarFrame(anchoVentana, altoVentana);
	}


	public int getAncho() {
		return panel.getWidth();
	}


	public int getAlto() {
		return panel.getHeight();
	}


	private void inicializarPanel(int anchoVentana, int altoVentana) {
		panel.setDoubleBuffered(true);
        panel.setPreferredSize(new Dimension(anchoVentana, altoVentana));
		panel.setMaximumSize(new Dimension(anchoVentana, altoVentana));
		panel.setMinimumSize(new Dimension(anchoVentana, altoVentana));
		
		panel.setLayout(new OverlayLayout(panel));
		panel.setFocusable(true);
	}


	private void inicializarFrame(int anchoVentana, int altoVentana) {
		frame.setResizable(true); // o true quien sabe
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(anchoVentana, altoVentana);
		frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
	}
	
	// Basico juego hermoso :D
	public void run(){

	}
	
	public synchronized void start(){
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start(); // Llama a this.run()
	}
	
	public synchronized void stop(){
		if (! running)
			return;
		running = false;
		try {
			thread.join(); // Mata este thread
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
    public void update(long elapsedTime){
    }
    public void tick(){
    	modeloJuego.nextJugadorActual();
    	State.getState().tick();
    }
   
   /* public void addKeyListener(KeyListener keyListener){
    	frame.addKeyListener(keyListener);
    }*/
    
    public Juego getModelo(){
    	return modeloJuego;
    }
    
    public Opciones getOpciones(){
    	return opciones;
	}


	public void comenzarJuego(Opciones opciones) {
		this.opciones = opciones;
		this.modeloJuego = new Juego(this.opciones);
		
		State.cambiarStateAJuego();
	}


	public JPanel getPanel() {
		return panel;
	}


	public JFrame getFrame() {
		return frame;
	}
}