package fiuba.algo3.edificios;

public class TrabajoConstruccion implements Trabajo {
	
	int cantVidaSumadaPorTurno;
	Edificio construccion;

	//private int regeneracionEntreTurnos;
	//private Vida vidaALlenar;
	private Trabajo trabajoASetear;
	
	public TrabajoConstruccion(int turnosParaCompletar, Edificio construccion){
		this.cantVidaSumadaPorTurno = (int) Math.ceil(construccion.getVidaMaxima() / (double) turnosParaCompletar);
		this.construccion = construccion;
		//this.vidaALlenar = vida;
		//this.regeneracionEntreTurnos = vida.getVidaMaxima() / this.turnos;
	}
	
	public Trabajo pasarTurno() {
		construccion.regenerarVida(cantVidaSumadaPorTurno);
		if (construccion.getVida() == construccion.getVidaMaxima()) {
			construccion.construccionFinalizada();
			return this.trabajoASetear;
		}
		return this;
	}

	public void setAnterior(Componente componenteAnterior) {
		this.trabajoASetear = (Trabajo) componenteAnterior;		
	}
	

}
