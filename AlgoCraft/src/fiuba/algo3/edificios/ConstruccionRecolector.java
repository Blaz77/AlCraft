package fiuba.algo3.edificios;

import fiuba.algo3.atributos.AtributosEdificioRecolector;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class ConstruccionRecolector extends Construccion {

	public ConstruccionRecolector(Jugador propietario, Posicion posicion,
			AtributosEdificioRecolector atributos) {
		super(propietario, posicion, atributos);
	}

	@Override
	public Edificio liberarEdificio() {
		return new EdificioRecolector(this.propietario, this.posicion, 
				(AtributosEdificioRecolector)this.atributos);
	}

}
