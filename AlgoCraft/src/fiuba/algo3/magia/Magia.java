package fiuba.algo3.magia;

import fiuba.algo3.ocupantes.unidades.UnidadMagica;

public abstract class Magia {

	protected UnidadMagica unidad;
	protected int costoDeEjecutar;
	
	public void setUnidadMagica(UnidadMagica unidad){
		this.unidad = unidad;
	}
	
}
