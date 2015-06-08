package fiuba.algo3.raza;

import fiuba.algo3.atributos.AtributosJugador;
import fiuba.algo3.atributos.AtributosJugador;

public interface Raza {
	public Object construccionesDisponibles();
	
	public Object unidadesDisponibles();
	
	public TipoRaza getTipoRaza();
	
	public AtributosJugador getAtributos();
	
	// etc
}
