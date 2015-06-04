package fiuba.algo3.unidades;

import fiuba.algo3.edificios.EntrenadorUnidades;
import fiuba.algo3.excepciones.UnidadFinalizadaException;
import fiuba.algo3.tp_final.Jugador;

public abstract class Constructor {
	
	protected int costoMinerales;
	protected int costoGas;
	protected int costoPoblacion;
	protected int turnosNecesarios;
	protected EntrenadorUnidades entrenador;

	abstract public void liberarUnidad(Jugador j, int x, int y);//aca va un return new Marine()
	
	abstract public Constructor copia();
	
	public void crear(){
		//chequear Requisitos
		// if (!this.cumpleRequisitos()) throw Exception;
		entrenador.entrenar(this.copia());
		
	}
	
	public void pasarTurno(){
		this.turnosNecesarios--;
		if (this.turnosNecesarios == 0) throw new UnidadFinalizadaException();
	}
	
	abstract public boolean cumpleRequisitos();
	
	public void setEntrenador(EntrenadorUnidades entrenador){
		this.entrenador = entrenador;		
	}
}
