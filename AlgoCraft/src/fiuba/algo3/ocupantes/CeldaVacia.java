package fiuba.algo3.ocupantes;

import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;

// CeldaVacia == RecursoNull
public class CeldaVacia extends ObjetoNoVivo {

	public CeldaVacia(Posicion posicion){
		super(posicion);
	}
	
	@Override
	public TipoOcupante getTipo() {
		return TipoOcupante.CELDA_VACIA;
	}

	@Override
	public boolean puedeCambiarsePor(Ocupante otroOcupante) {
		return !otroOcupante.debeOcuparRecurso();
	}

	@Override
	public boolean puedeOcuparTierra() {
		return true;
	}

	@Override
	public boolean puedeOcuparEspacio() {
		return true;
	}

	@Override
	public boolean debeOcuparRecurso() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean debeOcuparMineral() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean debeOcuparGasVespeno() {
		// TODO Auto-generated method stub
		return false;
	}

}