package fiuba.algo3.mapa.recurso;

import fiuba.algo3.edificios.Edificable;

public abstract class Recurso {
	
	public abstract boolean puedeEdificar(Edificable edif);
	
	public abstract TipoRecurso getTipo();
	
}
