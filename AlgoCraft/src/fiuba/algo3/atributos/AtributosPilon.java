package fiuba.algo3.atributos;

public class AtributosPilon extends 
					AtributosEdificioIncrementadorPoblacion {
	
	public AtributosPilon() {
		// fields ObjetoVivo:
		this.costoMineral = 100;
		this.costoGasVespeno = 0;
		this.turnosConstruccion = 5;
		this.vidaMaxima = 300;
		//this.escudoMaximo = 300; ?
		this.nombre = "Pilon";

		// fields EdificioIncrementadorPoblacion
		this.incrementoDePoblacion = 5;
	}

	@Override
	public boolean tieneEscudo() {
		return true;
	}
}
