package fiuba.algo3.ocupantes.edificios;

import fiuba.algo3.atributos.jugador.AtributosJugador;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.Posicion;

public class DepositoDeSuministros extends EdificioIncrementadorPoblacion {

	public DepositoDeSuministros(Jugador propietario, Posicion posicion) {
		super(propietario, posicion, 
				((AtributosJugador)propietario.getAtributos())
				.getIncrementadorPoblacion());
		this.vida = new Vida(0, ((AtributosJugador)propietario.getAtributos())
				.getIncrementadorPoblacion());
	}
	
}
