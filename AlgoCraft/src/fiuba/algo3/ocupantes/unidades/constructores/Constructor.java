package fiuba.algo3.ocupantes.unidades.constructores;

import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.EdificioEntrenadorUnidades;


/* Una instancia de ConstructorUnidadX, heredera de esta, se encarga de controlar 
 * que el jugador a usarlo tenga los recursos para spawnear una UnidadX.
 * */

// TODO Permitirle al constructor crear procesos de entrenador multiples
public abstract class Constructor {
	
	protected AtributosUnidad atributos;
	protected EdificioEntrenadorUnidades entrenador;

	//aca va un return new Unidad*Tipo*()
	abstract public void liberarUnidad(Jugador propietario, Posicion posicion);
	
	//aca debe tomar del jugador el atributo correspondiente para lo que 
	//se va a entrenar
	abstract public void setAtributos();
	
	public void setEdificioEntrenador(EdificioEntrenadorUnidades entrenador){
		this.entrenador = entrenador;
		this.setAtributos();
	}

	public int getTurnosConstruccion() {
		return this.atributos.getCosto().getTurnosConstruccion();
	}
	
	public void crear(){
		entrenador.getPropietario().comprar(atributos.getCosto().getCostoMineral(),
				atributos.getCosto().getCostoGasVespeno(), atributos.getCostoPoblacion());
		entrenador.entrenar(this);
	}

}
