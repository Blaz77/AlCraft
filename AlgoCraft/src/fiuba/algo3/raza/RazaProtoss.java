package fiuba.algo3.raza;

import fiuba.algo3.atributos.Atributos;
import fiuba.algo3.atributos.AtributosProtoss;

class RazaProtoss implements Raza {

	private Atributos atributos;
	
	public RazaProtoss(){
		this.atributos = new AtributosProtoss();
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
		return TipoRaza.PROTOSS;
	}

	public Atributos getAtributos() {
		return this.atributos;
	}
	
}