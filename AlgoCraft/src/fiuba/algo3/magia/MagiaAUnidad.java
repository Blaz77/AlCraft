package fiuba.algo3.magia;

import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.ocupantes.unidades.UnidadMagica;

public interface MagiaAUnidad {
	
	public void setUnidadMagica(UnidadMagica unidad);
	
	public boolean puedeEjecutar(Unidad unidad);
	
	public void ejecutar(Unidad unidad);

}
