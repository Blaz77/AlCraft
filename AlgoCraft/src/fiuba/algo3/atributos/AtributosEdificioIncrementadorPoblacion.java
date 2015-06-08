package fiuba.algo3.atributos;

import fiuba.algo3.componentes.EstadoSuministrandoPoblacion;

public class AtributosEdificioIncrementadorPoblacion extends AtributosEdificio {

	int incrementoDePoblacion;
	
	public AtributosEdificioIncrementadorPoblacion(){
		estadosIniciales.add(new EstadoSuministrandoPoblacion());
	}
	
	public int getIncrementoDePoblacion(){
		return incrementoDePoblacion;
	}
}
