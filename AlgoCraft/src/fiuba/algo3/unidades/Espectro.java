package fiuba.algo3.unidades;

import fiuba.algo3.atributos.AtributosTerran;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.mapa.recurso.TipoOcupante;

public class Espectro extends UnidadAtaque {

	public Espectro(Jugador propietario, Posicion posicion){
		super(propietario, posicion, 
				((AtributosTerran)propietario.getAtributos()).getEspectro());
		this.nombre = "Espectro".intern();
	}
	

	@Override
	public TipoOcupante getTipo() {
		return TipoOcupante.UNIDAD;
	}
}
