package factories;

import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.juego.Jugador;

public interface EdificiosAbstractFactory {
	
	// crear/construir
	// Despues pasar Jugador al constructor
	
	public Edificio crearRecolectorGasVespeno(Jugador jugador, int x, int y);
	
	public Edificio crearRecolectorMineral(Jugador jugador, int x, int y);
	
	public Edificio crearIncrementadorPoblacion(Jugador jugador, int x, int y);
	
	public Edificio crearEntrenadorUnidadesBasicas(Jugador jugador, int x, int y);
	// Unidades Tier 1
	
	public Edificio crearEntrenadorUnidadesIntermedias(Jugador jugador, int x, int y);
	// Unidades Tier 2 
	
	public Edificio crearEntrenadorUnidadesAvanzadas(Jugador jugador, int x, int y);
	// Unidades Tier 3

}
