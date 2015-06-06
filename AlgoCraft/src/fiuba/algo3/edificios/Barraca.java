package fiuba.algo3.edificios;

import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.unidades.ConstructorMarine;

public class Barraca extends EdificioEntrenadorUnidades {
	public Barraca(Jugador propietario, Posicion posicion) {
		super(propietario, posicion);
		this.vida = new Vida(0, 1000);
		this.nombre = "Barraca".intern();
		this.entrenador.agregarEntrenable(new ConstructorMarine());
	}

	@Override
	public int getCostoMineral() {
		return 150;
	}

	@Override
	public int getCostoGas() {
		return 0;
	}
	
}
