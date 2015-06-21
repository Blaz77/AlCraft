package fiuba.algo3interfaz.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.SwingUtilities;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3interfaz.gfx.HudVista;
import fiuba.algo3interfaz.gfx.MapaVista;
import fiuba.algo3interfaz.gfx.RecursoVista;

public class GameState extends State {

	private static final int MOV_CAMARA = 16;
	private HashMap<Jugador, MapaVista> mapas = new HashMap<Jugador, MapaVista>();
	private HashMap<Jugador, HudVista> huds = new HashMap<Jugador, HudVista>();
	private HashMap<Jugador, RecursoVista> recursos = new HashMap<Jugador, RecursoVista>();
	private MapaVista mapaActual;
	private HudVista hudActual;
	private RecursoVista recursoActual;
	private int i,j;
	private int iCamara,jCamara;

	private int previous_x;
	private int previous_y;


	
	public GameState(){
		
		Jugador jugador = game.getModelo().getJugador(1);
		mapas.put(jugador, new MapaVista(jugador.getMapa(), game.getPanel()));
		huds.put(jugador, new HudVista(jugador, game.getPanel()));
		recursos.put(jugador, new RecursoVista(jugador, game.getAncho(), game.getAlto(), game.getPanel()));
		game.getPanel().add(huds.get(jugador));
		game.getPanel().add(recursos.get(jugador));
		jugador = game.getModelo().getJugador(2);
		mapas.put(jugador, new MapaVista(jugador.getMapa(), game.getPanel()));
		huds.put(jugador, new HudVista(jugador, game.getPanel()));
		recursos.put(jugador, new RecursoVista(jugador, game.getAncho(), game.getAlto(), game.getPanel()));
		game.getPanel().add(huds.get(jugador));
		game.getPanel().add(recursos.get(jugador));
			
		mapaActual = mapas.get(game.getModelo().getJugadorActual());
		hudActual =  huds.get(game.getModelo().getJugadorActual());
		recursoActual = recursos.get(game.getModelo().getJugadorActual());
		hudActual.setVisible(true);
		recursoActual.setVisible(true);
	}
	
	@Override
	public void init() {
		game.getPanel().setBackground(java.awt.Color.BLACK);
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
		recursoActual.render(g);
	}

	public void mousePressed(MouseEvent e) {
		// TODO: Mejorar
				
	    if(SwingUtilities.isMiddleMouseButton(e)){
	    	this.previous_x = e.getX();
			this.previous_y = e.getY();

	    }
		
			mapaActual.setCeldaSeleccionada(e.getX(), e.getY());
			hudActual.actualizarCeldaSeleccionada(mapaActual.getPosicionCeldaSeleccionada());
		
		game.getPanel().repaint();
		//System.out.println("Pressed!");
	}

	public void mouseReleased(MouseEvent e) {
			mapaActual.setCeldaSeleccionada(e.getX(), e.getY());
			hudActual.actualizarCeldaSeleccionada(mapaActual.getPosicionCeldaSeleccionada());
		
		game.getPanel().repaint();
		//System.out.println("Released!");
	}

	
	
	
	public void mouseDragged(MouseEvent e) {
		
		
	    if(SwingUtilities.isMiddleMouseButton(e)){
	    	mapaActual.moverCamara((this.previous_x - e.getX()) * MOV_CAMARA, (this.previous_y - e.getY()) * MOV_CAMARA);
	    	this.previous_x = e.getX();
	    	this.previous_y = e.getY();

	    }
	    
		game.getPanel().repaint();
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
