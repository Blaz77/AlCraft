package fiuba.algo3.edificios;

import fiuba.algo3.atributos.AtributosEdificioEntrenadorUnidades;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class ConstruccionEntrenadorUnidades extends Construccion {

	public ConstruccionEntrenadorUnidades(Jugador propietario, Posicion posicion,
			AtributosEdificioEntrenadorUnidades atributos) {
		super(propietario, posicion, atributos);
	}

	@Override
	protected Edificio liberarEdificio() {
		return new EdificioEntrenadorUnidades(this.propietario, this.posicion, 
				(AtributosEdificioEntrenadorUnidades)this.atributos);
	}

}