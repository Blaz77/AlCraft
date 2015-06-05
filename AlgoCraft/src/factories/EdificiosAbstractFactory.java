package factories;

import fiuba.algo3.edificios.EdificioEntrenadorUnidades;
import fiuba.algo3.edificios.EdificioIncrementadorPoblacion;
import fiuba.algo3.edificios.EdificioRecolectorGasVespeno;
import fiuba.algo3.edificios.EdificioRecolectorMineral;
import fiuba.algo3.juego.Jugador;

public interface EdificiosAbstractFactory {
	
	// crear/construir
	// Despues pasar Jugador al constructor
	
	public EdificioRecolectorGasVespeno crearRecolectorGasVespeno(Jugador jugador, int x, int y);
	
	public EdificioRecolectorMineral crearRecolectorMineral(Jugador jugador, int x, int y);
	
	public EdificioIncrementadorPoblacion crearIncrementadorPoblacion(Jugador jugador, int x, int y);
	
	public EdificioEntrenadorUnidades crearEntrenadorUnidadesBasicas(Jugador jugador, int x, int y);
	// Unidades Tier 1
	
	public EdificioEntrenadorUnidades crearEntrenadorUnidadesIntermedias(Jugador jugador, int x, int y);
	// Unidades Tier 2 
	
	public EdificioEntrenadorUnidades crearEntrenadorUnidadesAvanzadas(Jugador jugador, int x, int y);
	// Unidades Tier 3

}
