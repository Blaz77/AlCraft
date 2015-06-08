package fiuba.algo3.atributos;

public class AtributosNexoMineral extends
		AtributosEdificioRecolectorMineral {
	
	public AtributosNexoMineral() {
		// fields ObjetoVivo:
		this.costoMineral = 50;
		this.costoGasVespeno = 0;
		this.turnosConstruccion = 4;
		this.vidaMaxima = 250;
		// this.escudoMaximo = 250; ?
		this.nombre = "Nexo Mineral";
		
		// fields EdificioRecolectorMineral
		this.cantARecolectarPorTurno = 10;
	}

	@Override
	public boolean tieneEscudo() {
		return true;
	}
}
