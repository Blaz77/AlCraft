package fiuba.algo3.mapa;

import fiuba.algo3.juego.Ocupante;
import fiuba.algo3.mapa.recurso.TipoOcupante;
import fiuba.algo3.ocupante.ObjetoNoVivo;

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