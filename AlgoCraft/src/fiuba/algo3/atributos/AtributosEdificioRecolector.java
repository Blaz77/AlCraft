package fiuba.algo3.atributos;

public abstract class AtributosEdificioRecolector extends AtributosEdificio {

	int cantARecolectarPorTurno;
	
	@Override
	public boolean edificableSobreRecurso(){
		return true;
	}
	
	public int getCantARecolectarPorTurno(){
		return cantARecolectarPorTurno;
	}
	
}
