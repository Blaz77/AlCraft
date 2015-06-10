package fiuba.algo3.atributos.edificios.terran;

import fiuba.algo3.atributos.edificios.AtributosEdificioRecolectorMineral;

public class AtributosCentroDeMineral extends
		AtributosEdificioRecolectorMineral {
	
	public AtributosCentroDeMineral() {
		// fields ObjetoVivo:
		this.costoMineral = 50;
		this.costoGasVespeno = 0;
		this.turnosConstruccion = 4;
		this.vidaMaxima = 500;
		this.nombre = "Centro de Mineral";

		// fields EdificioRecolectorMineral
		this.cantARecolectarPorTurno = 10;
	}


}
