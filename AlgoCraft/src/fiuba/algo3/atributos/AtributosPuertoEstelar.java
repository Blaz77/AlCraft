package fiuba.algo3.atributos;

import fiuba.algo3.unidades.ConstructorInfanteriaPesadaAerea;
import fiuba.algo3.unidades.ConstructorInfanteriaMagica;
import fiuba.algo3.unidades.ConstructorTransporte;

public class AtributosPuertoEstelar extends AtributosEdificioEntrenadorUnidades {

	public AtributosPuertoEstelar(){
		this.costoMineral = 150;
		this.costoGasVespeno = 100;
		
		this.vidaMaxima = 1300; 
		this.nombre = "Puerto Estelar";
		this.unidadesEntrenables.add(new ConstructorInfanteriaPesadaAerea());
		this.unidadesEntrenables.add(new ConstructorInfanteriaMagica());
		this.unidadesEntrenables.add(new ConstructorTransporte());
	}
	
}
