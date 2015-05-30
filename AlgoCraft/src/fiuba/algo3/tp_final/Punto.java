package fiuba.algo3.tp_final;

public class Punto {

	public int x;
	public int y;

	public Punto(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public double distancia(Punto otroPunto) {
		return Math.sqrt( Math.pow(this.x - otroPunto.x, 2) + Math.pow(this.y - otroPunto.y, 2) );
	}

}
