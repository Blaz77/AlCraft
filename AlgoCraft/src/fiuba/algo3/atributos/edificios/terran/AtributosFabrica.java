package fiuba.algo3.atributos.edificios.terran;

import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificioEntrenadorUnidades;
import fiuba.algo3.ocupantes.unidades.constructores.ConstructorInfanteriaPesadaTerrestre;

public class AtributosFabrica extends AtributosEdificioEntrenadorUnidades {

	public AtributosFabrica(){
		// fields ObjetoVivo:
		this.costoMineral = 200;
		this.costoGasVespeno = 100;
		this.turnosConstruccion = 12;
		this.vida = new AtributosVida(1250); 
		this.nombre = "Fabrica";
		
		// fields EdificioEntrenadorUnidades:
		this.unidadesEntrenables.add(new ConstructorInfanteriaPesadaTerrestre());
	}

}
