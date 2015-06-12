package fiuba.algo3.atributos.edificios.protoss;

import fiuba.algo3.atributos.AtributosRecolector;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.componentes.EstadoRecolectandoMineral;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;

public class AtributosNexoMineral extends AtributosEdificio {
	
	private AtributosRecolector recolectorMineral;
	
	public AtributosNexoMineral() {
		// fields ObjetoVivo:
		this.costoMineral = 50;
		this.costoGasVespeno = 0;
		this.turnosConstruccion = 4;
		this.vidaMaxima = 250;
		// this.escudoMaximo = 250; ?
		this.nombre = "Nexo Mineral";
		
		// fields EdificioRecolectorMineral
		this.recolectorMineral = new AtributosRecolector(10);
				
		estadosIniciales.add(new EstadoRecolectandoMineral(this.recolectorMineral));
		
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
	
	@Override
	public boolean debeOcuparRecurso(){
		return true;
	}
	
	@Override
	public boolean debeOcuparMineral(){
		return true;
	}
}
