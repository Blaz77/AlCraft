package fiuba.algo3interfaz.gfx;

import javax.swing.JPanel;

public class Camara {
	
	private static final int BORDE = 64;
	
	
	private int xOffset;
	private int yOffset;
	private int anchoMapa;
	private int altoMapa;
	private JPanel panel;
	

	public Camara(int xOffset, int yOffset, int anchoMapa, int altoMapa, JPanel panel){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		
		this.anchoMapa = anchoMapa;
		this.altoMapa = altoMapa;
		
		this.panel = panel;
		
	}

	public void move(int x, int y){
		
		int xStart = -BORDE;
		int yStart = -BORDE;
		
		int xEnd = anchoMapa - panel.getWidth() + BORDE;
		int yEnd = altoMapa - panel.getHeight() + HudVista.ALTO +  BORDE;
		
		xOffset = Math.min(Math.max(xOffset + x, xStart), xEnd);
		yOffset = Math.min(Math.max(yOffset + y, yStart), yEnd);
	}
	
	public int getxOffset() {
		return xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}
}