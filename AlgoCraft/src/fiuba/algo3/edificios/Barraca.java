package fiuba.algo3.edificios;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.unidades.ConstructorMarine;

public class Barraca extends EdificioEntrenadorUnidades {

	public Barraca(Jugador propietario, int x, int y) {
		super(propietario, x, y);
		this.vida = new VidaSinEscudo(1000);
		this.nombre = "Barraca";
		this.entrenador.agregarEntrenable(new ConstructorMarine());
	}
	
	
}
