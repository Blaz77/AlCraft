package fiuba.algo3.A_BORRAR;

import fiuba.algo3.edificios.Edificio;


// Strategy que, como todo trabajo, implementa el estado y 
// comportamiento de quien lo contiene entre turnos.
// En este caso, implementa la construccion del edificio.

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
