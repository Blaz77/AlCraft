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
	
	public Edificio crearRecolectorGasVespeno(Jugador jugador, Posicion posicion) {
		// Chequeo de terreno y/o recurso:
		if (jugador.getMapa().getOcupante(posicion).getTipo() != TipoOcupante.VESPENO) {
			throw new RecursoAusente();
		}
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		// Chequeo y compra 
		comprarEdificio(jugador, jugador.getAtributos().getRecolectorGasVespeno());
		AtributosConstruccion construccion = new AtributosConstruccion(jugador.getAtributos().getRecolectorGasVespeno());
		return new Edificio(jugador, posicion, construccion);
		//mapa.setOcupante(construccion, posicion)???
	}

	public Edificio crearRecolectorMineral(Jugador jugador, Posicion posicion) {
		// Chequeo de terreno y/o recurso:
		if (jugador.getMapa().getOcupante(posicion).getTipo() != TipoOcupante.MINERAL) {
			throw new RecursoAusente();
		}
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		// Chequeo y compra 
		comprarEdificio(jugador, jugador.getAtributos().getRecolectorMineral());
		AtributosConstruccion construccion = new AtributosConstruccion(jugador.getAtributos().getRecolectorMineral());
		return new Edificio(jugador, posicion, construccion);
	}

	public Edificio crearIncrementadorPoblacion(Jugador jugador, Posicion posicion) {
		// Chequeo de terreno y/o recurso:
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		// Chequeo y compra 
		comprarEdificio(jugador, jugador.getAtributos().getIncrementadorPoblacion());
		AtributosConstruccion construccion = new AtributosConstruccion(jugador.getAtributos().getIncrementadorPoblacion());
		return new Edificio(jugador, posicion, construccion);
	}

	public Edificio crearEntrenadorUnidadesBasicas(Jugador jugador, Posicion posicion) {
		// Chequeo de terreno y/o recurso:
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		comprarEdificio(jugador, jugador.getAtributos().getEntrenadorUnidadesBasicas());
		AtributosConstruccion construccion = new AtributosConstruccion(jugador.getAtributos().getEntrenadorUnidadesBasicas());
		return new Edificio(jugador, posicion, construccion);
	}

	public Edificio crearEntrenadorUnidadesIntermedias(Jugador jugador, Posicion posicion) {
		// Chequeo de terreno y/o recurso:
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		// Chequeo de requisitos y costos y compra
		if (! ordenConstruccionSeCumple(jugador, jugador.getAtributos().getEntrenadorUnidadesBasicas().getNombre())) {
			throw new OrdenConstruccionViolado();
		}
		comprarEdificio(jugador, jugador.getAtributos().getEntrenadorUnidadesIntermedias());
		AtributosConstruccion construccion = new AtributosConstruccion(jugador.getAtributos().getEntrenadorUnidadesIntermedias());
		return new Edificio(jugador, posicion, construccion);
	}

	public Edificio crearEntrenadorUnidadesAvanzadas(Jugador jugador, Posicion posicion) {
		// Chequeo de terreno y/o recurso:
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		// Chequeo y compra 
		if (! ordenConstruccionSeCumple(jugador, jugador.getAtributos().getEntrenadorUnidadesIntermedias().getNombre())) {
			throw new OrdenConstruccionViolado();
		}
		comprarEdificio(jugador, jugador.getAtributos().getEntrenadorUnidadesAvanzadas());
		AtributosConstruccion construccion = new AtributosConstruccion(jugador.getAtributos().getEntrenadorUnidadesAvanzadas());
		return new Edificio(jugador, posicion, construccion);
	}

}
