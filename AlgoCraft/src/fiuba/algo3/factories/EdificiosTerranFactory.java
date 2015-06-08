//TODO Clase sin usar!

package fiuba.algo3.factories;

import fiuba.algo3.atributos.AtributosEdificio;
import fiuba.algo3.componentes.EstadoConstruyendoEdificio;
import fiuba.algo3.edificios.Barraca;
import fiuba.algo3.edificios.CentroDeMineral;
import fiuba.algo3.edificios.DepositoDeSuministros;
import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.edificios.EdificioEntrenadorUnidades;
import fiuba.algo3.edificios.EdificioIncrementadorPoblacion;
import fiuba.algo3.edificios.EdificioRecolectorGasVespeno;
import fiuba.algo3.edificios.EdificioRecolectorMineral;
import fiuba.algo3.edificios.Fabrica;
import fiuba.algo3.edificios.PuertoEstelar;
import fiuba.algo3.edificios.Refineria;
import fiuba.algo3.excepciones.GasVespenoInsuficiente;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.RecursoAusente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.mapa.recurso.TipoOcupante;
import fiuba.algo3.terreno.TipoTerreno;

public class EdificiosTerranFactory implements EdificiosAbstractFactory{

	private void verificarCostosConstruccion(Jugador jugador, AtributosEdificio atributos) {
		if (jugador.getMinerales() < atributos.getCostoMineral()) 
			throw new MineralInsuficiente();
		if (jugador.getGasVespeno() < atributos.getCostoGasVespeno()) 
			throw new GasVespenoInsuficiente();
	}
	
	public EdificioRecolectorGasVespeno crearRecolectorGasVespeno(Jugador jugador, Posicion posicion) {
		if (jugador.getMapa().getOcupante(posicion).getTipo() != TipoOcupante.VESPENO) {
			throw new RecursoAusente();
		}
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		EdificioRecolectorGasVespeno edificio = new Refineria(jugador, posicion);
		verificarCostosConstruccion(jugador, jugador.getAtributos().getRecolectorGasVespeno());
		edificio.agregarEstado(new EstadoConstruyendoEdificio(6, edificio));
		return edificio;
	}

	public EdificioRecolectorMineral crearRecolectorMineral(Jugador jugador, Posicion posicion) {
		if (jugador.getMapa().getOcupante(posicion).getTipo() != TipoOcupante.MINERAL) {
			throw new RecursoAusente();
		}
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		EdificioRecolectorMineral edificio = new CentroDeMineral(jugador, posicion);
		verificarCostosConstruccion(jugador, jugador.getAtributos().getRecolectorMineral());
		edificio.agregarEstado(new EstadoConstruyendoEdificio(4, edificio));
		return edificio;
	}

	public EdificioIncrementadorPoblacion crearIncrementadorPoblacion(Jugador jugador, Posicion posicion) {
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		EdificioIncrementadorPoblacion edificio = new DepositoDeSuministros(jugador, posicion);
		verificarCostosConstruccion(jugador, jugador.getAtributos().getIncrementadorPoblacion());
		edificio.agregarEstado(new EstadoConstruyendoEdificio(6, edificio));
		return edificio;
	}

	public EdificioEntrenadorUnidades crearEntrenadorUnidadesBasicas(Jugador jugador, Posicion posicion) {
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		EdificioEntrenadorUnidades edificio = new Barraca(jugador, posicion);
		verificarCostosConstruccion(jugador, jugador.getAtributos().getEntrenadorUnidadesBasicas());
		edificio.agregarEstado(new EstadoConstruyendoEdificio(12, edificio));
		return edificio;
	}

	public EdificioEntrenadorUnidades crearEntrenadorUnidadesIntermedias(Jugador jugador, Posicion posicion) {
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		EdificioEntrenadorUnidades edificio = new Fabrica(jugador, posicion);
		verificarCostosConstruccion(jugador, jugador.getAtributos().getEntrenadorUnidadesIntermedias());
		edificio.agregarEstado(new EstadoConstruyendoEdificio(12, edificio));
		return edificio;
	}

	public EdificioEntrenadorUnidades crearEntrenadorUnidadesAvanzadas(Jugador jugador, Posicion posicion) {
		if (jugador.getMapa().getTerreno(posicion).getTipo() != TipoTerreno.TIERRA) {
			throw new TerrenoInadecuado();
		}
		EdificioEntrenadorUnidades edificio = new PuertoEstelar(jugador, posicion);
		verificarCostosConstruccion(jugador, jugador.getAtributos().getEntrenadorUnidadesAvanzadas());
		edificio.agregarEstado(new EstadoConstruyendoEdificio(10, edificio));
		return edificio;
	}

}
