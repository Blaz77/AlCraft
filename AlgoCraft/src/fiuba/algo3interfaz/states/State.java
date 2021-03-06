package fiuba.algo3interfaz.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import fiuba.algo3.excepciones.StateError;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3interfaz.Game;

public abstract class State implements MouseListener, MouseMotionListener, KeyListener{
	
	
	public  enum StateEnum {
		
		GAMESTATE (gameState),
		MENUSTATE (menuState);
		
		private State state;
		
		private StateEnum(State state){
			this.state = state;
		}
		
		public State getState(){
			return state;
		}
		
		public void setState(State state) {
			this.state = state;
		}
	}
	
	/** State Machine **/	

	protected static Game game;
	private static GameState gameState;
	private static MenuState menuState;
	private static LinkedList<State> stateStack = new LinkedList<State>();
	
	public static void setState(StateEnum stateEnum){
		State state = stateEnum.getState();
		// PArche feo
		if (state == null) state = gameState;
		
		game.getPanel().addKeyListener(state);
		game.getPanel().addMouseListener(state);
		game.getPanel().addMouseMotionListener(state);
		state.init();
		stateStack.add(state);
	}
	

	public static State getState(){
		return stateStack.getFirst();
	}
	
	public static void removeState(State state){
		if (stateStack.getLast() != state)
			throw new StateError();
		game.getPanel().removeKeyListener(state);
		game.getPanel().removeMouseListener(state);
		game.getPanel().removeMouseMotionListener(state);
		stateStack.pop();
	}
	

	public static void inicializar(Game _game){
		game = _game;
		menuState = new MenuState();
	}
	
	// Llamar luego de inicializar el modelo de juego
	public static void cambiarStateAJuego() {
		menuState.terminar();
		removeState(getState());
		
		gameState = new GameState();
		State.setState(StateEnum.GAMESTATE);
	}
		
	public State(){

	}
	public void init() {
		
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	// Implemento todos los attr de los eventListener asi 
	// si algun state no los usa no tiene q implementarlos
	
	/** Manejar Entrada del Usuario **/
	public void keyTyped(KeyEvent e) {		
	}

	public void keyPressed(KeyEvent e) {		
	}

	public void keyReleased(KeyEvent e) {		
	}

	public void mouseDragged(MouseEvent e) {		
	}

	public void mouseMoved(MouseEvent e) {	
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
		
}
