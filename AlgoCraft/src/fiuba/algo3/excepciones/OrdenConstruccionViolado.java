package fiuba.algo3.excepciones;

import fiuba.algo3.atributos.edificios.AtributosEdificio;

public class OrdenConstruccionViolado extends RuntimeException {

	public OrdenConstruccionViolado(AtributosEdificio atributos){
		super(String.format("No puede construir este edificio. Requiere %s", 
				atributos.getEdificioRequerido().getNombre()));
	}
	
}
