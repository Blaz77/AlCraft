package fiuba.algo3.A_BORRAR;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.edificios.EdificioIncrementadorPoblacion;

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
