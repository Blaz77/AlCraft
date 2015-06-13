package fiuba.algo3.A_BORRAR;

import fiuba.algo3.atributos.jugador.AtributosJugador;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class Refineria extends EdificioRecolectorGasVespeno {
	
	public Refineria(Jugador propietario, Posicion posicion) {
		super(propietario, posicion, 
				((AtributosJugador)propietario.getAtributos())
				.getRecolectorGasVespeno());
		this.vida = new Vida(0, ((AtributosJugador)propietario.getAtributos())
				.getRecolectorGasVespeno().getAtributosVida());
	}

}

