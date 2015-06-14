package fiuba.algo3.edificios;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;
import fiuba.algo3.terreno.TipoTerreno;

public abstract class TestEdificio {

	protected abstract Edificio crearEdificio(Jugador jugador, Posicion posicion);
	
	protected Edificio crearEdificioRequerido(Jugador jugador, Posicion posicion) {
		// Si no hay requerido, no redefinir
		return null;
	}
	
	protected Edificio crearEdificioRequeridoNivel2(Jugador jugador, Posicion posicion) {
		// Si no hay requerido, no redefinir
		return null;
	}
	
	protected Edificio crearEnTierra(Jugador jugador, Mapa mapa) {
		for (int y = 0; y < mapa.alto(); y++) {
			for (int x = 0; x < mapa.ancho(); x++) {
				Posicion posEnTierra = new Posicion(x, y);
				if (mapa.getTerreno(posEnTierra).getTipo() == TipoTerreno.TIERRA) {
					return crearEdificio(jugador, posEnTierra);
				}
			}
		}
		return null;
	}
	
	protected Edificio crearRequeridoEnTierra(Jugador jugador, Mapa mapa) {
		for (int y = 0; y < mapa.alto(); y++) {
			for (int x = 0; x < mapa.ancho(); x++) {
				Posicion posEnTierra = new Posicion(x, y);
				if (mapa.getTerreno(posEnTierra).getTipo() == TipoTerreno.TIERRA) {
					return crearEdificioRequerido(jugador, posEnTierra);
				}
			}
		}
		return null;
	}
	
	// Perdon por este horror, si se les ocurre algo mejor, bienvenido
	protected Edificio crearRequeridoNivel2EnTierra(Jugador jugador, Mapa mapa) {
		for (int y = 0; y < mapa.alto(); y++) {
			for (int x = 0; x < mapa.ancho(); x++) {
				Posicion posEnTierra = new Posicion(x, y);
				if (mapa.getTerreno(posEnTierra).getTipo() == TipoTerreno.TIERRA) {
					return crearEdificioRequeridoNivel2(jugador, posEnTierra);
				}
			}
		}
		return null;
	}
	
	protected Edificio crearFueraDeTierra(Jugador jugador, Mapa mapa) {
		for (int y = 0; y < mapa.alto(); y++) {
			for (int x = 0; x < mapa.ancho(); x++) {
				Posicion posFueraDeTierra = new Posicion(x, y);
				if (mapa.getTerreno(posFueraDeTierra).getTipo() != TipoTerreno.TIERRA) {
					return crearEdificio(jugador, posFueraDeTierra);
				}
			}
		}
		return null;
	}
	
	protected Edificio crearEnRecurso(Jugador jugador, Mapa mapa, TipoOcupante recurso) {
		for (int y = 0; y < mapa.alto(); y++) {
			for (int x = 0; x < mapa.ancho(); x++) {
				Posicion posConRecurso = new Posicion(x, y);
				if (mapa.getOcupante(posConRecurso).getTipo() == recurso) {
						return crearEdificio(jugador, posConRecurso);
				}
			}
		}
		return null;
	}
	
	protected Edificio crearFueraDeRecurso(Jugador jugador, Mapa mapa, TipoOcupante recurso) {
		for (int y = 0; y < mapa.alto(); y++) {
			for (int x = 0; x < mapa.ancho(); x++) {
				Posicion posSinRecurso = new Posicion(x, y);
				if (mapa.getOcupante(posSinRecurso).getTipo() != recurso) {
						return crearEdificio(jugador, posSinRecurso);
				}
			}
		}
		return null;
	}
	
}
