package fiuba.algo3.ocupantes.unidades;

import fiuba.algo3.componentes.ITransporte;

public class Pasajero {
	
	private Unidad pasajero;
	private ITransporte transporte;
	
	public Pasajero(Unidad unidad, ITransporte transporte){
		this.pasajero = unidad;
		this.transporte = transporte;
	}

	public void salir(){ //definir aca una Posicion?
		this.transporte.liberarUnidad(pasajero);
	}
	
}
