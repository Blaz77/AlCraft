package fiuba.algo3.ocupantes.edificios.en_construccion;

import fiuba.algo3.atributos.edificios.AtributosEdificioEntrenadorUnidades;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.edificios.EdificioEntrenadorUnidades;

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