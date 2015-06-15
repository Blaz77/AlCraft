package fiuba.algo3interfaz;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import fiuba.algo3interfaz.display.Display;
import fiuba.algo3interfaz.gfx.ImageLoader;
import fiuba.algo3interfaz.gfx.MapaVista;
import fiuba.algo3interfaz.gfx.SpriteSheet;

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
	
	/** Atributos para cargar imagenes/sprites **/
	private BufferedImage test;
	private MapaVista mapaVista;
	
	/** Constructor **/
	public Game(String tituloVentana, int anchoVentana, int altoVentana){
		this.anchoVentana = anchoVentana;
		this.altoVentana = altoVentana;
		this.tituloVentana = tituloVentana;
	}
	
	// Solo se corre una vez esto, en el run.
	// Preparamos los graficos y eso
	private void inicializar(){
		
		mapaVista = new MapaVista(10, 10);
		display = new Display(tituloVentana, anchoVentana, altoVentana);
		test = ImageLoader.loadImage("/textures/tierra.png");
		new SpriteSheet(test);
		
	}
	
	// Tipico nombre para updatear todo en el game loop. Tambien llamado
	// "update"
	private void actualizar(){

	}
	
	// Muestra todo de nuevo. Se supone que si actualizar no hizo nada,
	// esto deberia hacer lo mismo q en el instante anterior.
	private void dibujar(){
		
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
		dibujarMapaStarcraft();
		
		/** 4. Muestro en pantalla y cierro el Graphics */
		bs.show(); // Hacemos que el buffer q modificamos pase a ser el q muestre en pantalla.
		g.dispose(); // Desinicializamos el graphics (no se xq, pero parece q es importante)
	}

	private void dibujarMapaStarcraft() {
		for (int y = 0; y < 10; y++)
			for (int x = 0; x < 10; x++){
				// Dibujo el en la celda x,y el sprite indicado en mapaGrafico[x][y]
				g.drawImage(mapaVista.obtenerSprite(x, y), 5+32*x, 5+32*y, null);
			}
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
		
		// Game loop basico:
		while(running){
			actualizar(); 	// Actualizar variables
			dibujar(); 	// Dibujarlas
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
}


