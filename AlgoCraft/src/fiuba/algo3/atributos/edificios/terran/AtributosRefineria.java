package fiuba.algo3.atributos.edificios.terran;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosRecolector;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.componentes.EstadoRecolectandoGasVespeno;

public class AtributosRefineria extends AtributosEdificio {

	private AtributosRecolector recolectorGas;
	
	public AtributosRefineria() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(100, 0, 6);
		this.vida = new AtributosVida(750);
		this.nombre = "Refineria";


		// fields EdificioRecolectorMineral
		this.recolectorGas = new AtributosRecolector(10);
		
		estadosIniciales.add(new EstadoRecolectandoGasVespeno(this.recolectorGas));
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
