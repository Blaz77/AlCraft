package fiuba.algo3.atributos.edificios;

import fiuba.algo3.componentes.EstadoSuministrandoPoblacion;

public class AtributosEdificioIncrementadorPoblacion extends AtributosEdificio {

	protected int incrementoDePoblacion;
	
	public AtributosEdificioIncrementadorPoblacion(){
		estadosIniciales.add(new EstadoSuministrandoPoblacion());
	}
	
	public int getIncrementoDePoblacion(){
		return incrementoDePoblacion;
	}
}
