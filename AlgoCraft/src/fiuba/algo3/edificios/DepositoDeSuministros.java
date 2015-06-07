package fiuba.algo3.edificios;

import fiuba.algo3.atributos.AtributosTerran;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.Posicion;

public class DepositoDeSuministros extends EdificioIncrementadorPoblacion {

	public DepositoDeSuministros(Jugador propietario, Posicion posicion) {
		super(propietario, posicion, 
				((AtributosTerran)propietario.getAtributos()).getDepositoDeSuministros());
		this.vida = new Vida(0, ((AtributosTerran)propietario.getAtributos()).getDepositoDeSuministros());
		this.nombre = "Deposito De Suministros".intern();
	}

	@Override
	public int getCostoMineral() {
		return 100;
	}

	@Override
	public int getCostoGas() {
		return 0;
	}
	
}
