package fiuba.algo3.edificios;

import fiuba.algo3.componentes.TrabajoRecoleccionMineral;
import fiuba.algo3.juego.Jugador;

public abstract class EdificioRecolectorMineral extends Edificio {
	
	public EdificioRecolectorMineral(Jugador propietario, int x, int y, int recoleccionPorTurno) {
		super(propietario, x, y);
		this.trabajo = new TrabajoRecoleccionMineral(recoleccionPorTurno, propietario);
	}

}
