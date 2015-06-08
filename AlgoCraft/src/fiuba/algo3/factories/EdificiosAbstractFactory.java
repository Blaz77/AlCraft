//TODO Clase sin usar!

package fiuba.algo3.factories;

import fiuba.algo3.edificios.Construccion;
import fiuba.algo3.edificios.EdificioEntrenadorUnidades;
import fiuba.algo3.edificios.EdificioIncrementadorPoblacion;
import fiuba.algo3.edificios.EdificioRecolectorGasVespeno;
import fiuba.algo3.edificios.EdificioRecolectorMineral;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

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
