package fiuba.algo3.ocupantes;

import fiuba.algo3.excepciones.RecursoAusente;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.recurso.Tipo;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;

// CeldaVacia == RecursoNull
public class CeldaVacia implements Ocupante {

	private Posicion posicion;
	
	public CeldaVacia(Posicion posicion){
		this.posicion = posicion;
	}
	
	public Tipo getTipo() {
		return Tipo.CELDA_VACIA;
	}

	public TipoOcupante getTipoOcupante() {
		return Tipo.CELDA_VACIA.getTipoOcupante();
	}
	
	public Posicion getPosicion() {
		return posicion;
	}

	public boolean puedeCambiarsePor(Ocupante otroOcupante) {
		return !otroOcupante.debeOcuparRecurso();
	}
	
	public void lanzarExcepcionDeCambio() {
		throw new RecursoAusente();
	}

	public boolean puedeOcuparTierra() {
		return true;
	}

	public boolean puedeOcuparEspacio() {
		return true;
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
	
}