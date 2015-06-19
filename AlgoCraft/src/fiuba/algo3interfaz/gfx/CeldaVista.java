package fiuba.algo3interfaz.gfx;

import java.awt.image.BufferedImage;

public class CeldaVista {
	private int index;
	private BufferedImage terreno;
	
	public CeldaVista(int index) {
		this.index = Math.abs(index);
	}
	
	public int getIndex(){
		return index;
	}

	public void render(){
		
	}
}
