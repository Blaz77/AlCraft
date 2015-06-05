package fiuba.algo3.edificios;

import fiuba.algo3.componentes.VidaSinEscudo;
import fiuba.algo3.juego.*;

public class DepositoDeSuministros extends EdificioIncrementadorPoblacion {

	public DepositoDeSuministros(Jugador propietario, int x, int y) {
		super(propietario, x, y);
		this.vida = new VidaSinEscudo(500);
		this.nombre = "Deposito De Suministros";
	}
	
}
