package fiuba.algo3.edificios;

import fiuba.algo3.componentes.TrabajoRecoleccionGasVespeno;
import fiuba.algo3.juego.Jugador;

public abstract class EdificioRecolectorGasVespeno extends Edificio {
	
	public EdificioRecolectorGasVespeno(Jugador propietario, int x, int y, int recoleccionPorTurno) {
			super(propietario, x, y);
			this.trabajo = new TrabajoRecoleccionGasVespeno(recoleccionPorTurno, propietario);
	}
	
}
