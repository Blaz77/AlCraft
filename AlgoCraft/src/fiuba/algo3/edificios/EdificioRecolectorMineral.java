package fiuba.algo3.edificios;

import fiuba.algo3.componentes.TrabajoRecoleccionMineral;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public abstract class EdificioRecolectorMineral extends Edificio {
	
	public EdificioRecolectorMineral(Jugador propietario, Posicion posicion, int recoleccionPorTurno) {
		super(propietario, posicion);
		this.trabajo = new TrabajoRecoleccionMineral(recoleccionPorTurno, propietario);
	}

}
