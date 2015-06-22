package fiuba.algo3.excepciones;

import fiuba.algo3.terreno.Terreno;

public class TerrenoInadecuado extends RuntimeException {
	
	public TerrenoInadecuado(Terreno terreno){
		super(String.format("Terreno Inadecuado.\nNo puede ocupar %s.", terreno.getNombre()));
	}

}
