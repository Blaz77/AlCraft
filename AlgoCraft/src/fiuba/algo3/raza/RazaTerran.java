package fiuba.algo3.raza;

import fiuba.algo3.atributos.jugador.AtributosJugador;
import fiuba.algo3.atributos.jugador.AtributosJugadorTerran;

class RazaTerran implements Raza {
	
	private AtributosJugadorTerran atributos;
	
	public RazaTerran(){
		this.atributos = new AtributosJugadorTerran();
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

	public AtributosJugador getAtributos() {
		return this.atributos;
	}
	
}