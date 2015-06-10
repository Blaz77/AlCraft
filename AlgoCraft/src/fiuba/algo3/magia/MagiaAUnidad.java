package fiuba.algo3.magia;

import fiuba.algo3.ocupantes.unidades.Unidad;

public abstract class MagiaAUnidad extends Magia{
	
	public boolean esAUnidad(){
		return true;
	}
	
	public abstract boolean puedeEjecutar(Unidad unidad);
	
	public abstract void ejecutar(Unidad unidad);

}
