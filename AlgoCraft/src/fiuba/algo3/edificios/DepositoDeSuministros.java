package fiuba.algo3.edificios;

import fiuba.algo3.tp_final.*;

public class DepositoDeSuministros extends Edificio {

	public DepositoDeSuministros(Jugador propietario, int x, int y) {
		super(propietario, x, y);
		this.vida = new VidaSinEscudo(500);
		this.nombre = "Deposito De Suministros";
		propietario.aumentarPoblacion(5);
	}
	
}
