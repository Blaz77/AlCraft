package fiuba.algo3.edificios;

import fiuba.algo3.atributos.AtributosTerran;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class Fabrica extends EdificioEntrenadorUnidades {

	public Fabrica(Jugador propietario, Posicion posicion) {
		super(propietario, posicion, 
				((AtributosTerran)propietario.getAtributos()).getFabrica());
		this.vida = new Vida(0, ((AtributosTerran)propietario.getAtributos()).getFabrica());
		this.nombre = "Fabrica".intern();
	}

	@Override
	public int getCostoMineral() {
		return 200;
	}

	@Override
	public int getCostoGas() {
		return 100;
	}
	
}
