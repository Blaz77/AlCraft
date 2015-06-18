//TODO Clase sin usar!

package fiuba.algo3.factories;

import fiuba.algo3.atributos.edificios.AtributosConstruccion;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.excepciones.OrdenConstruccionViolado;
import fiuba.algo3.excepciones.RecursoAusente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;
import fiuba.algo3.terreno.TipoTerreno;

public class EdificiosFactory implements EdificiosAbstractFactory{

	private void comprarEdificio(Jugador jugador, AtributosEdificio atributos) {
		jugador.comprar(atributos.getCosto().getCostoMineral(), atributos.getCosto().getCostoGasVespeno());
	}
	
	private boolean ordenConstruccionSeCumple(Jugador jugador, String nombreEdificioRequerido) {
		for (Edificio edificio : jugador.getEdificios()) {
			if (edificio.getNombre() == nombreEdificioRequerido) return true; 
		}
		return false;
	}
	
	private Edificio crearEdificio(Jugador jugador, Posicion posicion, AtributosEdificio atributos){
		Edificio edificio = new Edificio(jugador, posicion, new AtributosConstruccion(atributos));
		// Chequeo de terreno y/o recurso:
		jugador.getMapa().verificarOcupacion(edificio, posicion);
		// Chequeo y compra 
		comprarEdificio(jugador, atributos);
		// Poner en mapa (chequeado anteriormente)
		jugador.getMapa().setOcupante(edificio, posicion);
		jugador.agregarEdificio(edificio);
		return edificio;
	}
	
	public Edificio crearRecolectorGasVespeno(Jugador jugador, Posicion posicion) {
		return crearEdificio(jugador, posicion, jugador.getAtributos().getRecolectorGasVespeno());	
	}

	public Edificio crearRecolectorMineral(Jugador jugador, Posicion posicion) {
		return crearEdificio(jugador, posicion, jugador.getAtributos().getRecolectorMineral());
	}

	public Edificio crearIncrementadorPoblacion(Jugador jugador, Posicion posicion) {
		return crearEdificio(jugador, posicion, jugador.getAtributos().getIncrementadorPoblacion());
	}

	public Edificio crearEntrenadorUnidadesBasicas(Jugador jugador, Posicion posicion) {
		return crearEdificio(jugador, posicion, jugador.getAtributos().getEntrenadorUnidadesBasicas());
	}

	public Edificio crearEntrenadorUnidadesIntermedias(Jugador jugador, Posicion posicion) {
		// Chequeo de orden construccion.
		if (! ordenConstruccionSeCumple(jugador, jugador.getAtributos().getEntrenadorUnidadesBasicas().getNombre())) {
			throw new OrdenConstruccionViolado();
		}
		return crearEdificio(jugador, posicion, jugador.getAtributos().getEntrenadorUnidadesIntermedias());
	}

	public Edificio crearEntrenadorUnidadesAvanzadas(Jugador jugador, Posicion posicion) {
		// Chequeo de orden construccion. 
		if (! ordenConstruccionSeCumple(jugador, jugador.getAtributos().getEntrenadorUnidadesIntermedias().getNombre())) {
			throw new OrdenConstruccionViolado();
		}
		return crearEdificio(jugador, posicion, jugador.getAtributos().getEntrenadorUnidadesAvanzadas());
	}

}
