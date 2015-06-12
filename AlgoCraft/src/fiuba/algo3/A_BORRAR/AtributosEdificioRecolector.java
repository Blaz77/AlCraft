package fiuba.algo3.A_BORRAR;

import fiuba.algo3.atributos.edificios.AtributosEdificio;

public abstract class AtributosEdificioRecolector extends AtributosEdificio {

	protected int cantARecolectarPorTurno;
	
	@Override
	public boolean debeOcuparRecurso(){
		return true;
	}
	
	public int getCantARecolectarPorTurno(){
		return cantARecolectarPorTurno;
	}
	
}
