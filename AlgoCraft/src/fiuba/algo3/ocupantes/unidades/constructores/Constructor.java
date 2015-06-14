package fiuba.algo3.ocupantes.unidades.constructores;

import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.IEntrenadorUnidades;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.EdificioEntrenadorUnidades;


/* Una instancia de ConstructorUnidadX, heredera de esta, se encarga de controlar 
 * que el jugador a usarlo tenga los recursos para spawnear una UnidadX.
 * */

// TODO Permitirle al constructor crear procesos de entrenador multiples
public class Constructor {
	
	private AtributosUnidad atributos;
	private IEntrenadorUnidades entrenador;
	
	public Constructor(IEntrenadorUnidades entrenador, AtributosUnidad atributos) {
		this.atributos = atributos;
		this.entrenador = entrenador;
	}
	
	public boolean puedeCrear() { // puedeComprar / verificarCostos
		return this.entrenador.puedeEntrenar(this.atributos);
	}
	
	public void crear(){
		this.entrenador.entrenar(this.atributos);
	}

}
