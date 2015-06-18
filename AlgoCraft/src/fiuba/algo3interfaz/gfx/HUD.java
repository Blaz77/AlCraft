package fiuba.algo3interfaz.gfx;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.raza.TipoRaza;


public class HUD {
	
	private TipoRaza raza;
	private Color color;
	private Jugador jugador; // dale que va, esto ya es cualquier cosa
	

	private int anchoVentana;
	private int altoVentana;

	public HUD(Jugador jugador, int anchoVentana, int altoVentana){
		this.raza = jugador.getRaza();
		this.color = jugador.getColor();
		this.jugador = jugador;
		
		this.anchoVentana = anchoVentana;
		this.altoVentana = altoVentana;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g) {
		

		dibujarHUD(g);
	}
	
	private static BufferedImage hudTest = ImageLoader.loadImage("/HUDs/HUDterran2.png"); //HUDzerg2.png, HUDprotoss2.png
	private void dibujarHUD(Graphics g) {
		
		g.drawImage(hudTest, 0, this.altoVentana - hudTest.getHeight(), null);
		g.setFont(new Font("Serif", Font.PLAIN, 36));
		g.setColor(java.awt.Color.WHITE);
		g.drawString(this.jugador.getNombre(), 120, 380);
		
	}
}
