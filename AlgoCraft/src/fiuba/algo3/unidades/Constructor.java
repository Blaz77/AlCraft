package fiuba.algo3.unidades;

import fiuba.algo3.edificios.EntrenadorUnidades;
import fiuba.algo3.excepciones.GasVespenoInsuficiente;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.SuministroInsuficiente;
import fiuba.algo3.excepciones.UnidadFinalizadaException;
import fiuba.algo3.juego.Jugador;

public abstract class Constructor {
	
	protected int costoMinerales;
	protected int costoGas;
	protected int costoPoblacion;
	protected int turnosNecesarios;
	private int turnosRestantes;
	protected EntrenadorUnidades entrenador;

	abstract public void liberarUnidad(Jugador j, int x, int y);//aca va un return new Marine()
	
	abstract public Constructor copia();
	
	public void crear(Jugador propietario){
		verificarRequisitos(propietario);
		propietario.agregarMinerales(-this.costoMinerales);
		propietario.agregarGasVespeno(-this.costoGas);
		propietario.aumentarPoblacion(this.costoPoblacion);
		this.turnosRestantes = this.turnosNecesarios;
		entrenador.entrenar(this);
		
	}
	
	public void pasarTurno(){
		this.turnosRestantes--;
		if (this.turnosRestantes == 0) {
			this.turnosRestantes = this.turnosNecesarios;
			throw new UnidadFinalizadaException();
		}
	}
	
	/* Verifica si hay recursos y poblacion necesaria para crear la unidad.
	 * Si no se cumple, lanza una excepcion
	 */
	public void verificarRequisitos(Jugador jugador) {
		if (jugador.getCapacidadPoblacion() - jugador.getPoblacion() < this.costoPoblacion) 
			throw new SuministroInsuficiente();
		if (jugador.getMinerales() < this.costoMinerales) 
			throw new MineralInsuficiente();
		if (jugador.getGasVespeno() < this.costoGas) 
			throw new GasVespenoInsuficiente();
	}
	
	public void setEntrenador(EntrenadorUnidades entrenador){
		this.entrenador = entrenador;		
	}
}
