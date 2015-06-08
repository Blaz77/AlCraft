package fiuba.algo3.edificios;

import fiuba.algo3.atributos.AtributosEdificioRecolectorMineral;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public abstract class EdificioRecolectorMineral extends Edificio {
	
	public EdificioRecolectorMineral(Jugador propietario, Posicion posicion, 
			AtributosEdificioRecolectorMineral atributos) {
		super(propietario, posicion, atributos);
	}

}
