package fiuba.algo3.unidades;

import fiuba.algo3.atributos.AtributosJugador;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class ConstructorMarine extends Constructor{

	@Override
	public void liberarUnidad(Jugador j, Posicion posicion) {
		new Marine(j, posicion);
	}

	@Override
	public void setAtributos() {
		this.atributos = 
				((AtributosJugador)this.entrenador.getPropietario().getAtributos()).getMarine();		
	}

}
