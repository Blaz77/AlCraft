package fiuba.algo3.edificios;

import fiuba.algo3.juego.Jugador;


public abstract class EdificioIncrementadorPoblacion extends Edificio {
	
	public EdificioIncrementadorPoblacion(Jugador propietario, int x, int y) {
		super(propietario, x, y);
	}
	
	public void construccionFinalizada() {
		propietario.aumentarPoblacion(5);
	}

}
