package fiuba.algo3.atributos;

import fiuba.algo3.unidades.ConstructorEspectro;
import fiuba.algo3.unidades.ConstructorNaveDeCiencia;
import fiuba.algo3.unidades.ConstructorNaveDeTransporte;

public class AtributosPuertoEstelarProtoss extends AtributosEdificioEntrenadorUnidades {

	public AtributosPuertoEstelarProtoss(){
		this.costoMineral = 150;
		this.costoGasVespeno = 100;
		
		this.vidaMaxima = 1300; 
		this.nombre = "Puerto Estelar";
		this.unidadesEntrenables.add(new ConstructorEspectro());
		this.unidadesEntrenables.add(new ConstructorNaveDeCiencia());
		this.unidadesEntrenables.add(new ConstructorNaveDeTransporte());
	}
	
}
