package fiuba.algo3.edificios;

import fiuba.algo3.tp_final.Jugador;

public abstract class TrabajoRecoleccion implements Trabajo {
	
	int cantPorTurnoARecolectar;
	Jugador beneficiario;
	
	public TrabajoRecoleccion(int cantidadPorTurno, Jugador beneficiario){
		
		this.cantPorTurnoARecolectar = cantidadPorTurno;
		this.beneficiario = beneficiario;
	}

}
