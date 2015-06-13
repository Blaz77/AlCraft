package fiuba.algo3.componentes;

import fiuba.algo3.atributos.AtributosVoluntadDelSer;

public class VoluntadDelSer {
	private AtributosVoluntadDelSer atributos;

	public VoluntadDelSer(AtributosVoluntadDelSer atributos) {
		this.atributos = atributos;
	}
	
	public int getCostoPoblacion() {
		return this.atributos.getCostoPoblacion();
	}
	
	public int getMovPorTurno() {
		return this.atributos.getMovPorTurno();
	}
	
	public int getRangoVision() {
		return this.atributos.getRangoVision();
	}
}
