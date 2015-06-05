package fiuba.algo3.edificios;

import fiuba.algo3.componentes.TrabajoRecoleccionGasVespeno;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public abstract class EdificioRecolectorGasVespeno extends Edificio {
	
	public EdificioRecolectorGasVespeno(Jugador propietario, Posicion posicion, int recoleccionPorTurno) {
		super(propietario, posicion);
		this.trabajo = new TrabajoRecoleccionGasVespeno(recoleccionPorTurno, propietario);
	}
	
}
