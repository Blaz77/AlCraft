package fiuba.algo3.componentes;

import fiuba.algo3.magia.MagiaAUnidad;
import fiuba.algo3.magia.MagiaDeAreaDeEfecto;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.ObjetoVivo;

public class MagiaNull implements IMagia {

	public boolean puedeHacerMagia() {
		return false;
	}

	public int getEnergia() {
		return 0;
	}

	public int getEnergiaMaxima() {
		return 0;
	}

	public int getEnergiaARegenerarPorTurno() {
		return 0;
	}

	public boolean puedeDisminuirEnergia(int cantidad) {
		return false;
	}

	public void disminuirEnergia(int cantidad) {
		// throw ALGO?
	}

	public void regenerarEnergia(int cantRegenerada) {
		// throw ALGO?
	}

	public int getRangoMagia() {
		return 0;
	}

	public boolean estaEnRangoDeMagia(Posicion otraPosicion) {
		return false;
	}

	public MagiaDeAreaDeEfecto getMagiaDeAreaDeEfecto() {
		return null;
	}

	public MagiaAUnidad getMagiaAUnidad() {
		return null;
	}

}
