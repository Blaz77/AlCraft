package fiuba.algo3.unidades;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class Espectro extends Unidad {

	public Espectro(Jugador propietario, Posicion posicion){
		super(propietario, posicion);
		this.nombre = "Espectro".intern();
	}
}
