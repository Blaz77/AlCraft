package fiuba.algo3.edificios;

import fiuba.algo3.atributos.AtributosEdificio;
import fiuba.algo3.atributos.AtributosEdificioIncrementadorPoblacion;
import fiuba.algo3.atributos.AtributosEdificioRecolector;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class ConstruccionIncrementadorPoblacion extends Construccion {

	public ConstruccionIncrementadorPoblacion(Jugador propietario,
			Posicion posicion, AtributosEdificioIncrementadorPoblacion atributos) {
		super(propietario, posicion, atributos);
	}

	@Override
	public Edificio liberarEdificio() {
		return new EdificioIncrementadorPoblacion(this.propietario, this.posicion, 
				(AtributosEdificioIncrementadorPoblacion)this.atributos);
	}


}
