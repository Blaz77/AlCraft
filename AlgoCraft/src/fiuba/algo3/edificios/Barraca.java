package fiuba.algo3.edificios;

import fiuba.algo3.tp_final.Jugador;
import fiuba.algo3.unidades.ConstructorMarine;

public class Barraca extends Edificio {

	public Barraca(Jugador propietario, int x, int y) {
		super(propietario, x, y);
		this.vida = new VidaSinEscudo(1000);
		this.entrenador = new EntrenadorUnidades(this);
		this.trabajo = this.entrenador;
		this.nombre = "Barraca";
		this.entrenador.agregarEntrenable(new ConstructorMarine());
	}
	
	
}
