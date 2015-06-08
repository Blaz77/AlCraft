package fiuba.algo3.unidades;

import fiuba.algo3.atributos.AtributosUnidadMagica;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public abstract class ConstructorUnidadMagica extends Constructor {

	@Override
	public void liberarUnidad(Jugador propietario, Posicion posicion) {
		new UnidadMagica(propietario, posicion, (AtributosUnidadMagica)this.atributos);
	}

}
