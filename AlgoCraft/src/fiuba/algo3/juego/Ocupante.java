package fiuba.algo3.juego;

import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.mapa.recurso.TipoOcupante;

public abstract class Ocupante {
	protected String nombre;
	protected Posicion posicion;

	
	public Posicion getPosicion() {
		return this.posicion;  //quizas devolver copia!
	}
	
	public String toString() {
		return nombre;
	}

	public void pasarTurno(){
		return;
	}
	
	public abstract TipoOcupante getTipo();
}
