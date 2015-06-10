package fiuba.algo3.unidades;

public class Pasajero {
	
	private Unidad pasajero;
	private UnidadTransporte almacenador;
	
	public Pasajero(Unidad unidad, UnidadTransporte almacenador){
		this.pasajero = unidad;
		this.almacenador = almacenador;
	}

	public void salir(){ //definir aca una Posicion?
		almacenador.liberarUnidad(pasajero);
	}
	
}
