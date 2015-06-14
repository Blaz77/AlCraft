package fiuba.algo3.atributos.edificios.protoss;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosRecolector;
import fiuba.algo3.atributos.AtributosVida;
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
		this.costo = new AtributosCosto(100, 0, 6);
		this.vida = new AtributosVida(450, 450);
		this.nombre = "Asimilador";
		
		// fields EdificioRecolectorMineral
		this.recolectorGas = new AtributosRecolector(10);
				
		estadosIniciales.add(new EstadoRecolectandoGasVespeno(this.recolectorGas));
		
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
	public boolean debeOcuparGasVespeno(){
		return true;
	}
}
