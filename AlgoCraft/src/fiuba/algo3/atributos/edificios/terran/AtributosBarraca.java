package fiuba.algo3.atributos.edificios.terran;

import fiuba.algo3.atributos.edificios.AtributosEdificioEntrenadorUnidades;
import fiuba.algo3.ocupantes.unidades.constructores.ConstructorInfanteriaLivianaTerrestre;

public class AtributosBarraca extends AtributosEdificioEntrenadorUnidades {

	public AtributosBarraca(){
		// fields ObjetoVivo:
		this.costoMineral = 150;
		this.costoGasVespeno = 0;
		this.turnosConstruccion = 12;
		this.vidaMaxima = 1000;
		this.nombre = "Barraca";
		
		// fields EdificioEntrenadorUnidades:
		this.unidadesEntrenables.add(new ConstructorInfanteriaLivianaTerrestre());
	}

}
