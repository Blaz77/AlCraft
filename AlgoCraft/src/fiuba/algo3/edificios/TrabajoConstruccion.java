package fiuba.algo3.edificios;

public class TrabajoConstruccion implements Trabajo {

	private int turnos;
	
	public TrabajoConstruccion(int turnosParaCompletar){
		this.turnos = turnosParaCompletar;
	}
	
	public void pasarTurno() {
		this.turnos--;
	}
	

}
