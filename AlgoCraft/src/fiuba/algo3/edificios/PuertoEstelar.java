package fiuba.algo3.edificios;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.unidades.ConstructorEspectro;
import fiuba.algo3.unidades.ConstructorNaveDeCiencia;
import fiuba.algo3.unidades.ConstructorNaveDeTransporte;

public class PuertoEstelar extends Edificio {

	public PuertoEstelar(Jugador propietario, int x, int y) {
		super(propietario, x, y);
		this.vida = new VidaSinEscudo(1300);
		//this.trabajo = new TrabajoEntrenarUnidades();
		this.entrenador = new EntrenadorUnidades(this);
		this.trabajo = this.entrenador;
		this.nombre = "Puerto Estelar";
		this.entrenador.agregarEntrenable(new ConstructorEspectro());
		this.entrenador.agregarEntrenable(new ConstructorNaveDeCiencia());
		this.entrenador.agregarEntrenable(new ConstructorNaveDeTransporte());
	}
	
	
}
