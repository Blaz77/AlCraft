package fiuba.algo3.A_BORRAR;

import fiuba.algo3.atributos.unidades.AtributosUnidadMagica;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.unidades.UnidadMagica;

public abstract class ConstructorUnidadMagica extends Constructor {

	@Override
	public void liberarUnidad(Jugador propietario, Posicion posicion) {
		new UnidadMagica(propietario, posicion, (AtributosUnidadMagica)this.atributos);
	}

}
