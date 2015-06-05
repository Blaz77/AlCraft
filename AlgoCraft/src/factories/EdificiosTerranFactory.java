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
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.recurso.TipoRecurso;

public class EdificiosTerranFactory implements EdificiosAbstractFactory{

	public EdificioRecolectorGasVespeno crearRecolectorGasVespeno(Jugador jugador, int x, int y) {
		if (jugador.getMapa().getRecurso(x, y).getTipo() != TipoRecurso.VESPENO) {
			throw new RuntimeException();
		}
		EdificioRecolectorGasVespeno edificio = new Refineria(jugador, x, y);
		Trabajo construccion = new TrabajoConstruccion(6, edificio);
		edificio.setTrabajo(construccion);
		return edificio;
	}

	public EdificioRecolectorMineral crearRecolectorMineral(Jugador jugador, int x, int y) {
		EdificioRecolectorMineral edificio = new CentroDeMineral(jugador, x, y);
		Trabajo construccion = new TrabajoConstruccion(4, edificio);
		edificio.setTrabajo(construccion);
		return edificio;
	}

	public EdificioIncrementadorPoblacion crearIncrementadorPoblacion(Jugador jugador, int x, int y) {
		EdificioIncrementadorPoblacion edificio = new DepositoDeSuministros(jugador, x, y);
		Trabajo construccion = new TrabajoConstruccion(6, edificio);
		edificio.setTrabajo(construccion);
		return edificio;
	}

	public EdificioEntrenadorUnidades crearEntrenadorUnidadesBasicas(Jugador jugador, int x, int y) {
		EdificioEntrenadorUnidades edificio = new Barraca(jugador, x, y);
		Trabajo construccion = new TrabajoConstruccion(12, edificio);
		edificio.setTrabajo(construccion);
		return edificio;
	}

	public EdificioEntrenadorUnidades crearEntrenadorUnidadesIntermedias(Jugador jugador, int x,
			int y) {
		EdificioEntrenadorUnidades edificio = new Fabrica(jugador, x, y);
		Trabajo construccion = new TrabajoConstruccion(12, edificio);
		edificio.setTrabajo(construccion);
		return edificio;
	}

	public EdificioEntrenadorUnidades crearEntrenadorUnidadesAvanzadas(Jugador jugador, int x,
			int y) {
		EdificioEntrenadorUnidades edificio = new PuertoEstelar(jugador, x, y);
		Trabajo construccion = new TrabajoConstruccion(10, edificio);
		edificio.setTrabajo(construccion);
		return edificio;
	}

}
