package fiuba.algo3.excepciones;

import fiuba.algo3.ocupantes.recurso.Recurso;
import fiuba.algo3.terreno.Terreno;

public class RecursoPresente extends RuntimeException {

	public RecursoPresente(Recurso recurso){
		super(String.format("Recurso Inadecuado.\nNo puede ocupar %s.", recurso.getTipo().getNombre()));
	}
	
}
