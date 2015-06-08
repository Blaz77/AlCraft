package fiuba.algo3.unidades;

import fiuba.algo3.atributos.AtributosJugador;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class NaveDeTransporte extends Unidad {

	public NaveDeTransporte(Jugador propietario, Posicion posicion){
		super(propietario, posicion, 
				((AtributosJugador)propietario.getAtributos()).getNaveDeTransporte());
		this.nombre = "Nave de Transporte".intern();
	}
}
