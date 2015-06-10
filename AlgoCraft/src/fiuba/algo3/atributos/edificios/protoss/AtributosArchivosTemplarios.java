package fiuba.algo3.atributos.edificios.protoss;

import fiuba.algo3.atributos.edificios.AtributosEdificioEntrenadorUnidades;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;
import fiuba.algo3.ocupantes.unidades.constructores.ConstructorInfanteriaMagica;

public class AtributosArchivosTemplarios extends AtributosEdificioEntrenadorUnidades {

	public AtributosArchivosTemplarios() {
		// fields ObjetoVivo:
		this.costoMineral = 150;
		this.costoGasVespeno = 200;
		this.turnosConstruccion = 9;
		this.vidaMaxima = 500; 
		//this.escudoMaximo = 500; ?
		this.nombre = "Archivos Templarios";
		
		// fields EdificioEntrenadorUnidades:
		this.unidadesEntrenables.add(new ConstructorInfanteriaMagica());
		
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
