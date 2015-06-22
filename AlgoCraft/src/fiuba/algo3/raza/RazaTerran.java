package fiuba.algo3.raza;

import fiuba.algo3.atributos.jugador.AtributosJugador;
import fiuba.algo3.atributos.jugador.AtributosJugadorTerran;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.ocupantes.Tipo;

class RazaTerran implements Raza {
	
	private AtributosJugadorTerran atributos = new AtributosJugadorTerran();
	private EdificiosFactory edificador = new EdificiosFactory();
	
	public RazaTerran(){
		this.edificador.denegarConstruccionEdificio(Tipo.FABRICA);
		this.edificador.denegarConstruccionEdificio(Tipo.PUERTO_ESTELAR_TERRAN);
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
	
	public EdificiosFactory getEdificador() {
		return this.edificador;
	}
	
}