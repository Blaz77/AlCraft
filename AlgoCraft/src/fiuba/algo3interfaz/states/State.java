package fiuba.algo3interfaz.states;

import java.awt.Graphics;

import fiuba.algo3interfaz.Game;

public abstract class State {
	
	//GAME STATE MANAGER
	
	private static State currentState = null;
	
	public static void setState(State state){
		currentState = state;
	}
	
	public static State getState(){
		return currentState;
	}
	
	//CLASS
	
	protected Game game;
	
	public State(Game game){
		this.game = game;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
		
}
