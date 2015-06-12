package fiuba.algo3.atributos.edificios.terran;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificioEntrenadorUnidades;
import fiuba.algo3.ocupantes.unidades.constructores.ConstructorInfanteriaLivianaTerrestre;

public class AtributosBarraca extends AtributosEdificioEntrenadorUnidades {

	public AtributosBarraca(){
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(150, 0, 12);
		this.vida = new AtributosVida(1000);
		this.nombre = "Barraca";
		
		// fields EdificioEntrenadorUnidades:
		this.unidadesEntrenables.add(new ConstructorInfanteriaLivianaTerrestre());
	}

}
