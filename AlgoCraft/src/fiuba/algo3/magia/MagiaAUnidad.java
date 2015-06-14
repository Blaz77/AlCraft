package fiuba.algo3.magia;

import fiuba.algo3.ocupantes.unidades.Unidad;

public interface MagiaAUnidad {
	
	public void setUnidad(Unidad unidad);
	
	public boolean puedeEjecutar(Unidad unidad);
	
	public void ejecutar(Unidad unidad);

}
