//TODO Clase sin usar!

package fiuba.algo3.factories;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.en_construccion.Construccion;

public interface EdificiosAbstractFactory {
	
	// crear/construir
	// Despues pasar Jugador al constructor
	
	public Construccion crearRecolectorGasVespeno(Jugador jugador, Posicion posicion);
	
	public Construccion crearRecolectorMineral(Jugador jugador, Posicion posicion);
	
	public Construccion crearIncrementadorPoblacion(Jugador jugador, Posicion posicion);
	
	public Construccion crearEntrenadorUnidadesBasicas(Jugador jugador, Posicion posicion);
	// Unidades Tier 1
	
	public Construccion crearEntrenadorUnidadesIntermedias(Jugador jugador, Posicion posicion);
	// Unidades Tier 2 
	
	public Construccion crearEntrenadorUnidadesAvanzadas(Jugador jugador, Posicion posicion);
	// Unidades Tier 3

}
