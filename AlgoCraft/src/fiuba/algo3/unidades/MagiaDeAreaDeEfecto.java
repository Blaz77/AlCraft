package fiuba.algo3.unidades;

import fiuba.algo3.mapa.Posicion;

public abstract class MagiaDeAreaDeEfecto extends Magia{
	
	protected int rangoAoE;
	
	public boolean esDeAreaDeEfecto(){
		return true;
	}
	
	public abstract boolean puedeEjecutar(Posicion posicionCentral);
	
	public abstract void ejecutar(Posicion posicionCentral);

}
