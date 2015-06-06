//TODO Clase sin usar!

package factories;

import fiuba.algo3.componentes.Trabajo;
import fiuba.algo3.componentes.TrabajoConstruccion;
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
import fiuba.algo3.excepciones.SuministroInsuficiente;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.mapa.recurso.TipoRecurso;

public class EdificiosTerranFactory implements EdificiosAbstractFactory{

	private void verificarCostosConstruccion(Jugador jugador, Edificio edificio) {
		if (jugador.getMinerales() < edificio.getCostoMineral()) 
			throw new MineralInsuficiente();
		if (jugador.getGasVespeno() < edificio.getCostoGas()) 
			throw new GasVespenoInsuficiente();
	}
	
	public EdificioRecolectorGasVespeno crearRecolectorGasVespeno(Jugador jugador, Posicion posicion) {
		if (jugador.getMapa().getRecurso(posicion).getTipo() != TipoRecurso.VESPENO) {
			throw new RuntimeException();
		}
		EdificioRecolectorGasVespeno edificio = new Refineria(jugador, posicion);
		verificarCostosConstruccion(jugador, edificio);
		Trabajo construccion = new TrabajoConstruccion(6, edificio);
		edificio.setTrabajo(construccion);
		return edificio;
	}

	public EdificioRecolectorMineral crearRecolectorMineral(Jugador jugador, Posicion posicion) {
		if (jugador.getMapa().getRecurso(posicion).getTipo() != TipoRecurso.MINERAL) {
			throw new RuntimeException();
		}
		EdificioRecolectorMineral edificio = new CentroDeMineral(jugador, posicion);
		verificarCostosConstruccion(jugador, edificio);
		Trabajo construccion = new TrabajoConstruccion(4, edificio);
		edificio.setTrabajo(construccion);
		return edificio;
	}

	public EdificioIncrementadorPoblacion crearIncrementadorPoblacion(Jugador jugador, Posicion posicion) {
		EdificioIncrementadorPoblacion edificio = new DepositoDeSuministros(jugador, posicion);
		verificarCostosConstruccion(jugador, edificio);
		Trabajo construccion = new TrabajoConstruccion(6, edificio);
		edificio.setTrabajo(construccion);
		return edificio;
	}

	public EdificioEntrenadorUnidades crearEntrenadorUnidadesBasicas(Jugador jugador, Posicion posicion) {
		EdificioEntrenadorUnidades edificio = new Barraca(jugador, posicion);
		verificarCostosConstruccion(jugador, edificio);
		Trabajo construccion = new TrabajoConstruccion(12, edificio);
		edificio.setTrabajo(construccion);
		return edificio;
	}

	public EdificioEntrenadorUnidades crearEntrenadorUnidadesIntermedias(Jugador jugador, Posicion posicion) {
		EdificioEntrenadorUnidades edificio = new Fabrica(jugador, posicion);
		verificarCostosConstruccion(jugador, edificio);
		Trabajo construccion = new TrabajoConstruccion(12, edificio);
		edificio.setTrabajo(construccion);
		return edificio;
	}

	public EdificioEntrenadorUnidades crearEntrenadorUnidadesAvanzadas(Jugador jugador, Posicion posicion) {
		EdificioEntrenadorUnidades edificio = new PuertoEstelar(jugador, posicion);
		verificarCostosConstruccion(jugador, edificio);
		Trabajo construccion = new TrabajoConstruccion(10, edificio);
		edificio.setTrabajo(construccion);
		return edificio;
	}

}
