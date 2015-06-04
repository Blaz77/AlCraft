package fiuba.algo3.edificios;

import fiuba.algo3.mapa.TipoRecurso;
import fiuba.algo3.tp_final.*;

public class EdificiosTerranFactory implements EdificiosAbstractFactory{

	public Edificio crearRecolectorGasVespeno(Jugador jugador, int x, int y) {
		if (jugador.getMApa().getRecurso(x, y).getTipo() != TipoRecurso.VESPENO) {
			throw new RuntimeException();
		}
		Edificio edificio = new Refineria(jugador, x, y);
		Trabajo construccion = new TrabajoConstruccion(6, edificio);
		edificio.setTrabajo(construccion);
		return edificio;
	}

	public Edificio crearRecolectorMineral(Jugador jugador, int x, int y) {
		Edificio edificio = new CentroDeMineral(jugador, x, y);
		Trabajo construccion = new TrabajoConstruccion(4, edificio);
		edificio.setTrabajo(construccion);
		return edificio;
	}

	public Edificio crearIncrementadorPoblacion(Jugador jugador, int x, int y) {
		Edificio edificio = new DepositoDeSuministros(jugador, x, y);
		Trabajo construccion = new TrabajoConstruccion(6, edificio);
		edificio.setTrabajo(construccion);
		return edificio;
	}

	public Edificio crearEntrenadorUnidadesBasicas(Jugador jugador, int x, int y) {
		Edificio edificio = new Barraca(jugador, x, y);
		Trabajo construccion = new TrabajoConstruccion(12, edificio);
		edificio.setTrabajo(construccion);
		return edificio;
	}

	public Edificio crearEntrenadorUnidadesIntermedias(Jugador jugador, int x,
			int y) {
		Edificio edificio = new Fabrica(jugador, x, y);
		Trabajo construccion = new TrabajoConstruccion(12, edificio);
		edificio.setTrabajo(construccion);
		return edificio;
	}

	public Edificio crearEntrenadorUnidadesAvanzadas(Jugador jugador, int x,
			int y) {
		Edificio edificio = new PuertoEstelar(jugador, x, y);
		Trabajo construccion = new TrabajoConstruccion(10, edificio);
		edificio.setTrabajo(construccion);
		return edificio;
	}

}
