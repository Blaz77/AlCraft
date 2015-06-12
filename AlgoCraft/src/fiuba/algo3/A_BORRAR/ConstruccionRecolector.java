package fiuba.algo3.A_BORRAR;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.edificios.EdificioRecolector;

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
