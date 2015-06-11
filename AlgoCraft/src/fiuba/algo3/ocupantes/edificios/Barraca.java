package fiuba.algo3.ocupantes.edificios;

import fiuba.algo3.atributos.jugador.AtributosJugador;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class Barraca extends EdificioEntrenadorUnidades {
		
	public Barraca(Jugador propietario, Posicion posicion) {
		super(propietario, posicion, ((AtributosJugador)propietario.getAtributos())
				.getEntrenadorUnidadesBasicas());
		this.vida = new Vida(0, ((AtributosJugador)propietario.getAtributos())
				.getEntrenadorUnidadesBasicas());
		this.nombre = "Barraca".intern();
	}
	
}