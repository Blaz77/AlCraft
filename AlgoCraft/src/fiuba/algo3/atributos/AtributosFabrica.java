package fiuba.algo3.atributos;

import fiuba.algo3.unidades.ConstructorInfanteriaPesadaTerrestre;

public class AtributosFabrica extends AtributosEdificioEntrenadorUnidades {

	public AtributosFabrica(){
		// fields ObjetoVivo:
		this.costoMineral = 200;
		this.costoGasVespeno = 100;
		this.turnosConstruccion = 12;
		this.vidaMaxima = 1250; 
		this.nombre = "Fabrica";
		
		// fields EdificioEntrenadorUnidades:
		this.unidadesEntrenables.add(new ConstructorInfanteriaPesadaTerrestre());
	}

}
