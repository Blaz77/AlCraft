package fiuba.algo3.ocupantes.recurso;

import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.Ocupante;

public class Mineral extends Recurso {
	
	public Mineral(Posicion posicion) {
		super(posicion);
	}

	public TipoOcupante getTipo() {
		return TipoOcupante.MINERAL;
	}

	@Override
	public boolean puedeCambiarsePor(Ocupante otroOcupante) {
		return (super.puedeCambiarsePor(otroOcupante) &&
				otroOcupante.debeOcuparMineral());
	}

}
