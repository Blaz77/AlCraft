package fiuba.algo3.atributos;

public class AtributosPilon extends 
					AtributosEdificioIncrementadorPoblacion {
	
	public AtributosPilon() {
		this.costoMineral = 100;
		this.costoGasVespeno = 0;
		
		this.incrementoDePoblacion = 5;
		this.vidaMaxima = 300;
		//this.escudoMaximo = 300; ?
		this.nombre = "Pilon";
	}

	@Override
	public boolean tieneEscudo() {
		return true;
	}
}
