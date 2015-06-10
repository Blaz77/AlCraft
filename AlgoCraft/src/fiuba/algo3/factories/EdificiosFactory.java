//TODO Clase sin usar!

package fiuba.algo3.factories;

import fiuba.algo3.atributos.AtributosEdificio;
import fiuba.algo3.edificios.Construccion;
import fiuba.algo3.edificios.ConstruccionEntrenadorUnidades;
import fiuba.algo3.edificios.ConstruccionIncrementadorPoblacion;
import fiuba.algo3.edificios.ConstruccionRecolector;
import fiuba.algo3.excepciones.GasVespenoInsuficiente;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.RecursoAusente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.mapa.recurso.TipoOcupante;
import fiuba.algo3.terreno.TipoTerreno;

public class EdificiosFactory implements EdificiosAbstractFactory{

	private void verificarCostosConstruccion(Jugador jugador, AtributosEdificio atributos) {
		if (jugador.getMinerales() < atributos.getCostoMineral()) 
			throw new MineralInsuficiente();
		if (jugador.getGasVespeno() < atributos.getCostoGasVespeno()) 
			throw new GasVespenoInsuficiente();
	}
	
	public Construccion crearRecolectorGasVespeno(Jugador jugador, Posicion posicion) {
		if (jugador.getMapa().getOcupante(posicion).getTipo() != TipoOcupante.VESPENO) {
			throw new RecursoAusente();
		}
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		verificarCostosConstruccion(jugador, jugador.getAtributos().getRecolectorGasVespeno());
		return new ConstruccionRecolector(jugador, posicion, jugador.getAtributos().getRecolectorGasVespeno());
	}

	public Construccion crearRecolectorMineral(Jugador jugador, Posicion posicion) {
		if (jugador.getMapa().getOcupante(posicion).getTipo() != TipoOcupante.MINERAL) {
			throw new RecursoAusente();
		}
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		verificarCostosConstruccion(jugador, jugador.getAtributos().getRecolectorMineral());
		return new ConstruccionRecolector(jugador, posicion, jugador.getAtributos().getRecolectorMineral());
	}

	public Construccion crearIncrementadorPoblacion(Jugador jugador, Posicion posicion) {
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		verificarCostosConstruccion(jugador, jugador.getAtributos().getIncrementadorPoblacion());
		return new ConstruccionIncrementadorPoblacion(jugador, posicion, jugador.getAtributos().getIncrementadorPoblacion());
	}

	public Construccion crearEntrenadorUnidadesBasicas(Jugador jugador, Posicion posicion) {
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		verificarCostosConstruccion(jugador, jugador.getAtributos().getEntrenadorUnidadesBasicas());
		return new ConstruccionEntrenadorUnidades(jugador, posicion, jugador.getAtributos().getEntrenadorUnidadesBasicas());
	}

	public Construccion crearEntrenadorUnidadesIntermedias(Jugador jugador, Posicion posicion) {
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		verificarCostosConstruccion(jugador, jugador.getAtributos().getEntrenadorUnidadesIntermedias());
		return new ConstruccionEntrenadorUnidades(jugador, posicion, jugador.getAtributos().getEntrenadorUnidadesIntermedias());
	}

	public Construccion crearEntrenadorUnidadesAvanzadas(Jugador jugador, Posicion posicion) {
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		verificarCostosConstruccion(jugador, jugador.getAtributos().getEntrenadorUnidadesAvanzadas());
		return new ConstruccionEntrenadorUnidades(jugador, posicion, jugador.getAtributos().getEntrenadorUnidadesAvanzadas());
	}

}
