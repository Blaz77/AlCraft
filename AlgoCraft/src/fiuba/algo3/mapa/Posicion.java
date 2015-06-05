package fiuba.algo3.mapa;

public class Posicion {
	private int x;
	private int y;
	
	public Posicion (int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int distancia(Posicion other) {
		return Math.abs(this.x - other.x) + Math.abs(this.x - other.x);
	}

	public boolean estaEnRango(Posicion otraPosicion, int rango) {
		return (this.distancia(otraPosicion) <= rango);
	}
}
