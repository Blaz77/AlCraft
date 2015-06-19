package fiuba.algo3interfaz.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3interfaz.gfx.HudVista;
import fiuba.algo3interfaz.gfx.MapaVista;

public class GameState extends State {
	final int HUD_INICIO_Y = 360;

	private static final int MOV_CAMARA = 50;
	private MapaVista mapaActual;
	private HashMap<Jugador, MapaVista> mapas = new HashMap<Jugador, MapaVista>();
	private int i,j;
	private int iCamara,jCamara;
	private HashMap<Jugador, HudVista> huds = new HashMap<Jugador, HudVista>();
	private HudVista hudActual;
	

	
	public GameState(){
		
		Jugador jugador = game.getModelo().getJugador(1);
		mapas.put(jugador, new MapaVista(jugador.getMapa(), game.getPanel()));
		huds.put(jugador, new HudVista(jugador, game.getAncho(), game.getAlto(), game.getPanel()));
		game.getPanel().add(huds.get(jugador));
		jugador = game.getModelo().getJugador(2);
		mapas.put(jugador, new MapaVista(jugador.getMapa(), game.getPanel()));
		huds.put(jugador, new HudVista(jugador, game.getAncho(), game.getAlto(), game.getPanel()));
		game.getPanel().add(huds.get(jugador));
			
		mapaActual = mapas.get(game.getModelo().getJugadorActual());
		hudActual =  huds.get(game.getModelo().getJugadorActual());
		hudActual.setVisible(true);
	}
	
	@Override
	public void tick() {
		hudActual.setVisible(false);
		mapaActual = mapas.get(game.getModelo().getJugadorActual());
		hudActual = huds.get(game.getModelo().getJugadorActual());
		hudActual.setVisible(true);
		mapaActual.tick();
		hudActual.tick();
	}

	@Override
	public void render(Graphics g) {
		mapaActual.render(g);
		hudActual.render(g);
	}

	public void mousePressed(MouseEvent e) {
		// TODO: Mejorar
		if (e.getY() < HUD_INICIO_Y) {
			mapaActual.setCeldaSeleccionada(e.getX(), e.getY());
			hudActual.actualizar(mapaActual.getPosicionCeldaSeleccionada());
		}
		else {
			hudActual.recibirClick(e.getX(), e.getY());
		}
		game.getPanel().repaint();
		//System.out.println("Pressed!");
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getY() < HUD_INICIO_Y) {
			mapaActual.setCeldaSeleccionada(e.getX(), e.getY());
			hudActual.actualizar(mapaActual.getPosicionCeldaSeleccionada());
		}
		game.getPanel().repaint();
		//System.out.println("Released!");
	}

	public void mouseDragged(MouseEvent e) {
		//mapaActual.setCeldaSeleccionada(e.getX(), e.getY());
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

		game.getPanel().repaint();
	}

	public void keyReleased(KeyEvent e) {
		int keyReleased = e.getKeyCode();
		
		j = 0;
		i = 0;
		
		if(keyReleased == KeyEvent.VK_UP)jCamara = 0;
		if(keyReleased == KeyEvent.VK_DOWN)jCamara = 0;
		if(keyReleased == KeyEvent.VK_LEFT)iCamara = 0;
		if(keyReleased == KeyEvent.VK_RIGHT)iCamara = 0;
		
		game.getPanel().repaint();
	}

}
