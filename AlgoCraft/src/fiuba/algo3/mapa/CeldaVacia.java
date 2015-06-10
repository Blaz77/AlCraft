package fiuba.algo3.mapa;

import fiuba.algo3.mapa.recurso.TipoOcupante;
import fiuba.algo3.ocupante.ObjetoNoVivo;

public class CeldaVacia extends ObjetoNoVivo {

	public CeldaVacia(Posicion posicion){
		super(posicion);
	}
	
	@Override
	public TipoOcupante getTipo() {
		return TipoOcupante.CELDA_VACIA;
	}

}