//TODO Clase sin usar!

package fiuba.algo3.factories;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Edificio;

public interface EdificiosAbstractFactory {
	
	// crear/construir
	// Despues pasar Jugador al constructor
	
	public Edificio crearRecolectorGasVespeno(Jugador jugador, Posicion posicion);
	
	public Edificio crearRecolectorMineral(Jugador jugador, Posicion posicion);
	
	public Edificio crearIncrementadorPoblacion(Jugador jugador, Posicion posicion);
	
	public Edificio crearEntrenadorUnidadesBasicas(Jugador jugador, Posicion posicion);
	// Unidades Tier 1
	
	public Edificio crearEntrenadorUnidadesIntermedias(Jugador jugador, Posicion posicion);
	// Unidades Tier 2 
	
	public Edificio crearEntrenadorUnidadesAvanzadas(Jugador jugador, Posicion posicion);
	// Unidades Tier 3

}
