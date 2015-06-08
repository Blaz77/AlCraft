package fiuba.algo3.edificios;

import fiuba.algo3.atributos.AtributosEdificioIncrementadorPoblacion;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;


public class EdificioIncrementadorPoblacion extends Edificio {
	
	public EdificioIncrementadorPoblacion(Jugador propietario, Posicion posicion, AtributosEdificioIncrementadorPoblacion atributos) {
		super(propietario, posicion, atributos);
	}
	
	public void construccionFinalizada() {
		//PASAR A UN ESTADO! -> TODO
		propietario.aumentarCapacidadPoblacion(( (AtributosEdificioIncrementadorPoblacion) atributos).getIncrementoDePoblacion());
	}

}
