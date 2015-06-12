package fiuba.algo3.atributos.edificios.terran;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificioEntrenadorUnidades;
import fiuba.algo3.ocupantes.unidades.constructores.ConstructorInfanteriaMagica;
import fiuba.algo3.ocupantes.unidades.constructores.ConstructorInfanteriaPesadaAerea;
import fiuba.algo3.ocupantes.unidades.constructores.ConstructorTransporte;

public class AtributosPuertoEstelar extends AtributosEdificioEntrenadorUnidades {

	public AtributosPuertoEstelar(){
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(150, 100, 10);
		this.vida = new AtributosVida(1300); 
		this.nombre = "Puerto Estelar";
		
		// fields EdificioEntrenadorUnidades:
		this.unidadesEntrenables.add(new ConstructorInfanteriaPesadaAerea());
		this.unidadesEntrenables.add(new ConstructorInfanteriaMagica());
		this.unidadesEntrenables.add(new ConstructorTransporte());
	}
	
}
