package fiuba.algo3.unidades;
//TODO: Probablemente borrar esto.


// Trate de encontrar un patron entre las unidades
// con lo cual se puede hacer esta AbstractFactory
public interface UnidadFactory {
	//faltaran parametros en estos metodos
	
	public Unidad crearInfanteriaLivianaTerrestre();
	// Protoss: Zealot, Terran: Marine, Zerg: Zergling
	
	public Unidad crearInfanteriaPesadaTerrestre();
	// Protoss: Dragoon, Terran: Goliath, Zerg: Hydralisk
	
	public Unidad crearInfanteriaPesadaArea();
	// Protoss: Scout, Terran: Ghost/Spectre, Zerg: Mutalisk
	
	public Unidad crearInfanteriaMagica(); //Elite, Spell Casters
	// Protoss: High Templar, Terran: Science Vessel, Zerg: Queen
	
	public Unidad crearTransporte();
	// Protoss/Terran: Nave Transporte, Zerg: Amo Supremo
	
}
