package fiuba.algo3.atributos.jugador;

import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.atributos.unidades.AtributosUnidad;

//No tiene sentido que se llame AtributosJugador
//Como mucho para eso seria AtributosRaza*nombre_raza*
//Tiene mas que ver con la raza

public interface AtributosJugador {
	
	public AtributosEdificio getRecolectorMineral();
	
	public AtributosEdificio getRecolectorGasVespeno();
	
	public AtributosEdificio getIncrementadorPoblacion();
	
	public AtributosEdificio getEntrenadorUnidadesBasicas();
	// Unidades Tier 1
	
	public AtributosEdificio getEntrenadorUnidadesIntermedias();
	// Unidades Tier 2 
	
	public AtributosEdificio getEntrenadorUnidadesAvanzadas();
	// Unidades Tier 3

	public AtributosUnidad getInfanteriaLivianaTerrestre();
	// Protoss: Zealot, Terran: Marine, Zerg: Zergling
	
	public AtributosUnidad getInfanteriaPesadaTerrestre();
	// Protoss: Dragoon, Terran: Goliath, Zerg: Hydralisk
	
	public AtributosUnidad getInfanteriaPesadaArea();
	// Protoss: Scout, Terran: Ghost/Spectre, Zerg: Mutalisk
	
	public AtributosUnidad getInfanteriaMagica(); //Elite, Spell Casters
	// Protoss: High Templar, Terran: Science Vessel, Zerg: Queen
	
	public AtributosUnidad getTransporte();
	// Protoss/Terran: Nave Transporte, Zerg: Amo Supremo
	
}
