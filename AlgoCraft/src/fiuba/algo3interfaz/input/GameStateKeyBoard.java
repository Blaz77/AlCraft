package fiuba.algo3interfaz.input;

// DEPRECATED

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import fiuba.algo3interfaz.states.GameState;

public class GameStateKeyBoard implements KeyListener {

	public HashMap<Integer, Boolean> keys;
	public boolean up, down, left, right;
	
	public GameState gameState;
	
	public GameStateKeyBoard(GameState gameState){
		this.gameState = gameState;
		keys = new HashMap<Integer, Boolean>();
		
		keys.put(KeyEvent.VK_W, false);
		keys.put(KeyEvent.VK_S, false);
		keys.put(KeyEvent.VK_A, false);
		keys.put(KeyEvent.VK_D, false);
		System.out.println("Initialized!");
	}
	

	public void tick(){
		
		up = keys.get(KeyEvent.VK_W);
		down = keys.get(KeyEvent.VK_S);
		left = keys.get(KeyEvent.VK_A);
		right = keys.get(KeyEvent.VK_D);
		System.out.println("Ticked!");

	}
		
	public void keyPressed(KeyEvent e) {
		keys.put(e.getKeyCode(), true);
		System.out.println("Key Pressed!");
	}

	public void keyReleased(KeyEvent e) {
		keys.put(e.getKeyCode(), false);
		System.out.println("Key Released!");

	}
	
	public void keyTyped(KeyEvent e) {
		System.out.println("Key Typed!");

	}
}