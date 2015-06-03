package fiuba.algo3.edificios;

import fiuba.algo3.tp_final.*;

public class EdificiosTerranFactory implements EdificiosAbstractFactory{

	public Edificio crearRecolectorGasVespeno() {
		// TODO Auto-generated method stub
		return null;
	}

	public Edificio crearRecolectorMineral(Jugador jugador, int x, int y) {
		Edificio edificio = new CentroDeMineral(jugador, x, y);
		Trabajo construccion = new TrabajoConstruccion(4);
		edificio.setTrabajo(construccion);
		return edificio;
	}

	public Edificio crearIncrementadorPoblacion() {
		// TODO Auto-generated method stub
		return null;
	}

	public Edificio crearEntrenadorUnidadesBasicas() {
		// TODO Auto-generated method stub
		return null;
	}

	public Edificio crearEntrenadorUnidadesIntermedias() {
		// TODO Auto-generated method stub
		return null;
	}

	public Edificio crearEntrenadorUnidadesAvanzadas() {
		// TODO Auto-generated method stub
		return null;
	}

	public Edificio crearRecolectorMineral() {
		// TODO Auto-generated method stub
		return null;
	}

}
