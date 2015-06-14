package fiuba.algo3.componentes;

import fiuba.algo3.magia.MagiaAUnidad;
import fiuba.algo3.magia.MagiaDeAreaDeEfecto;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.ocupantes.unidades.UnidadMagica;

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

	public boolean estaEnRangoDeMagia(Unidad efectuador,
			Posicion otraPosicion) {
		return false;
	}

	public MagiaDeAreaDeEfecto getMagiaDeAreaDeEfecto(Unidad efectuador) {
		return null;
	}

	public MagiaAUnidad getMagiaAUnidad(Unidad efectuador) {
		return null;
	}

}
