package fiuba.algo3.raza;

import fiuba.algo3.atributos.Atributos;

public interface Raza {
	public Object construccionesDisponibles();
	
	public Object unidadesDisponibles();
	
	public TipoRaza getTipoRaza();
	
	public Atributos getAtributos();
	
	// etc
}
