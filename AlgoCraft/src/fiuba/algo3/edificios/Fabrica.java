package fiuba.algo3.edificios;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.unidades.ConstructorGolliat;

public class Fabrica extends Edificio {
	
	public Fabrica(Jugador propietario, int x, int y) {
		super(propietario, x, y);
		this.vida = new VidaSinEscudo(1250);
		//this.trabajo = new TrabajoEntrenarUnidades();
		this.entrenador = new EntrenadorUnidades(this);
		this.trabajo = this.entrenador;
		this.nombre = "Fabrica";
		this.entrenador.agregarEntrenable(new ConstructorGolliat());
	}
	

}
