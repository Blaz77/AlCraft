package fiuba.algo3.ocupantes.edificios;

import fiuba.algo3.atributos.edificios.AtributosEdificioRecolector;
import fiuba.algo3.atributos.edificios.AtributosEdificioRecolectorMineral;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class EdificioRecolectorMineral extends EdificioRecolector {
	
	public EdificioRecolectorMineral(Jugador propietario, Posicion posicion, 
			AtributosEdificioRecolectorMineral atributos) {
		super(propietario, posicion, atributos);
	}

}
