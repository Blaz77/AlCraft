package fiuba.algo3.atributos.edificios.protoss;

import fiuba.algo3.atributos.AtributosRecolector;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.componentes.EstadoRecolectandoGasVespeno;
import fiuba.algo3.componentes.EstadoRecolectandoMineral;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;

public class AtributosAsimilador extends AtributosEdificio {

	private AtributosRecolector recolectorGas;
	
	public AtributosAsimilador() {
		// fields ObjetoVivo:
		this.costoMineral = 100;
		this.costoGasVespeno = 0;
		this.turnosConstruccion = 6;
		this.vidaMaxima = 450;
		//this.escudoMaximo = 450; ?
		this.nombre = "Asimilador";
		
		// fields EdificioRecolectorMineral
		this.recolectorGas = new AtributosRecolector(10);
				
		estadosIniciales.add(new EstadoRecolectandoGasVespeno(this.recolectorGas));
		
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
	public boolean debeOcuparGasVespeno(){
		return true;
	}
}
