package fiuba.algo3.edificios;

import fiuba.algo3.atributos.AtributosEdificioRecolector;
import fiuba.algo3.atributos.AtributosEdificioRecolectorGasVespeno;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public abstract class EdificioRecolectorGasVespeno extends Edificio {
		
	public EdificioRecolectorGasVespeno(Jugador propietario, Posicion posicion,
			AtributosEdificioRecolectorGasVespeno atributos) {
		super(propietario, posicion, atributos);
	}

}
