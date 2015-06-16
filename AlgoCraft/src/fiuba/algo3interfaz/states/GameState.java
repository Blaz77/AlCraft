package fiuba.algo3interfaz.states;

import java.awt.Graphics;

import fiuba.algo3interfaz.Game;
import fiuba.algo3interfaz.gfx.MapaVista;

public class GameState extends State{

	private MapaVista mapaVista;
	
	public GameState(Game game){
		super(game);
		mapaVista = new MapaVista(20, 15, game.getKeyManager(), game.getMouseManager()); // TODO: recibir un mapaproxy?
	}
	
	@Override
	public void tick() {
		mapaVista.tick();
	}

	@Override
	public void render(Graphics g) {

		mapaVista.render(g);
		
	}

}
