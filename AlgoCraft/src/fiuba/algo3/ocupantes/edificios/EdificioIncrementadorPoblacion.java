package fiuba.algo3.ocupantes.edificios;

import fiuba.algo3.atributos.edificios.AtributosEdificioIncrementadorPoblacion;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;


public class EdificioIncrementadorPoblacion extends Edificio {
	
	public EdificioIncrementadorPoblacion(Jugador propietario, Posicion posicion, AtributosEdificioIncrementadorPoblacion atributos) {
		super(propietario, posicion, atributos);
	}
	
	public int getIncrementoDePoblacion(){
		return ((AtributosEdificioIncrementadorPoblacion)this.atributos).getIncrementoDePoblacion();
	}

}
