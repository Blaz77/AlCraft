package fiuba.algo3.atributos;

import fiuba.algo3.unidades.ConstructorInfanteriaLivianaTerrestre;

public class AtributosBarraca extends AtributosEdificioEntrenadorUnidades {

	public AtributosBarraca(){
		//fields ObjetoVivo:
		this.costoMineral = 150;
		this.costoGasVespeno = 0;
		this.turnosConstruccion = 12;
		this.vidaMaxima = 1000;
		this.nombre = "Barraca";
		//fields EdificioEntrenadorUnidades:
		this.unidadesEntrenables.add(new ConstructorInfanteriaLivianaTerrestre());
	}

}
