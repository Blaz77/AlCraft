package fiuba.algo3.atributos.edificios.terran;

import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificioIncrementadorPoblacion;

public class AtributosDepositoDeSuministros extends 
					AtributosEdificioIncrementadorPoblacion {
	
	public AtributosDepositoDeSuministros() {
		// fields ObjetoVivo:
		this.costoMineral = 100;
		this.costoGasVespeno = 0;
		this.turnosConstruccion = 6;
		this.vida = new AtributosVida(500);
		this.nombre = "Deposito De Suministros";

		// fields EdificioIncrementadorPoblacion
		this.incrementoDePoblacion = 5;
	}

}
