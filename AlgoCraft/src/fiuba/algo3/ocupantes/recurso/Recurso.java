package fiuba.algo3.ocupantes.recurso;

import fiuba.algo3.excepciones.RecursoPresente;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.Ocupante;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.TipoOcupante;

public abstract class Recurso implements Ocupante {
	
	//private int cantidadRecurso = 1500; tal vez sea eterno? :D
	private Posicion posicion;
	
	public Recurso(Posicion posicion) {
		this.posicion = posicion;
	}
	
	public abstract Tipo getTipo();
	
	public abstract TipoOcupante getTipoOcupante();

	public Posicion getPosicion() {
		return posicion;
	}
	
	public boolean puedeOcuparTierra() {
		return true;
	}

	public boolean puedeOcuparEspacio() {
		return false;
	}

	public boolean debeOcuparRecurso() {
		return false;
	}

	public boolean debeOcuparMineral() {
		return false;
	}

	public boolean debeOcuparGasVespeno() {
		return false;
	}
	
	public boolean puedeCambiarsePor(Ocupante otroOcupante) {
		return otroOcupante.debeOcuparRecurso();
	}
	
	public void lanzarExcepcionDeCambio(){
		throw new RecursoPresente(this);
	}
	
}
