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
		return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
	}

	public boolean estaEnRango(Posicion otraPosicion, int rango) {
		return (this.distancia(otraPosicion) <= rango);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicion other = (Posicion) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
