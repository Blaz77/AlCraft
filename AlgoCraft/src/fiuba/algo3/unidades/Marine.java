package fiuba.algo3.unidades;

import fiuba.algo3.atributos.AtributosJugador;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class Marine extends UnidadAtaque {

	public Marine(Jugador propietario, Posicion posicion){
		super(propietario, posicion, 
				((AtributosJugador)propietario.getAtributos()).getMarine());
		this.nombre = "Marine".intern();
	}
}
