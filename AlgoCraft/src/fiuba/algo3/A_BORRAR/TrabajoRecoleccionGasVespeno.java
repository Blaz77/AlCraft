package fiuba.algo3.componentes;

import fiuba.algo3.juego.Jugador;

public class TrabajoRecoleccionGasVespeno extends TrabajoRecoleccion {
	
	public TrabajoRecoleccionGasVespeno(int cantidadPorTurno, Jugador beneficiario) {
		super(cantidadPorTurno, beneficiario);
		// TODO Auto-generated constructor stub
	}

	public Trabajo pasarTurno(){
		(this.beneficiario).agregarGasVespeno(this.cantPorTurnoARecolectar);
		return this;
	}

}