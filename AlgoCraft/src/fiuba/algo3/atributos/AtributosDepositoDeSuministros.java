package fiuba.algo3.atributos;

public class AtributosDepositoDeSuministros extends 
					AtributosEdificioIncrementadorPoblacion {
	
	public AtributosDepositoDeSuministros() {
		// fields ObjetoVivo:
		this.costoMineral = 100;
		this.costoGasVespeno = 0;
		this.turnosConstruccion = 6;
		this.vidaMaxima = 500;
		this.nombre = "Deposito De Suministros";

		// fields EdificioIncrementadorPoblacion
		this.incrementoDePoblacion = 5;
	}

}
