package fiuba.algo3.edificios;

public class TrabajoConstruccion implements Trabajo {

	private int turnos;
	private int regeneracionEntreTurnos;
	private Vida vidaALlenar;
	private Trabajo trabajoASetear;
	
	public TrabajoConstruccion(int turnosParaCompletar, Vida vida, Trabajo trabajo){
		this.turnos = turnosParaCompletar;
		this.vidaALlenar = vida;
		this.trabajoASetear = trabajo;
		this.regeneracionEntreTurnos = vida.getVidaMaxima() / this.turnos;
	}
	
	public Trabajo pasarTurno() {
		this.vidaALlenar.regenerar(this.regeneracionEntreTurnos);
		this.turnos--;
		if (this.turnos == 0) return this.trabajoASetear;
		return this;
	}
	

}
