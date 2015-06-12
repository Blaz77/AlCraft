package fiuba.algo3.atributos.edificios.terran;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificioIncrementadorPoblacion;

public class AtributosDepositoDeSuministros extends 
					AtributosEdificioIncrementadorPoblacion {
	
	public AtributosDepositoDeSuministros() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(100, 0, 6);
		this.vida = new AtributosVida(500);
		this.nombre = "Deposito De Suministros";

		// fields EdificioIncrementadorPoblacion
		this.incrementoDePoblacion = 5;
	}

}
