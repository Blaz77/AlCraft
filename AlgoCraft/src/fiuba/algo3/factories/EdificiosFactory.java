//TODO Clase sin usar!

package fiuba.algo3.factories;

import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.excepciones.RecursoAusente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.en_construccion.Construccion;
import fiuba.algo3.ocupantes.edificios.en_construccion.ConstruccionEntrenadorUnidades;
import fiuba.algo3.ocupantes.edificios.en_construccion.ConstruccionIncrementadorPoblacion;
import fiuba.algo3.ocupantes.edificios.en_construccion.ConstruccionRecolector;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;
import fiuba.algo3.terreno.TipoTerreno;

public class EdificiosFactory implements EdificiosAbstractFactory{

	private void comprarEdificio(Jugador jugador, AtributosEdificio atributos) {
		jugador.comprar(atributos.getCostoMineral(), atributos.getCostoGasVespeno()); 
	}
	
	public Construccion crearRecolectorGasVespeno(Jugador jugador, Posicion posicion) {
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
		return new ConstruccionRecolector(jugador, posicion, jugador.getAtributos().getRecolectorGasVespeno());
		
	}

	public Construccion crearRecolectorMineral(Jugador jugador, Posicion posicion) {
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
		return new ConstruccionRecolector(jugador, posicion, jugador.getAtributos().getRecolectorMineral());
	}

	public Construccion crearIncrementadorPoblacion(Jugador jugador, Posicion posicion) {
		// Chequeo de requisitos de edificio anterior:
		// TODO
		// Chequeo de terreno y/o recurso:
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		// Chequeo y compra 
		comprarEdificio(jugador, jugador.getAtributos().getIncrementadorPoblacion());
		return new ConstruccionIncrementadorPoblacion(jugador, posicion, jugador.getAtributos().getIncrementadorPoblacion());
	}

	public Construccion crearEntrenadorUnidadesBasicas(Jugador jugador, Posicion posicion) {
		// Chequeo de requisitos de edificio anterior:
		// TODO
		// Chequeo de terreno y/o recurso:
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		comprarEdificio(jugador, jugador.getAtributos().getEntrenadorUnidadesBasicas());
		return new ConstruccionEntrenadorUnidades(jugador, posicion, jugador.getAtributos().getEntrenadorUnidadesBasicas());
	}

	public Construccion crearEntrenadorUnidadesIntermedias(Jugador jugador, Posicion posicion) {
		// Chequeo de requisitos de edificio anterior:
		// TODO
		// Chequeo de terreno y/o recurso:
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		// Chequeo y compra 
		comprarEdificio(jugador, jugador.getAtributos().getEntrenadorUnidadesIntermedias());
		return new ConstruccionEntrenadorUnidades(jugador, posicion, jugador.getAtributos().getEntrenadorUnidadesIntermedias());
	}

	public Construccion crearEntrenadorUnidadesAvanzadas(Jugador jugador, Posicion posicion) {
		// Chequeo de requisitos de edificio anterior:
		// TODO
		// Chequeo de terreno y/o recurso:
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		// Chequeo y compra 
		comprarEdificio(jugador, jugador.getAtributos().getEntrenadorUnidadesAvanzadas());
		return new ConstruccionEntrenadorUnidades(jugador, posicion, jugador.getAtributos().getEntrenadorUnidadesAvanzadas());
	}

}
