package fiuba.algo3.mapa;

import fiuba.algo3.edificios.Edificable;

public abstract class Recurso {
	
	public abstract boolean puedeEdificar(Edificable edif);
	
	public abstract TipoRecurso getTipo();
	
	// Este metodo despues se borra :3 es para los string y eso.
	public String toString() {
		return "Sin Recurso";
	}
}
