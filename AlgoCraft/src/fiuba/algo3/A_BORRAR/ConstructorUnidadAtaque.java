package fiuba.algo3.A_BORRAR;

import fiuba.algo3.atributos.unidades.AtributosUnidadAtaque;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.unidades.UnidadAtaque;

public abstract class ConstructorUnidadAtaque extends Constructor {

	@Override
	public void liberarUnidad(Jugador propietario, Posicion posicion) {
		new UnidadAtaque(propietario, posicion, (AtributosUnidadAtaque)this.atributos);
	}

}
