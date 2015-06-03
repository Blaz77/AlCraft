package fiuba.algo3.edificios;

public interface EdificiosAbstractFactory {
	
	// crear/construir
	
	public Edificio crearRecolectorGasVespeno();
	
	public Edificio crearRecolectorMineral();
	
	public Edificio crearIncrementadorPoblacion();
	
	public Edificio crearEntrenadorUnidadesBasicas();
	// Unidades Tier 1
	
	public Edificio crearEntrenadorUnidadesIntermedias();
	// Unidades Tier 2 
	
	public Edificio crearEntrenadorUnidadesAvanzadas();
	// Unidades Tier 3

}
