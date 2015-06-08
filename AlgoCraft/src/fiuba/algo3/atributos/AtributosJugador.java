package fiuba.algo3.atributos;

//No tiene sentido que se llame AtributosJugador
//Como mucho para eso seria AtributosRaza*nombre_raza*
//Tiene mas que ver con la raza

public interface AtributosJugador {
	
	public AtributosEdificioRecolectorMineral getRecolectorMineral();
	
	public AtributosEdificioRecolectorGasVespeno getRecolectorGasVespeno();
	
	public AtributosEdificioIncrementadorPoblacion getIncrementadorPoblacion();
	
	public AtributosEdificioEntrenadorUnidades getEntrenadorUnidadesBasicas();
	// Unidades Tier 1
	
	public AtributosEdificioEntrenadorUnidades getEntrenadorUnidadesIntermedias();
	// Unidades Tier 2 
	
	public AtributosEdificioEntrenadorUnidades getEntrenadorUnidadesAvanzadas();
	// Unidades Tier 3

	public AtributosUnidadAtaque getInfanteriaLivianaTerrestre();
	// Protoss: Zealot, Terran: Marine, Zerg: Zergling
	
	public AtributosUnidadAtaque getInfanteriaPesadaTerrestre();
	// Protoss: Dragoon, Terran: Goliath, Zerg: Hydralisk
	
	public AtributosUnidadAtaque getInfanteriaPesadaArea();
	// Protoss: Scout, Terran: Ghost/Spectre, Zerg: Mutalisk
	
	public AtributosUnidadMagica getInfanteriaMagica(); //Elite, Spell Casters
	// Protoss: High Templar, Terran: Science Vessel, Zerg: Queen
	
	public AtributosUnidadTransporte getTransporte();
	// Protoss/Terran: Nave Transporte, Zerg: Amo Supremo
	
}