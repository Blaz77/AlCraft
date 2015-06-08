package fiuba.algo3.atributos;

public class AtributosRefineria extends AtributosEdificioRecolectorGasVespeno {

	public AtributosRefineria() {
		// fields ObjetoVivo:
		this.costoMineral = 200;
		this.costoGasVespeno = 0;
		this.turnosConstruccion = 6;
		this.vidaMaxima = 750;
		this.nombre = "Refineria";

		this.cantARecolectarPorTurno = 10;
	}

}
