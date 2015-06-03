package fiuba.algo3.edificios;

import fiuba.algo3.tp_final.Jugador;

public class Fabrica extends Edificio {
	
	public Fabrica(Jugador propietario, int x, int y) {
		super(propietario, x, y);
		this.vida = new VidaSinEscudo(1250);
		//this.trabajo = new TrabajoEntrenarUnidades();
		this.nombre = "Fabrica";
	}
	

}
