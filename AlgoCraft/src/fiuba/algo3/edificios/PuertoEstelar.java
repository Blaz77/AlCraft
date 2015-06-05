package fiuba.algo3.edificios;

import fiuba.algo3.juego.Jugador;

public class PuertoEstelar extends Edificio {

	public PuertoEstelar(Jugador propietario, int x, int y) {
		super(propietario, x, y);
		this.vida = new VidaSinEscudo(1300);
		//this.trabajo = new TrabajoEntrenarUnidades();
		this.nombre = "Puerto Estelar";
	}
	
	
}
