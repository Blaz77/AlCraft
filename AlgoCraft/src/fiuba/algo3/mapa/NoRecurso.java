package fiuba.algo3.mapa;

import fiuba.algo3.edificios.Edificable;

public class NoRecurso extends Recurso {

	@Override
	public boolean puedeEdificar(Edificable edif) {
		return true;
	}

	@Override
	public TipoRecurso getTipo() {
		return TipoRecurso.NO_RECURSO;
	}

}
