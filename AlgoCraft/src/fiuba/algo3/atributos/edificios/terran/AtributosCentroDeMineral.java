package fiuba.algo3.atributos.edificios.terran;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosRecolector;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.componentes.EstadoRecolectandoMineral;

public class AtributosCentroDeMineral extends AtributosEdificio {
	
	private AtributosRecolector recolectorMineral;
	
	public AtributosCentroDeMineral() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(50, 0, 4);
		this.vida = new AtributosVida(500);
		this.nombre = "Centro de Mineral";

		// fields EdificioRecolectorMineral
		this.recolectorMineral = new AtributosRecolector(10);
		
		estadosIniciales.add(new EstadoRecolectandoMineral(this.recolectorMineral));
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
