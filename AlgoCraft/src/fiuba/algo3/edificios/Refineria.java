package fiuba.algo3.edificios;

import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class Refineria extends EdificioRecolectorGasVespeno {
	
	public Refineria(Jugador propietario, Posicion posicion) {
		super(propietario, posicion, 10);
		this.vida = new Vida(0, 750);
		this.nombre = "Refineria".intern();
	}

	@Override
	public int getCostoMineral() {
		return 200;
	}

	@Override
	public int getCostoGas() {
		return 0;
	}

}

