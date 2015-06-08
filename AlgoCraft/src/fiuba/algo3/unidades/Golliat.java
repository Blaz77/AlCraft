package fiuba.algo3.unidades;

import fiuba.algo3.atributos.AtributosJugador;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class Golliat extends UnidadAtaque {
	
	public Golliat(Jugador propietario, Posicion posicion){
		super(propietario, posicion, 
				((AtributosJugador)propietario.getAtributos()).getGolliat());
		this.nombre = "Golliat".intern();
	}
}
