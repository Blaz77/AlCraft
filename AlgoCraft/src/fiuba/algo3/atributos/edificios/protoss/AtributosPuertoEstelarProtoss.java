package fiuba.algo3.atributos.edificios.protoss;

import fiuba.algo3.atributos.edificios.AtributosEdificioEntrenadorUnidades;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;
import fiuba.algo3.ocupantes.unidades.constructores.ConstructorInfanteriaPesadaAerea;
import fiuba.algo3.ocupantes.unidades.constructores.ConstructorTransporte;

public class AtributosPuertoEstelarProtoss extends AtributosEdificioEntrenadorUnidades {

	public AtributosPuertoEstelarProtoss(){
		// fields ObjetoVivo:
		this.costoMineral = 150;
		this.costoGasVespeno = 100;
		this.turnosConstruccion = 10;
		this.vidaMaxima = 600; 
		this.escudoMaximo = 600;
		this.nombre = "Puerto Estelar";
		
		// fields EdificioEntrenadorUnidades:
		this.unidadesEntrenables.add(new ConstructorInfanteriaPesadaAerea());
		this.unidadesEntrenables.add(new ConstructorTransporte());
		
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
