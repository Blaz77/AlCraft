package fiuba.algo3.unidades;

import fiuba.algo3.atributos.AtributosUnidadTransporte;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public abstract class ConstructorUnidadTransporte extends Constructor {

	@Override
	public void liberarUnidad(Jugador propietario, Posicion posicion) {	
		new UnidadTransporte(propietario, posicion, (AtributosUnidadTransporte)this.atributos);
	}

}
