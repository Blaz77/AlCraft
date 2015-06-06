package fiuba.algo3.mapa;

import fiuba.algo3.juego.Ocupante;
import fiuba.algo3.mapa.recurso.TipoOcupante;

public class CeldaVacia extends Ocupante {

	@Override
	public void pasarTurno() {
		return;

	}

	@Override
	public TipoOcupante getTipo() {
		return TipoOcupante.CELDA_VACIA;
	}

}
