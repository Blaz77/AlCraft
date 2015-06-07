package fiuba.algo3.A_BORRAR;

import fiuba.algo3.juego.Jugador;

public class TrabajoRecoleccionMineral extends TrabajoRecoleccion {
	
	public TrabajoRecoleccionMineral(int cantidadPorTurno, Jugador beneficiario) {
		super(cantidadPorTurno, beneficiario);
		// TODO Auto-generated constructor stub
	}

	public Trabajo pasarTurno(){
		(this.beneficiario).agregarMinerales(this.cantPorTurnoARecolectar);
		return this;
	}

}
