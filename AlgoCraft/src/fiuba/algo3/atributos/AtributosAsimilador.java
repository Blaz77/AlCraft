package fiuba.algo3.atributos;

public class AtributosAsimilador extends AtributosEdificioRecolectorGasVespeno {

	public AtributosAsimilador() {
		// fields ObjetoVivo:
		this.costoMineral = 100;
		this.costoGasVespeno = 0;
		this.turnosConstruccion = 6;
		this.vidaMaxima = 450;
		//this.escudoMaximo = 450; ?
		this.nombre = "Asimilador";
		
		this.cantARecolectarPorTurno = 10;
	}

	@Override
	public boolean tieneEscudo() {
		return true;
	}
}
