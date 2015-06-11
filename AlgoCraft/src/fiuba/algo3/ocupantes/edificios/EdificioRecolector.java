package fiuba.algo3.ocupantes.edificios;

import fiuba.algo3.atributos.edificios.AtributosEdificioRecolector;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class EdificioRecolector extends Edificio {
		
	public EdificioRecolector(Jugador propietario, Posicion posicion,
			AtributosEdificioRecolector atributos) {
		super(propietario, posicion, atributos);
	}

	public int getCantARecolectarPorTurno() {
		return ((AtributosEdificioRecolector)this.atributos).getCantARecolectarPorTurno();
	}
}