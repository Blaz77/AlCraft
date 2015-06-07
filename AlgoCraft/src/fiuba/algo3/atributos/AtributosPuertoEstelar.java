package fiuba.algo3.atributos;

import fiuba.algo3.unidades.ConstructorEspectro;
import fiuba.algo3.unidades.ConstructorNaveDeCiencia;
import fiuba.algo3.unidades.ConstructorNaveDeTransporte;

public class AtributosPuertoEstelar extends AtributosEdificioEntrenadorUnidades {

	public AtributosPuertoEstelar(){
		this.vidaMaxima = 1300; 
		this.nombre = "Puerto Estelar";
		this.unidadesEntrenables.add(new ConstructorEspectro());
		this.unidadesEntrenables.add(new ConstructorNaveDeCiencia());
		this.unidadesEntrenables.add(new ConstructorNaveDeTransporte());
	}
	
}
