package fiuba.algo3.unidades;

import fiuba.algo3.componentes.EntrenadorUnidades;
import fiuba.algo3.excepciones.GasVespenoInsuficiente;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.SuministroInsuficiente;
import fiuba.algo3.excepciones.UnidadFinalizadaException;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;


/* Una instancia de ConstructorUnidadX, heredera de esta, se encarga de controlar 
 * que el jugador a usarlo tenga los recursos para spawnear una UnidadX.
 * */

// TODO Permitirle al constructor crear procesos de entrenador multiples
public abstract class Constructor {
	
	protected int costoMinerales;
	protected int costoGas;
	protected int costoPoblacion;
	protected int turnosNecesarios;
	//
	private int turnosRestantes; //OJO ACA!
	// -> SI SE PERMITE EN SIMULTANEO ES NECESARIO HACER COPIAS
	// -> ahora funciona porque es de a uno.
	protected EntrenadorUnidades entrenador;

	abstract public void liberarUnidad(Jugador propietario, Posicion posicion);//aca va un return new Marine()
	
	abstract public Constructor copia();
	
	public void crear(Jugador propietario){
		
		// Si no hay requisitos, se pasa una excepcion.
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
