package fiuba.algo3interfaz;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import fiuba.algo3interfaz.states.GameState;
import fiuba.algo3interfaz.states.MenuState;
import fiuba.algo3interfaz.input.KeyManager;
import fiuba.algo3interfaz.input.MouseManager;
import fiuba.algo3interfaz.display.Display;
import fiuba.algo3interfaz.gfx.SpriteSheet;
import fiuba.algo3interfaz.states.State;

public class Game implements Runnable { // Permite correr un thread
	
	private Display display;
	public int anchoVentana, altoVentana;
	public String tituloVentana;
	
	private boolean running = false;
	private Thread thread;
	
	
	/** Atributos del render **/
	// Le dice a la pc como dibujar cosas en el buffer (creo?)
	private BufferStrategy bs;
	// Nuestro pincel magico :D
	private Graphics g;
	
	/** States **/
	private State gameState;
	private State menuState;
	
	/** Input **/
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	/** Constructor **/
	public Game(String tituloVentana, int anchoVentana, int altoVentana){
		this.anchoVentana = anchoVentana;
		this.altoVentana = altoVentana;
		this.tituloVentana = tituloVentana;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	// Solo se corre una vez esto, en el run.
	// Preparamos los graficos y eso
	private void inicializar(){
		display = new Display(tituloVentana, anchoVentana, altoVentana);
		
		display.getFrame().addKeyListener(keyManager);
		display.getCanvas().addMouseListener(mouseManager);

		SpriteSheet.inicializar();
		
		gameState = new GameState(this);
		menuState = new MenuState(this);
		State.setState(gameState);
		
	}
	
	// Tipico nombre para updatear todo en el game loop. Tambien llamado
	// "update"
	private void tick(){
		keyManager.tick();
		mouseManager.tick();
		
		if (State.getState() != null)
			State.getState().tick();
	}
	
	// Muestra todo de nuevo. Se supone que si actualizar no hizo nada,
	// esto deberia hacer lo mismo q en el instante anterior.
	private void render(){
		
		/** 1. Obtengo el buffer, y su Graphics. **/
		// Un 'buffer' es un banco de memoria auxiliar al q guarda lo q muestra la pantalla.
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3); // 3 = cantidad de buffers.
			return;
		}
		g = bs.getDrawGraphics(); // Obtenemos el graphics del buffer actual.

		/** 2. Limpio el buffer. **/
		// Limpia la parte de la pantalla marcada (en este caso todo)
		// Si no se hace esto, parpadea a lo loco.
		g.clearRect(0, 0, anchoVentana, altoVentana);
		
		
		/** 3. Dibujo el buffer con Graphics. */
		if (State.getState() != null)
			State.getState().render(g);
		
		/** 4. Muestro en pantalla y cierro el Graphics */
		bs.show(); // Hacemos que el buffer q modificamos pase a ser el q muestre en pantalla.
		g.dispose(); // Desinicializamos el graphics (no se xq, pero parece q es importante)
	}

	// drawRect dibuja un cuadrado sin relleno
	// fillRect dibuja un cuadrado con relleno del color elegido
	// setColor... bueno, eso
	public void rectangulosLindos() {
		g.drawRect(80, 80, 10, 10);
	}
	
	// Basico juego hermoso :D
	public void run(){
		
		inicializar();
		
		int fps = 60;
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
				tick(); 	// Actualizar variables
				render(); 	// Dibujarlas
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
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public  MouseManager getMouseManager(){
		return mouseManager;
	}
	
	
}


