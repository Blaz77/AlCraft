package fiuba.algo3.raza;

import fiuba.algo3.atributos.Atributos;
import fiuba.algo3.atributos.AtributosTerran;

class RazaTerran implements Raza {
	
	private Atributos atributos;
	
	public RazaTerran(){
		this.atributos = new AtributosTerran();
	}

	public Object construccionesDisponibles() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object unidadesDisponibles() {
		// TODO Auto-generated method stub
		return null;
	}

	public TipoRaza getTipoRaza() {
		return TipoRaza.TERRAN;
	}

	public Atributos getAtributos() {
		return this.atributos;
	}
	
}