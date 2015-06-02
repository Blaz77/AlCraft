package fiuba.algo3.edificios;

import fiuba.algo3.tp_final.Jugador;

public class TrabajoRecoleccionMineral extends TrabajoRecoleccion {
	
	public TrabajoRecoleccionMineral(int cantidadPorTurno, Jugador beneficiario) {
		super(cantidadPorTurno, beneficiario);
		// TODO Auto-generated constructor stub
	}

	public void pasarTurno(){
		(this.beneficiario).agregarMinerales(this.cantPorTurnoARecolectar);
	}

}
