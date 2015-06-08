package fiuba.algo3.raza;

import fiuba.algo3.atributos.AtributosJugador;
import fiuba.algo3.atributos.AtributosJugador;
import fiuba.algo3.atributos.AtributosProtoss;

class RazaProtoss implements Raza {

	private AtributosProtoss atributos;
	
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

	public AtributosProtoss getAtributos() {
		return this.atributos;
	}
	
}