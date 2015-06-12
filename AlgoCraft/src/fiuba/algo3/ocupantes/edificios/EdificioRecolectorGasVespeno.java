package fiuba.algo3.ocupantes.edificios;

import fiuba.algo3.atributos.edificios.AtributosEdificioRecolector;
import fiuba.algo3.atributos.edificios.AtributosEdificioRecolectorGasVespeno;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class EdificioRecolectorGasVespeno extends EdificioRecolector {
		
	public EdificioRecolectorGasVespeno(Jugador propietario, Posicion posicion,
			AtributosEdificioRecolectorGasVespeno atributos) {
		super(propietario, posicion, atributos);
	}

}
