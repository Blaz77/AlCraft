package fiuba.algo3.atributos.edificios.protoss;

import java.util.Arrays;
import java.util.List;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosRecolector;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.componentes.Estado;
import fiuba.algo3.componentes.EstadoRecolectandoMineral;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;
import fiuba.algo3.ocupantes.Tipo;

public class AtributosNexoMineral extends AtributosEdificio {
	
	private AtributosRecolector recolectorMineral;
	
	public AtributosNexoMineral() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(50, 0, 4);
		this.vida = new AtributosVida(250, 250);
		this.tipo = Tipo.NEXO_MINERAL;
		
		// fields EdificioRecolectorMineral
		this.recolectorMineral = new AtributosRecolector(10);
	}
	
	@Override
	public List<Estado> getEstadosIniciales(){
		return Arrays.asList(new EstadoRegenerandoEscudo(),
				new EstadoRecolectandoMineral(this.recolectorMineral));
	}
	
	@Override
	public IVida getVida() {
		return new VidaConEscudo(this.vida);
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
