package fiuba.algo3interfaz.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fiuba.algo3interfaz.Game;
import fiuba.algo3interfaz.gfx.ImageLoader;
import fiuba.algo3interfaz.gfx.MenuVista;

public class MenuState extends State{
		
	MenuVista menu;
	
	
	public MenuState() {
		this.menu = new MenuVista(game.getAncho(), game.getAlto(), game);
		game.add(menu);
		menu.setVisible(true);
	}

	@Override
	public void tick() {
		menu.setVisible(true);
	}

	@Override
	public void render(Graphics g) {
		menu.render(g);
	}
	
	public void terminar() {
		menu.setVisible(false);
		game.remove(menu);
		
	}

}
