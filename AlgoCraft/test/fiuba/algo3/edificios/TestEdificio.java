package fiuba.algo3.edificios;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.mapa.recurso.TipoOcupante;
import fiuba.algo3.terreno.TipoTerreno;

public abstract class TestEdificio {

	protected abstract Edificio crearEdificio(Jugador jugador, Posicion posicion);
	
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
	
}
