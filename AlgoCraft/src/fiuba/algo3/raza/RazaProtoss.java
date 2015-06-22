package fiuba.algo3.raza;

import fiuba.algo3.atributos.jugador.AtributosJugadorProtoss;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.ocupantes.Tipo;

class RazaProtoss implements Raza {

	private AtributosJugadorProtoss atributos = new AtributosJugadorProtoss();
	private EdificiosFactory edificador = new EdificiosFactory();
	
	public RazaProtoss(){
		this.edificador.denegarConstruccionEdificio(Tipo.PUERTO_ESTELAR_PROTOSS);
		this.edificador.denegarConstruccionEdificio(Tipo.ARCHIVOS_TEMPLARIOS);
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
	
	public EdificiosFactory getEdificador() {
		return this.edificador;
	}
}