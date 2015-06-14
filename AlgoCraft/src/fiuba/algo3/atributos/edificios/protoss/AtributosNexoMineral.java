package fiuba.algo3.atributos.edificios.protoss;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosRecolector;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.componentes.EstadoRecolectandoMineral;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;

public class AtributosNexoMineral extends AtributosEdificio {
	
	private AtributosRecolector recolectorMineral;
	
	public AtributosNexoMineral() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(50, 0, 4);
		this.vida = new AtributosVida(250, 250);
		this.nombre = "Nexo Mineral";
		
		// fields EdificioRecolectorMineral
		this.recolectorMineral = new AtributosRecolector(10);
				
		estadosIniciales.add(new EstadoRecolectandoMineral(this.recolectorMineral));
		
		estadosIniciales.add(new EstadoRegenerandoEscudo());
	}
	
	@Override
	public IVida getVida() {
		return new VidaConEscudo(0, 0, this.vida);
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
