package fiuba.algo3.ocupantes.edificios;

import fiuba.algo3.atributos.edificios.AtributosEdificioIncrementadorPoblacion;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;


public class EdificioIncrementadorPoblacion extends Edificio {
	
	private AtributosEdificioIncrementadorPoblacion atributos;
	public EdificioIncrementadorPoblacion(Jugador propietario, Posicion posicion, AtributosEdificioIncrementadorPoblacion atributos) {
		super(propietario, posicion);
		this.atributos = atributos;
		inicializar();
	}
	
	public int getIncrementoDePoblacion(){
		return ((AtributosEdificioIncrementadorPoblacion)this.atributos).getIncrementoDePoblacion();
	}

}
