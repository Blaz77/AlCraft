package fiuba.algo3.A_BORRAR;

import fiuba.algo3.juego.Jugador;

public abstract class TrabajoRecoleccion implements Trabajo {
	
	int cantPorTurnoARecolectar;
	Jugador beneficiario;
	
	public TrabajoRecoleccion(int cantidadPorTurno, Jugador beneficiario){
		
		this.cantPorTurnoARecolectar = cantidadPorTurno;
		this.beneficiario = beneficiario;
	}
	
	public void setAnterior(Componente trabajo){
		
	}

}
