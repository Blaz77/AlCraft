package fiuba.algo3interfaz;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fiuba.algo3.juego.Juego;
import fiuba.algo3.juego.Opciones;
import fiuba.algo3interfaz.states.State.StateEnum;
import fiuba.algo3interfaz.gfx.SpriteSheet;
import fiuba.algo3interfaz.states.State;

public class Game extends JPanel implements Runnable { // Permite correr un thread

	private static final int FRAMES_PER_SECOND = 60;
	
	/** Atributos del Thread **/
	private boolean running = false;
	private Thread thread;

	/** Frame (el JPanel es si mismo y despues se añade) **/
	private JFrame frame = new JFrame("AlgoCraft");
	
	private Opciones opciones = new Opciones();
	
	private Juego modeloJuego = new Juego(getOpciones());
	/** States **/
	
	/** Constructor **/
	public Game(int anchoVentana, int altoVentana){
			
		// Inicializar los datos requeridos
		SpriteSheet.inicializar();
		//MusicaAlgunDia.inicializar();
		State.inicializar(this);
		State.setState(StateEnum.GAMESTATE);

		// Inicializo el frame y el JPanel
		inicializarPanel(anchoVentana, altoVentana);
        inicializarFrame(anchoVentana, altoVentana);
	}


	private void inicializarPanel(int anchoVentana, int altoVentana) {
		setDoubleBuffered(true);
        setPreferredSize(new Dimension(anchoVentana, altoVentana));
		setMaximumSize(new Dimension(anchoVentana, altoVentana));
		setMinimumSize(new Dimension(anchoVentana, altoVentana));
	}


	private void inicializarFrame(int anchoVentana, int altoVentana) {
		frame.setResizable(false); // o true quien sabe
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(anchoVentana, altoVentana);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
	}
	
	// Basico juego hermoso :D
	public void run(){
				
		int fps = FRAMES_PER_SECOND;
		double timePerTick = 1000000000 / fps; // 1 second = 1000000000 nanoseconds
		double delta = 0; // whut.
		long now;
		long lastTime = System.nanoTime(); // tiempo actual de la pc en nanos
		
		// Game loop basico:
		while(running){
			
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			if (delta >= 1){
				repaint();
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
		if (State.getState() != null)
			State.getState().tick();
    }
	
    public void paint(Graphics g){
    	State.getState().render(g);
    }
   
    public void addKeyListener(KeyListener keyListener){
    	frame.addKeyListener(keyListener);
    }
    
    public Juego getModelo(){
    	return modeloJuego;
    }
    
    public Opciones getOpciones(){
    	return opciones;
	}
}