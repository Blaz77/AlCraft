package fiuba.algo3interfaz.gfx;

public class Camara {
	public int xOffset;
	public int yOffset;
	private int yStart;
	private int xStart;
	private int xEnd;
	private int yEnd;
	
	public Camara(int xOffset, int yOffset, int xStart, int yStart, int xEnd, int yEnd){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
		
	}

	public void move(int x, int y){
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