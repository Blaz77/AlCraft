package fiuba.algo3.magia;

import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.unidades.UnidadMagica;

public interface MagiaDeAreaDeEfecto {
	
	public void setUnidadMagica(UnidadMagica unidad);
	
	public boolean puedeEjecutar(Posicion posicionCentral);
	
	public void ejecutar(Posicion posicionCentral, Mapa mapa);

}
