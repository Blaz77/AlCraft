package fiuba.algo3.edificios;

import fiuba.algo3.tp_final.Jugador;

public class Barraca extends Edificio {

	public Barraca(Jugador propietario, int x, int y) {
		super(propietario, x, y);
		this.vida = new VidaSinEscudo(1000);
		//this.trabajo = new TrabajoEntrenarUnidades();
		this.nombre = "Barraca";
	}
	
	
}
