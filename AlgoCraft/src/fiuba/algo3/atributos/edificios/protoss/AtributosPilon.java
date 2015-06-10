package fiuba.algo3.atributos.edificios.protoss;

import fiuba.algo3.atributos.edificios.AtributosEdificioIncrementadorPoblacion;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;

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
		estadosIniciales.add(new EstadoRegenerandoEscudo());
	}
	
	@Override
	public IVida getVida() {
		return new VidaConEscudo(this);
	}
	@Override
	public boolean tieneEscudo() {
		return true;
	}
}
