package fiuba.algo3.excepciones;

public class OrdenConstruccionViolado extends RuntimeException {

	public OrdenConstruccionViolado(String nombreEdificioRequerido){
		super(String.format("No puede construir este edificio. Requiere %s", 
				nombreEdificioRequerido));
	}
	
}
