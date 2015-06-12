package fiuba.algo3.atributos.edificios.terran;

import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificioRecolectorGasVespeno;

public class AtributosRefineria extends AtributosEdificioRecolectorGasVespeno {

	public AtributosRefineria() {
		// fields ObjetoVivo:
		this.costoMineral = 100;
		this.costoGasVespeno = 0;
		this.turnosConstruccion = 6;
		this.vida = new AtributosVida(750);
		this.nombre = "Refineria";

		this.cantARecolectarPorTurno = 10;
	}

}
