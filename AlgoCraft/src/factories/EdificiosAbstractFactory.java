//TODO Clase sin usar!

package factories;

import fiuba.algo3.edificios.EdificioEntrenadorUnidades;
import fiuba.algo3.edificios.EdificioIncrementadorPoblacion;
import fiuba.algo3.edificios.EdificioRecolectorGasVespeno;
import fiuba.algo3.edificios.EdificioRecolectorMineral;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public interface EdificiosAbstractFactory {
	
	// crear/construir
	// Despues pasar Jugador al constructor
	
	public EdificioRecolectorGasVespeno crearRecolectorGasVespeno(Jugador jugador, Posicion posicion);
	
	public EdificioRecolectorMineral crearRecolectorMineral(Jugador jugador, Posicion posicion);
	
	public EdificioIncrementadorPoblacion crearIncrementadorPoblacion(Jugador jugador, Posicion posicion);
	
	public EdificioEntrenadorUnidades crearEntrenadorUnidadesBasicas(Jugador jugador, Posicion posicion);
	// Unidades Tier 1
	
	public EdificioEntrenadorUnidades crearEntrenadorUnidadesIntermedias(Jugador jugador, Posicion posicion);
	// Unidades Tier 2 
	
	public EdificioEntrenadorUnidades crearEntrenadorUnidadesAvanzadas(Jugador jugador, Posicion posicion);
	// Unidades Tier 3

}
