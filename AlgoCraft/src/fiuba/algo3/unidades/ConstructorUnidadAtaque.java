package fiuba.algo3.unidades;

import fiuba.algo3.atributos.AtributosUnidadAtaque;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public abstract class ConstructorUnidadAtaque extends Constructor {

	@Override
	public void liberarUnidad(Jugador propietario, Posicion posicion) {
		new UnidadAtaque(propietario, posicion, (AtributosUnidadAtaque)this.atributos);
	}

}
