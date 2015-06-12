package fiuba.algo3.componentes;

import fiuba.algo3.atributos.AtributosCosto;

public class Costo {
	private AtributosCosto atributos;

	public Costo(AtributosCosto atributos) {
		this.atributos = atributos;
	}
	
	public int getCostoMineral(){
		return this.atributos.getCostoMineral();
	}
	
	public int getCostoGasVespeno(){
		return this.atributos.getCostoGasVespeno();
	}
	
	public int getTurnosConstruccion(){
		return this.atributos.getTurnosConstruccion();
	}
}
