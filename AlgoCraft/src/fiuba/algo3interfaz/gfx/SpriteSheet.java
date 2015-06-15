package fiuba.algo3interfaz.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet){
		this.sheet = sheet;
	}
	
	// Corta un sprite del sheet
	public BufferedImage crop(int x, int y, int ancho, int alto){
		return sheet.getSubimage(x, y, ancho, alto);
	}
	
}
