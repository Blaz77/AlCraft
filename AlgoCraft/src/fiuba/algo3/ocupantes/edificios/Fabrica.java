package fiuba.algo3.ocupantes.edificios;

import fiuba.algo3.atributos.jugador.AtributosJugador;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class Fabrica extends EdificioEntrenadorUnidades {

	public Fabrica(Jugador propietario, Posicion posicion) {
		super(propietario, posicion, 
				((AtributosJugador)propietario.getAtributos())
				.getEntrenadorUnidadesIntermedias());
		this.vida = new Vida(0, ((AtributosJugador)propietario.getAtributos())
				.getEntrenadorUnidadesIntermedias());
	}
	
}
