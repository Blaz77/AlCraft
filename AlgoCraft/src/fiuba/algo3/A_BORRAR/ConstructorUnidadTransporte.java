package fiuba.algo3.ocupantes.unidades.constructores;

import fiuba.algo3.atributos.unidades.AtributosUnidadTransporte;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.unidades.UnidadTransporte;

public abstract class ConstructorUnidadTransporte extends Constructor {

	@Override
	public void liberarUnidad(Jugador propietario, Posicion posicion) {	
		new UnidadTransporte(propietario, posicion, (AtributosUnidadTransporte)this.atributos);
	}

}
