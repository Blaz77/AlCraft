package fiuba.algo3.edificios;

import fiuba.algo3.componentes.TrabajoRecoleccionGasVespeno;
import fiuba.algo3.componentes.VidaSinEscudo;
import fiuba.algo3.juego.Jugador;

public class Refineria extends EdificioRecolectorGasVespeno {
	
	public Refineria(Jugador propietario, int x, int y) {
		super(propietario, x, y, 10);
		this.vida = new VidaSinEscudo(750);
		this.nombre = "Refineria";
	}

}

