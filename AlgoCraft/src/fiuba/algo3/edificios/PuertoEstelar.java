package fiuba.algo3.edificios;

import fiuba.algo3.atributos.AtributosJugador;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class PuertoEstelar extends EdificioEntrenadorUnidades {

	public PuertoEstelar(Jugador propietario, Posicion posicion) {
		super(propietario, posicion, 
				((AtributosJugador)propietario.getAtributos()).getPuertoEstelar());
		this.vida = new Vida(0, ((AtributosJugador)propietario.getAtributos()).getPuertoEstelar());
		this.nombre = "Puerto Estelar".intern();
	}
	
	
}
