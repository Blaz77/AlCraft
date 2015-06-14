package fiuba.algo3.atributos.jugador;

import fiuba.algo3.A_BORRAR.AtributosEdificioIncrementadorPoblacion;
import fiuba.algo3.A_BORRAR.AtributosEdificioRecolectorGasVespeno;
import fiuba.algo3.A_BORRAR.AtributosEdificioRecolectorMineral;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.atributos.edificios.AtributosEdificioEntrenadorUnidades;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.atributos.unidades.AtributosUnidadAtaque;
import fiuba.algo3.atributos.unidades.AtributosUnidadMagica;
import fiuba.algo3.atributos.unidades.AtributosUnidadTransporte;

//No tiene sentido que se llame AtributosJugador
//Como mucho para eso seria AtributosRaza*nombre_raza*
//Tiene mas que ver con la raza

public interface AtributosJugador {
	
	public AtributosEdificio getRecolectorMineral();
	
	public AtributosEdificio getRecolectorGasVespeno();
	
	public AtributosEdificio getIncrementadorPoblacion();
	
	public AtributosEdificioEntrenadorUnidades getEntrenadorUnidadesBasicas();
	// Unidades Tier 1
	
	public AtributosEdificioEntrenadorUnidades getEntrenadorUnidadesIntermedias();
	// Unidades Tier 2 
	
	public AtributosEdificioEntrenadorUnidades getEntrenadorUnidadesAvanzadas();
	// Unidades Tier 3

	public AtributosUnidad getInfanteriaLivianaTerrestre();
	// Protoss: Zealot, Terran: Marine, Zerg: Zergling
	
	public AtributosUnidad getInfanteriaPesadaTerrestre();
	// Protoss: Dragoon, Terran: Goliath, Zerg: Hydralisk
	
	public AtributosUnidad getInfanteriaPesadaArea();
	// Protoss: Scout, Terran: Ghost/Spectre, Zerg: Mutalisk
	
	public AtributosUnidadMagica getInfanteriaMagica(); //Elite, Spell Casters
	// Protoss: High Templar, Terran: Science Vessel, Zerg: Queen
	
	public AtributosUnidadTransporte getTransporte();
	// Protoss/Terran: Nave Transporte, Zerg: Amo Supremo
	
}
