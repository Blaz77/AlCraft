package fiuba.algo3.unidades;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class Marine extends Unidad {

	public Marine(Jugador propietario, Posicion posicion){
		super(propietario, posicion);
		this.nombre = "Marine".intern();
	}
}
