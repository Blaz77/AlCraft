package fiuba.algo3.atributos.edificios.protoss;

import java.util.Arrays;
import java.util.List;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosRecolector;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.componentes.Estado;
import fiuba.algo3.componentes.EstadoRecolectandoGasVespeno;
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
	}
	
	@Override
	public List<Estado> getEstadosIniciales(){
		return Arrays.asList(new EstadoRegenerandoEscudo(),
				new EstadoRecolectandoGasVespeno(this.recolectorGas));
	}
	
	@Override
	public IVida getVida() {
		return new VidaConEscudo(0, 0, this.vida);
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
