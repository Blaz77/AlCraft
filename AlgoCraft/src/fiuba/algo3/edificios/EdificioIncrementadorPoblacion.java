package fiuba.algo3.edificios;

import fiuba.algo3.atributos.AtributosEdificioIncrementadorPoblacion;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;


public abstract class EdificioIncrementadorPoblacion extends Edificio {
	
	public EdificioIncrementadorPoblacion(Jugador propietario, Posicion posicion, AtributosEdificioIncrementadorPoblacion atributos) {
		super(propietario, posicion, atributos);
		//SUPER-HIPER-TEMPORAL: TODO: despues pasa a un estado!
		this.construccionFinalizada(atributos.getIncrementoDePoblacion());
	}
	
	public void construccionFinalizada(int cant) {
		propietario.aumentarPoblacion(cant);
	}

}
