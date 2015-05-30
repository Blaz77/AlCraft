package fiuba.algo3.tp_final;

public class Punto {

	private int x;
	private int y;
	private Recurso recurso = null;

	public Punto(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public double distancia(Punto otroPunto) {
		return Math.sqrt( Math.pow(this.x - otroPunto.x, 2) + Math.pow(this.y - otroPunto.y, 2) );
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Recurso getRecurso() {
		return recurso;
	}
}
