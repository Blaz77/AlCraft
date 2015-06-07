package fiuba.algo3.atributos;

import fiuba.algo3.unidades.ConstructorGolliat;

public class AtributosFabrica extends AtributosEdificioEntrenadorUnidades {

	public AtributosFabrica(){
		this.vidaMaxima = 1250; 
		this.nombre = "Fabrica";
		this.unidadesEntrenables.add(new ConstructorGolliat());
	}

}
