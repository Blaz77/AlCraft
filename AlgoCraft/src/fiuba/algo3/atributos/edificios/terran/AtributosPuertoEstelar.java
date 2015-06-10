package fiuba.algo3.atributos.edificios.terran;

import fiuba.algo3.atributos.edificios.AtributosEdificioEntrenadorUnidades;
import fiuba.algo3.ocupantes.unidades.constructores.ConstructorInfanteriaMagica;
import fiuba.algo3.ocupantes.unidades.constructores.ConstructorInfanteriaPesadaAerea;
import fiuba.algo3.ocupantes.unidades.constructores.ConstructorTransporte;

public class AtributosPuertoEstelar extends AtributosEdificioEntrenadorUnidades {

	public AtributosPuertoEstelar(){
		// fields ObjetoVivo:
		this.costoMineral = 150;
		this.costoGasVespeno = 100;
		this.turnosConstruccion = 10;
		this.vidaMaxima = 1300; 
		this.nombre = "Puerto Estelar";
		
		// fields EdificioEntrenadorUnidades:
		this.unidadesEntrenables.add(new ConstructorInfanteriaPesadaAerea());
		this.unidadesEntrenables.add(new ConstructorInfanteriaMagica());
		this.unidadesEntrenables.add(new ConstructorTransporte());
	}
	
}
