package fiuba.algo3.edificios;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;


public abstract class EdificioIncrementadorPoblacion extends Edificio {
	
	public EdificioIncrementadorPoblacion(Jugador propietario, Posicion posicion) {
		super(propietario, posicion);
	}
	
	public void construccionFinalizada() {
		propietario.aumentarPoblacion(5);
	}

}
