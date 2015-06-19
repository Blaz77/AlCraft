package fiuba.algo3interfaz;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fiuba.algo3.juego.Juego;
import fiuba.algo3.juego.Opciones;
import fiuba.algo3interfaz.states.State.StateEnum;
import fiuba.algo3interfaz.gfx.SpriteSheet;
import fiuba.algo3interfaz.states.State;

public class Game implements Runnable { // Permite correr un thread

	private static final int FRAMES_PER_SECOND = 60;
	
	/** Atributos del Thread **/
	private boolean running = false;
	private Thread thread;

	/** Frame (el JPanel es si mismo y despues se añade) **/
	private JFrame frame = new JFrame("AlgoCraft");
	private JPanel panel = new JPanel() {
		public void paint(Graphics g) {
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
		
		// Inicializo el frame y el JPanel
		inicializarPanel(anchoVentana, altoVentana);
        inicializarFrame(anchoVentana, altoVentana);
        
		// Inicializar los datos requeridos
		SpriteSheet.inicializar();
		//MusicaAlgunDia.inicializar();
		State.inicializar(this);
		State.setState(StateEnum.MENUSTATE);

        
        frame.setVisible(true);
	}


	public int getAncho() {
		
		return panel.getWidth();
	}


	public int getAlto() {
		
		return panel.getHeight();
	}


	private void inicializarPanel(int anchoVentana, int altoVentana) {
		panel.setDoubleBuffered(true);
		panel.setSize(anchoVentana, altoVentana);
		panel.setPreferredSize(new Dimension(anchoVentana, altoVentana));
		panel.setMaximumSize(new Dimension(anchoVentana, altoVentana));
		panel.setMinimumSize(new Dimension(anchoVentana, altoVentana));
		
		panel.setFocusable(true);
	}


	private void inicializarFrame(int anchoVentana, int altoVentana) {
		frame.setResizable(true); // o true quien sabe
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(anchoVentana, altoVentana);
		frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.pack();
	}
	
	// Basico juego hermoso :D
	public void run(){
				
		int fps = FRAMES_PER_SECOND;
		double timePerTick = 1000000000 / fps; // 1 second = 1000000000 nanoseconds
		double delta = 0; // whut.
		long now;
		long lastTime = System.nanoTime(); // tiempo actual de la pc en nanos
		
		tick();
		panel.repaint();
		// Game loop basico:
		while(running){
			
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			if (delta >= 1){
				tick();
				panel.repaint();
				delta --;
			}
			
		}
		stop();
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
    	State.getState().tick();
    }
    public void paintComponent(Graphics g){
		g.clearRect(0, 0, ancho, alto);
    	State.getState().render(g);
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


<<<<<<< HEAD
	public void comenzarJuego(Opciones opciones) {
		this.opciones = opciones;
		this.modeloJuego = new Juego(this.opciones);
		
		State.cambiarStateAJuego();
=======
	public void addKeyListener(KeyListener listener) {
		panel.addKeyListener(listener);
		
	}


	public void addMouseListener(MouseListener listener) {
		panel.addMouseListener(listener);
		
	}


	public void addMouseMotionListener(MouseMotionListener listener) {
		panel.addMouseMotionListener(listener);
		
	}


	public void removeKeyListener(KeyListener listener) {
		panel.removeKeyListener(listener);
		
	}


	public void removeMouseListener(MouseListener listener) {
		panel.removeMouseListener(listener);
		
	}


	public void removeMouseMotionListener(MouseMotionListener listener) {
		panel.removeMouseMotionListener(listener);
		
	}


	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return panel;
>>>>>>> branch-xq-la-cague
	}
}