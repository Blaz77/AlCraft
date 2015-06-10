package fiuba.algo3.ocupante;

import fiuba.algo3.juego.Ocupante;
import fiuba.algo3.mapa.Posicion;

public abstract class ObjetoNoVivo extends Ocupante {
	
	public ObjetoNoVivo(Posicion posicion){
		this.posicion = posicion;
	}

}
