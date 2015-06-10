package fiuba.algo3.mapa.recurso;

import fiuba.algo3.edificios.Edificable;
import fiuba.algo3.juego.Ocupante;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupante.ObjetoNoVivo;

public abstract class Recurso extends ObjetoNoVivo {
	
	private int cantidadRecurso = 1500;
	
	public Recurso(Posicion posicion) {
		super(posicion);
	}

	public abstract boolean puedeEdificar(Edificable edif);
	
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
