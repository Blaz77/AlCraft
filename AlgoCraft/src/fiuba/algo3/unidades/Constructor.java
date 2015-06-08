package fiuba.algo3.unidades;

import fiuba.algo3.atributos.AtributosUnidad;
import fiuba.algo3.edificios.EdificioEntrenadorUnidades;
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
	
	protected AtributosUnidad atributos;
	protected EdificioEntrenadorUnidades entrenador;

	//aca va un return new Marine()
	abstract public void liberarUnidad(Jugador propietario, Posicion posicion);
	
	//aca debe tomar del jugador el atributo correspondiente para lo que 
	//se va a entrenar
	abstract public void setAtributos();
	
	public void setEdificioEntrenador(EdificioEntrenadorUnidades entrenador){
		this.entrenador = entrenador;
		this.setAtributos();
	}

	public int getTurnosConstruccion() {
		return this.atributos.getTurnosConstruccion();
	}
	
	public void crear(){
		
		Jugador propietario = entrenador.getPropietario();
		// Si no hay requisitos, se pasa una excepcion.
		verificarRequisitos(propietario);
		
		propietario.agregarMinerales(-this.atributos.getCostoMineral());
		propietario.agregarGasVespeno(-this.atributos.getCostoGasVespeno());
		propietario.aumentarPoblacion(this.atributos.getCostoPoblacion());
		
		entrenador.entrenar(this);
		
	}
	
	/* Verifica si hay recursos y poblacion necesaria para crear la unidad.
	 * Si no se cumple, lanza una excepcion
	 */
	public void verificarRequisitos(Jugador jugador) {
		if (jugador.getCapacidadPoblacion() - jugador.getPoblacion() < this.atributos.getCostoPoblacion()) 
			throw new SuministroInsuficiente();
		if (jugador.getMinerales() < this.atributos.getCostoMineral()) 
			throw new MineralInsuficiente();
		if (jugador.getGasVespeno() < this.atributos.getCostoGasVespeno()) 
			throw new GasVespenoInsuficiente();
	}

}
