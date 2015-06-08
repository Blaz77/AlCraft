package fiuba.algo3.atributos;

public class AtributosNexoMineral extends
		AtributosEdificioRecolectorMineral {
	
	public AtributosNexoMineral() {
		this.costoMineral = 50;
		this.costoGasVespeno = 0;
		this.vidaMaxima = 250;
		// this.escudoMaximo = 250; ?
		this.nombre = "Nexo Mineral";
		
		this.cantARecolectarPorTurno = 10;
	}

	@Override
	public boolean tieneEscudo() {
		return true;
	}
}
