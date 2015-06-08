package fiuba.algo3.atributos;

import fiuba.algo3.unidades.ConstructorInfanteriaPesadaTerrestre;

public class AtributosFabrica extends AtributosEdificioEntrenadorUnidades {

	public AtributosFabrica(){
		this.costoMineral = 200;
		this.costoGasVespeno = 100;
		this.vidaMaxima = 1250; 
		this.nombre = "Fabrica";
		this.unidadesEntrenables.add(new ConstructorInfanteriaPesadaTerrestre());
	}

}
