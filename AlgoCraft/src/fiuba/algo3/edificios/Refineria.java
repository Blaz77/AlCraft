package fiuba.algo3.edificios;

import fiuba.algo3.componentes.TrabajoRecoleccionGasVespeno;
import fiuba.algo3.componentes.VidaSinEscudo;
import fiuba.algo3.juego.Jugador;

public class Refineria extends Edificio {
	
	public Refineria(Jugador propietario, int x, int y) {
		super(propietario, x, y);
		this.vida = new VidaSinEscudo(750);
		this.trabajo = new TrabajoRecoleccionGasVespeno(10, propietario);
		this.nombre = "Refineria";
	}

}

