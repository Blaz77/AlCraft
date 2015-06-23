package fiuba.algo3.atributos;

public class AtributosTransporte {
	
	private int capacidadAlmacenamiento; //capacidadTransporte
	private int rangoEntrada; // distancia permitida para dejar entrar almacenables.
	
	public AtributosTransporte(int capacidadMaxima, int rangoEntrada) {
		this.capacidadAlmacenamiento = capacidadMaxima;
		this.rangoEntrada = rangoEntrada;
	}
	
	public int getCapacidadAlmacenamiento(){
		return capacidadAlmacenamiento;
	}

	public int getRangoEntrada() {
		return rangoEntrada;
	}

}
