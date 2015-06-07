package fiuba.algo3.edificios;

import fiuba.algo3.atributos.AtributosTerran;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class Barraca extends EdificioEntrenadorUnidades {
		
	public Barraca(Jugador propietario, Posicion posicion) {
		super(propietario, posicion, ((AtributosTerran)propietario.getAtributos()).getBarraca());
		this.vida = new Vida(0, ((AtributosTerran)propietario.getAtributos()).getBarraca());
		this.nombre = "Barraca".intern();
	}

	@Override
	public int getCostoMineral() {
		return 150;
	}

	@Override
	public int getCostoGas() {
		return 0;
	}
	
}
