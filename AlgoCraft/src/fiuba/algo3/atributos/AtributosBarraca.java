package fiuba.algo3.atributos;

import fiuba.algo3.unidades.ConstructorMarine;

public class AtributosBarraca extends AtributosEdificioEntrenadorUnidades {

	public AtributosBarraca(){
		this.vidaMaxima = 1000; 
		this.nombre = "Barraca";
		this.unidadesEntrenables.add(new ConstructorMarine());
	}

}
