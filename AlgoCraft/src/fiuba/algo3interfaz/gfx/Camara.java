package fiuba.algo3interfaz.gfx;

import javax.swing.JPanel;

public class Camara {
	
	private static final int BORDE = 64;

	private JPanel panel;

	public int xOffset;
	public int yOffset;
	private int yStart;
	private int xStart;
	private int xEnd;
	private int yEnd;

	private int anchoMapa;
	private int altoMapa;
	
	public Camara(int xOffset, int yOffset, int anchoMapa, int altoMapa, JPanel panel){
		
		this.panel = panel;
		
		this.xOffset = xOffset;
		this.yOffset = yOffset;

		this.anchoMapa = anchoMapa;
		this.altoMapa = altoMapa;
		
		
	}

	public void move(int x, int y){
		
		this.xStart = - BORDE;
		this.yStart = - BORDE;
		this.xEnd = anchoMapa * 32 - panel.getWidth() + BORDE;
		this.yEnd = altoMapa * 32 - panel.getHeight() + HudVista.ALTO + BORDE;

		
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