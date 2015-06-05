package fiuba.algo3.edificios;

import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.unidades.ConstructorMarine;

public class Barraca extends EdificioEntrenadorUnidades {

	public Barraca(Jugador propietario, Posicion posicion) {
		super(propietario, posicion);
		this.vida = new Vida(0, 1000);
		this.nombre = "Barraca";
		this.entrenador.agregarEntrenable(new ConstructorMarine());
	}
	
	
}
