package fiuba.algo3.edificios;

import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.unidades.ConstructorEspectro;
import fiuba.algo3.unidades.ConstructorNaveDeCiencia;
import fiuba.algo3.unidades.ConstructorNaveDeTransporte;

public class PuertoEstelar extends EdificioEntrenadorUnidades {

	public PuertoEstelar(Jugador propietario, Posicion posicion) {
		super(propietario, posicion);
		this.vida = new Vida(0, 1300);
		this.nombre = "Puerto Estelar";
		this.entrenador.agregarEntrenable(new ConstructorEspectro());
		this.entrenador.agregarEntrenable(new ConstructorNaveDeCiencia());
		this.entrenador.agregarEntrenable(new ConstructorNaveDeTransporte());
	}
	
	
}
