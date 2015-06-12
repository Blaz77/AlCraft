package fiuba.algo3.atributos.edificios.terran;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificioRecolectorMineral;

public class AtributosCentroDeMineral extends
		AtributosEdificioRecolectorMineral {
	
	public AtributosCentroDeMineral() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(50, 0, 4);
		this.vida = new AtributosVida(500);
		this.nombre = "Centro de Mineral";

		// fields EdificioRecolectorMineral
		this.cantARecolectarPorTurno = 10;
	}


}
