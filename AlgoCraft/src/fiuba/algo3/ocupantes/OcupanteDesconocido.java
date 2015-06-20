package fiuba.algo3.ocupantes;

import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.recurso.Tipo;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;


// Solo existe para comunicacion entre MapaProxy y la Vista
public class OcupanteDesconocido implements Ocupante {

	private static OcupanteDesconocido instancia = null;
	
	public static OcupanteDesconocido getInstance() {
		if (instancia == null) {
			instancia = new OcupanteDesconocido();
		}
		return instancia;
	}
	
	public Tipo getTipo() {
		return Tipo.DESCONOCIDO;
	}
		
	public TipoOcupante getTipoOcupante() {
		return Tipo.DESCONOCIDO.getTipoOcupante();
	}

	public Posicion getPosicion() {
		throw new RuntimeException();
	}

	public boolean puedeCambiarsePor(Ocupante otroOcupante) {
		throw new RuntimeException();
	}

	public void lanzarExcepcionDeCambio() {
		throw new RuntimeException();
	}

	public boolean puedeOcuparTierra() {
		throw new RuntimeException();
	}

	public boolean puedeOcuparEspacio() {
		throw new RuntimeException();
	}

	public boolean debeOcuparRecurso() {
		throw new RuntimeException();
	}

	public boolean debeOcuparMineral() {
		throw new RuntimeException();
	}

	public boolean debeOcuparGasVespeno() {
		throw new RuntimeException();
	}

}
