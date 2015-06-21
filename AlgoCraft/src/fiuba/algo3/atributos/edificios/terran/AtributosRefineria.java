package fiuba.algo3.atributos.edificios.terran;

import java.util.Arrays;
import java.util.List;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosRecolector;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.componentes.Estado;
import fiuba.algo3.componentes.EstadoRecolectandoGasVespeno;
import fiuba.algo3.ocupantes.Tipo;

public class AtributosRefineria extends AtributosEdificio {

	private AtributosRecolector recolectorGas;
	
	public AtributosRefineria() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(100, 0, 6);
		this.vida = new AtributosVida(750);
		this.tipo = Tipo.REFINERIA;


		// fields EdificioRecolectorMineral
		this.recolectorGas = new AtributosRecolector(10);
	}
	
	@Override
	public List<Estado> getEstadosIniciales(){
		return Arrays.asList((Estado)new EstadoRecolectandoGasVespeno(this.recolectorGas));
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
