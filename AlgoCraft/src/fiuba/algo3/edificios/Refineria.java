package fiuba.algo3.edificios;

import fiuba.algo3.atributos.AtributosTerran;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class Refineria extends EdificioRecolectorGasVespeno {
	
	public Refineria(Jugador propietario, Posicion posicion) {
		super(propietario, posicion, 
				((AtributosTerran)propietario.getAtributos()).getRefineria());
		this.vida = new Vida(0, ((AtributosTerran)propietario.getAtributos()).getRefineria());
		this.nombre = "Refineria".intern();
	}

	@Override
	public int getCostoMineral() {
		return 200;
	}

	@Override
	public int getCostoGas() {
		return 0;
	}

}

