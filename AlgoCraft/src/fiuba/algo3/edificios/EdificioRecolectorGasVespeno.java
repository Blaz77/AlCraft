package fiuba.algo3.edificios;

import fiuba.algo3.atributos.AtributosEdificioRecolector;
import fiuba.algo3.atributos.AtributosEdificioRecolectorGasVespeno;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public abstract class EdificioRecolectorGasVespeno extends Edificio {
		
	public EdificioRecolectorGasVespeno(Jugador propietario, Posicion posicion,
			AtributosEdificioRecolectorGasVespeno atributos) {
		super(propietario, posicion, atributos);
	}

	public void pasarTurno(){
		super.pasarTurno();
		//LUEGO PASAR LO DE ABAJO A UN ESTADO -> ESTADORECOLECTANDO
		(this.propietario).agregarGasVespeno(
				((AtributosEdificioRecolector)atributos).getCantARecolectarPorTurno());
	}

}
