package fiuba.algo3.mapa.recurso;

import fiuba.algo3.edificios.Edificable;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupante.ObjetoNoVivo;

public abstract class Recurso extends ObjetoNoVivo {
	
	private int cantidadRecurso = 1500;
	
	public Recurso(Posicion posicion) {
		super(posicion);
	}

	public abstract boolean puedeEdificar(Edificable edif);
	
	public abstract TipoOcupante getTipo();
	
}
