package fiuba.algo3.edificios;

import fiuba.algo3.componentes.EntrenadorUnidades;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.unidades.ConstructorGolliat;

public class Fabrica extends EdificioEntrenadorUnidades {
	
	public Fabrica(Jugador propietario, int x, int y) {
		super(propietario, x, y);
		this.vida = new Vida(1250);
		//this.trabajo = new TrabajoEntrenarUnidades();
		this.entrenador = new EntrenadorUnidades(this);
		this.trabajo = this.entrenador;
		this.nombre = "Fabrica";
		this.entrenador.agregarEntrenable(new ConstructorGolliat());
	}
	

}
