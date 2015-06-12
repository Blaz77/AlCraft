package fiuba.algo3.atributos.edificios.protoss;

import fiuba.algo3.atributos.AtributosIncrementadorPoblacion;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.EstadoSuministrandoPoblacion;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;

public class AtributosPilon extends AtributosEdificio {
	
	private AtributosIncrementadorPoblacion incrPoblacion;
	
	public AtributosPilon() {
		// fields ObjetoVivo:
		this.costoMineral = 100;
		this.costoGasVespeno = 0;
		this.turnosConstruccion = 5;
		this.vidaMaxima = 300;
		//this.escudoMaximo = 300; ?
		this.nombre = "Pilon";

		// fields EdificioIncrementadorPoblacion
		this.incrPoblacion = new AtributosIncrementadorPoblacion(5);
				
		estadosIniciales.add(new EstadoSuministrandoPoblacion(this.incrPoblacion));
		
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
