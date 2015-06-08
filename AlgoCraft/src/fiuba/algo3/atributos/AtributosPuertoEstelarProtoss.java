package fiuba.algo3.atributos;

import fiuba.algo3.unidades.ConstructorInfanteriaPesadaAerea;
import fiuba.algo3.unidades.ConstructorTransporte;

public class AtributosPuertoEstelarProtoss extends AtributosEdificioEntrenadorUnidades {

	public AtributosPuertoEstelarProtoss(){
		this.costoMineral = 150;
		this.costoGasVespeno = 100;
		this.vidaMaxima = 600; 
		//this.escudoMaximo = 600;
		this.nombre = "Puerto Estelar";
		
		// fields EdificioEntrenadorUnidades:
		this.unidadesEntrenables.add(new ConstructorInfanteriaPesadaAerea());
		this.unidadesEntrenables.add(new ConstructorTransporte());
	}
	
	@Override
	public boolean tieneEscudo() {
		return true;
	}
}
