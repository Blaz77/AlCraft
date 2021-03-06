package fiuba.algo3interfaz.states;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.UIManager;

import fiuba.algo3interfaz.Game;
import fiuba.algo3interfaz.gfx.ImageLoader;
import fiuba.algo3interfaz.gfx.MenuVista;
import fiuba.algo3interfaz.music.MP3Player;

public class MenuState extends State{
		
	MenuVista menu;
	
	
	public MenuState() {
		this.menu = new MenuVista(game.getAncho(), game.getAlto(), game);
		game.getPanel().add(menu);
		menu.setVisible(true);
	}

	@Override
	public void init() {
		game.getPanel().setBackground(UIManager.getColor ( "Panel.background" )); // Default color
		MP3Player.play("main_menu.mp3");
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
		game.getPanel().remove(menu);
	}

}
