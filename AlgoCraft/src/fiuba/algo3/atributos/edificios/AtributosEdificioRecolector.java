package fiuba.algo3.atributos.edificios;

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
