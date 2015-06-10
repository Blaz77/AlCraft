package fiuba.algo3.atributos;

public abstract class AtributosEdificioRecolector extends AtributosEdificio {

	int cantARecolectarPorTurno;
	
	@Override
	public boolean debeOcuparRecurso(){
		return true;
	}
	
	public int getCantARecolectarPorTurno(){
		return cantARecolectarPorTurno;
	}
	
}
