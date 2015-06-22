package fiuba.algo3.raza;

import fiuba.algo3.atributos.jugador.AtributosJugador;
import fiuba.algo3.factories.EdificiosFactory;

public interface Raza {
	public Object construccionesDisponibles();
	
	public Object unidadesDisponibles();
	
	public TipoRaza getTipoRaza();
	
	public AtributosJugador getAtributos();
	
	public EdificiosFactory getEdificador();
	
	// etc
}
