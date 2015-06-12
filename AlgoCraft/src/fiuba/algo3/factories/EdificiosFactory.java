//TODO Clase sin usar!

package fiuba.algo3.factories;

import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.excepciones.RecursoAusente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.edificios.EdificioEntrenadorUnidades;
import fiuba.algo3.ocupantes.edificios.EdificioIncrementadorPoblacion;
import fiuba.algo3.ocupantes.edificios.EdificioRecolectorGasVespeno;
import fiuba.algo3.ocupantes.edificios.EdificioRecolectorMineral;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;
import fiuba.algo3.terreno.TipoTerreno;

public class EdificiosFactory implements EdificiosAbstractFactory{

	private void comprarEdificio(Jugador jugador, AtributosEdificio atributos) {
		jugador.comprar(atributos.getCosto().getCostoMineral(), atributos.getCosto().getCostoGasVespeno()); 
	}
	
	public Edificio crearRecolectorGasVespeno(Jugador jugador, Posicion posicion) {
		// Chequeo de requisitos de edificio anterior:
		// TODO
		// Chequeo de terreno y/o recurso:
		if (jugador.getMapa().getOcupante(posicion).getTipo() != TipoOcupante.VESPENO) {
			throw new RecursoAusente();
		}
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		// Chequeo y compra 
		comprarEdificio(jugador, jugador.getAtributos().getRecolectorGasVespeno());
		return new EdificioRecolectorGasVespeno(jugador, posicion, jugador.getAtributos().getRecolectorGasVespeno());
		//mapa.setOcupante(construccion, posicion)???
	}

	public Edificio crearRecolectorMineral(Jugador jugador, Posicion posicion) {
		// Chequeo de requisitos de edificio anterior:
		// TODO
		// Chequeo de terreno y/o recurso:
		if (jugador.getMapa().getOcupante(posicion).getTipo() != TipoOcupante.MINERAL) {
			throw new RecursoAusente();
		}
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		// Chequeo y compra 
		comprarEdificio(jugador, jugador.getAtributos().getRecolectorMineral());
		return new EdificioRecolectorMineral(jugador, posicion, jugador.getAtributos().getRecolectorMineral());
	}

	public Edificio crearIncrementadorPoblacion(Jugador jugador, Posicion posicion) {
		// Chequeo de requisitos de edificio anterior:
		// TODO
		// Chequeo de terreno y/o recurso:
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		// Chequeo y compra 
		comprarEdificio(jugador, jugador.getAtributos().getIncrementadorPoblacion());
		return new EdificioIncrementadorPoblacion(jugador, posicion, jugador.getAtributos().getIncrementadorPoblacion());
	}

	public Edificio crearEntrenadorUnidadesBasicas(Jugador jugador, Posicion posicion) {
		// Chequeo de requisitos de edificio anterior:
		// TODO
		// Chequeo de terreno y/o recurso:
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		comprarEdificio(jugador, jugador.getAtributos().getEntrenadorUnidadesBasicas());
		return new EdificioEntrenadorUnidades(jugador, posicion, jugador.getAtributos().getEntrenadorUnidadesBasicas());
	}

	public Edificio crearEntrenadorUnidadesIntermedias(Jugador jugador, Posicion posicion) {
		// Chequeo de requisitos de edificio anterior:
		// TODO
		// Chequeo de terreno y/o recurso:
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		// Chequeo y compra 
		comprarEdificio(jugador, jugador.getAtributos().getEntrenadorUnidadesIntermedias());
		return new EdificioEntrenadorUnidades(jugador, posicion, jugador.getAtributos().getEntrenadorUnidadesIntermedias());
	}

	public Edificio crearEntrenadorUnidadesAvanzadas(Jugador jugador, Posicion posicion) {
		// Chequeo de requisitos de edificio anterior:
		// TODO
		// Chequeo de terreno y/o recurso:
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		// Chequeo y compra 
		comprarEdificio(jugador, jugador.getAtributos().getEntrenadorUnidadesAvanzadas());
		return new EdificioEntrenadorUnidades(jugador, posicion, jugador.getAtributos().getEntrenadorUnidadesAvanzadas());
	}

}
