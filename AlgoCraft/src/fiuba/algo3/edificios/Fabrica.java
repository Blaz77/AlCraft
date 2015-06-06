package fiuba.algo3.edificios;

import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.unidades.ConstructorGolliat;

public class Fabrica extends EdificioEntrenadorUnidades {

	public Fabrica(Jugador propietario, Posicion posicion) {
		super(propietario, posicion);
		this.nombre = "Fabrica".intern();
		this.vida = new Vida(0, 1250);
		this.entrenador.agregarEntrenable(new ConstructorGolliat());
	}
	
}
