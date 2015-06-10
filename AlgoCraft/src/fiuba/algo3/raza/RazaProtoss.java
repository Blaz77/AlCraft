package fiuba.algo3.raza;

import fiuba.algo3.atributos.jugador.AtributosJugador;
import fiuba.algo3.atributos.jugador.AtributosJugadorProtoss;

class RazaProtoss implements Raza {

	private AtributosJugadorProtoss atributos;
	
	public RazaProtoss(){
		this.atributos = new AtributosJugadorProtoss();
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

	public AtributosJugadorProtoss getAtributos() {
		return this.atributos;
	}
	
}