package fiuba.algo3interfaz.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3interfaz.gfx.HUD;
import fiuba.algo3interfaz.gfx.MapaVista;

public class GameState extends State {

	private static final int MOV_CAMARA = 10;
	private MapaVista mapaActual;
	private HashMap<Jugador, MapaVista> mapas = new HashMap<Jugador, MapaVista>();
	private int i,j;
	private int iCamara,jCamara;
	private HashMap<Jugador, HUD> huds = new HashMap<Jugador, HUD>();
	private HUD hudActual;
	

	
	public GameState(){
		
		for (Jugador jugador: game.getModelo()) {
			mapas.put(jugador, new MapaVista(jugador.getMapa(), game.getAncho(), game.getAlto() - 100));
			huds.put(jugador, new HUD(jugador, game.getAncho(), game.getAlto()));
		}
			
		mapaActual = mapas.get(game.getModelo().getJugadorActual());
		this.hudActual =  huds.get(game.getModelo().getJugadorActual());
	}
	
	@Override
	public void tick() {
		mapaActual = mapas.get(game.getModelo().getJugadorActual());
		hudActual = huds.get(game.getModelo().getJugadorActual());
		mapaActual.tick();
		hudActual.tick();
	}

	@Override
	public void render(Graphics g) {
		mapaActual.render(g);
		hudActual.render(g);
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
		
				
		if(keyPressed == KeyEvent.VK_W)j = -1;
		if(keyPressed == KeyEvent.VK_S)j = 1;
		if(keyPressed == KeyEvent.VK_A)i = -1;
		if(keyPressed == KeyEvent.VK_D)i = 1;
		
		if(keyPressed == KeyEvent.VK_UP)jCamara = -MOV_CAMARA;
		if(keyPressed == KeyEvent.VK_DOWN)jCamara = MOV_CAMARA;
		if(keyPressed == KeyEvent.VK_LEFT)iCamara = -MOV_CAMARA;
		if(keyPressed == KeyEvent.VK_RIGHT)iCamara = MOV_CAMARA;
		
		mapaActual.moverCeldaSeleccionada(i, j);
		mapaActual.moverCamara(iCamara, jCamara);

	}

	public void keyReleased(KeyEvent e) {
		int keyReleased = e.getKeyCode();
		
		j = 0;
		i = 0;
		
		if(keyReleased == KeyEvent.VK_UP)jCamara = 0;
		if(keyReleased == KeyEvent.VK_DOWN)jCamara = 0;
		if(keyReleased == KeyEvent.VK_LEFT)iCamara = 0;
		if(keyReleased == KeyEvent.VK_RIGHT)iCamara = 0;
	}

}
