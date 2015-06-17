package fiuba.algo3interfaz.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import fiuba.algo3.juego.Juego;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.juego.Opciones;
import fiuba.algo3interfaz.Game;
import fiuba.algo3interfaz.gfx.MapaVista;

public class GameState extends State {

	private MapaVista mapaActual;
	private HashMap<Jugador, MapaVista> mapas = new HashMap<Jugador, MapaVista>();
	private int j;
	private int i;
	
	public GameState(){
		
		for (Jugador jugador: game.getModelo())
			mapas.put(jugador, new MapaVista(jugador.getMapa()));
		
		mapaActual = mapas.get(game.getModelo().getJugadorActual());
	}
	
	@Override
	public void tick() {
		mapaActual = mapas.get(game.getModelo().getJugadorActual());
		mapaActual.tick();		
	}

	@Override
	public void render(Graphics g) {
		mapaActual.render(g);
	}

	public void mousePressed(MouseEvent e) {
		mapaActual.setCeldaSeleccionada(e.getX(), e.getY());
		//System.out.println("Pressed!");
	}

	public void mouseReleased(MouseEvent e) {
		mapaActual.setCeldaSeleccionada(e.getX(), e.getY());
		//System.out.println("Released!");
	}

	public void mouseDragged(MouseEvent e) {
		mapaActual.setCeldaSeleccionada(e.getX(), e.getY());
		//System.out.println("Dragged!");
	}

	/** Key Pressings **/
	
	public void keyPressed(KeyEvent e) {
		int keyPressed = e.getKeyCode();
		
		if(keyPressed == KeyEvent.VK_W)	j = -1;
		if(keyPressed == KeyEvent.VK_S) j = 1;
		if(keyPressed == KeyEvent.VK_A)	i = -1;
		if(keyPressed == KeyEvent.VK_D)i = 1;
			
		mapaActual.moverCeldaSeleccionada(i, j);

	}

	public void keyReleased(KeyEvent e) {
		int keyPressed = e.getKeyCode();

		
		if(keyPressed == KeyEvent.VK_W || keyPressed == KeyEvent.VK_S) j = 0;
		if(keyPressed == KeyEvent.VK_A || keyPressed == KeyEvent.VK_D) i = 0;
		
	}

}
