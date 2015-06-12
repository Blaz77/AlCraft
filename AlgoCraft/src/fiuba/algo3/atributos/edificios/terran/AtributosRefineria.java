package fiuba.algo3.atributos.edificios.terran;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificioRecolectorGasVespeno;

public class AtributosRefineria extends AtributosEdificioRecolectorGasVespeno {

	public AtributosRefineria() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(100, 0, 6);
		this.vida = new AtributosVida(750);
		this.nombre = "Refineria";

		this.cantARecolectarPorTurno = 10;
	}

}
