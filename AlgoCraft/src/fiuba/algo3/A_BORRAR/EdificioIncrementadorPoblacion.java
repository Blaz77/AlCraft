package fiuba.algo3.A_BORRAR;

import fiuba.algo3.A_BORRAR.AtributosEdificioIncrementadorPoblacion;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;


public class EdificioIncrementadorPoblacion extends Edificio {
	
	public EdificioIncrementadorPoblacion(Jugador propietario, Posicion posicion, AtributosEdificio atributos) {
		super(propietario, posicion, atributos);
	}
	
	public int getIncrementoDePoblacion(){
		return ((AtributosEdificioIncrementadorPoblacion)this.atributos).getIncrementoDePoblacion();
	}

}
