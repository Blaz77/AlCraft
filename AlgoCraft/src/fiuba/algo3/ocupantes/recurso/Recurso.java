package fiuba.algo3.ocupantes.recurso;

import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.ObjetoNoVivo;
import fiuba.algo3.ocupantes.Ocupante;

public abstract class Recurso extends ObjetoNoVivo {
	
	//private int cantidadRecurso = 1500; tal vez sea eterno? :D
	
	public Recurso(Posicion posicion) {
		super(posicion);
	}
	
	public abstract TipoOcupante getTipo();
	
	@Override
	public boolean puedeOcuparTierra() {
		return true;
	}

	@Override
	public boolean puedeOcuparEspacio() {
		return false;
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
	
	@Override
	public boolean puedeCambiarsePor(Ocupante otroOcupante) {
		return otroOcupante.debeOcuparRecurso();
	}
	
}
