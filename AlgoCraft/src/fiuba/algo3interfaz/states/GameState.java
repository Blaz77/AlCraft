package fiuba.algo3interfaz.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Random;

import javax.swing.SwingUtilities;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3interfaz.gfx.CopyOfSpriteSheet;
import fiuba.algo3interfaz.gfx.HudVista;
import fiuba.algo3interfaz.gfx.MapaVista;
import fiuba.algo3interfaz.gfx.MinimapaVista;
import fiuba.algo3interfaz.gfx.RecursoVista;
import fiuba.algo3interfaz.gfx.SpriteSheet;
import fiuba.algo3interfaz.music.MP3Player;

public class GameState extends State {

	private static final int MOV_CAMARA = 16;
	private HashMap<Jugador, MapaVista> mapas = new HashMap<Jugador, MapaVista>();
	private HashMap<Jugador, HudVista> huds = new HashMap<Jugador, HudVista>();
	private HashMap<Jugador, RecursoVista> recursos = new HashMap<Jugador, RecursoVista>();
	private HashMap<Jugador, MinimapaVista> minimapas = new HashMap<Jugador, MinimapaVista>();
	private MapaVista mapaActual;
	private HudVista hudActual;
	private RecursoVista recursoActual;
	private int i,j;
	private int iCamara,jCamara;

	private int previous_x;
	private int previous_y;
	private MinimapaVista minimapaActual;


	
	public GameState(){
		
		for (Jugador jugador : game.getModelo()){
			mapas.put(jugador, new MapaVista(jugador, game.getPanel()));
			minimapas.put(jugador, new MinimapaVista(jugador, game.getPanel(), mapas.get(jugador).getCamara()));
			huds.put(jugador, new HudVista(jugador, game.getPanel(), game));
			recursos.put(jugador, new RecursoVista(jugador, game.getAncho(), game.getAlto(), game.getPanel()));
			game.getPanel().add(huds.get(jugador));
			game.getPanel().add(recursos.get(jugador));
			game.getPanel().add(minimapas.get(jugador));
			CopyOfSpriteSheet.crearSpritesJugador(jugador);
		}
			
		mapaActual = mapas.get(game.getModelo().getJugadorActual());
		minimapaActual = minimapas.get(game.getModelo().getJugadorActual());
		hudActual =  huds.get(game.getModelo().getJugadorActual());
		recursoActual = recursos.get(game.getModelo().getJugadorActual());
		hudActual.setVisible(true);
		recursoActual.setVisible(true);
		minimapaActual.setVisible(true);
	}
	
	@Override
	public void init() {
		game.getPanel().setBackground(java.awt.Color.BLACK);
		Integer themeNumber = new Random().nextInt(3) + 1;
		MP3Player.play("terran" + themeNumber.toString() + ".mp3");
	}
	@Override
	public void tick() {
		if (mapaActual != mapas.get(game.getModelo().getJugadorActual())){
			
			this.init(); // EMI mi idea es q init corra 1 vez x cambio de jugador.

			minimapaActual.setVisible(false);
			hudActual.setVisible(false);
			recursoActual.setVisible(false);
			mapaActual = mapas.get(game.getModelo().getJugadorActual());
			hudActual = huds.get(game.getModelo().getJugadorActual());
			recursoActual = recursos.get(game.getModelo().getJugadorActual());
			minimapaActual.setVisible(true);
			hudActual.setVisible(true);
			recursoActual.setVisible(true);

		}
		mapaActual.tick();
		hudActual.tick();
		recursoActual.tick();
	}

	@Override
	public void render(Graphics g) {
		mapaActual.render(g);
		hudActual.render(g);
		minimapaActual.render(g);
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
