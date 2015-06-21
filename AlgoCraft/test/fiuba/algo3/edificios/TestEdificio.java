package fiuba.algo3.edificios;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.MapaReal;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.TipoOcupante;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.terreno.Terreno;

public abstract class TestEdificio {
	
	protected Posicion posRelativa(ObjetoVivo o, int offsetX, int offsetY){
		return new Posicion(o.getPosicion().getX() + offsetX, o.getPosicion().getY() + offsetY);
	}

	protected abstract Edificio crearEdificio(Jugador jugador, Posicion posicion);
	
	protected Edificio crearEdificioRequerido(Jugador jugador, Posicion posicion) {
		// Si no hay requerido, no redefinir
		throw new RuntimeException();
	}
	
	protected Edificio crearEdificioRequeridoNivel2(Jugador jugador, Posicion posicion) {
		// Si no hay requerido, no redefinir
		throw new RuntimeException();
	}
	
	protected Edificio crearEnTierra(Jugador jugador, MapaReal mapa, Posicion posInicial) {
		for (int y = posInicial.getY(); y < mapa.alto(); y++) {
			for (int x = posInicial.getX(); x < mapa.ancho(); x++) {
				Posicion posEnTierra = new Posicion(x, y);
				if (mapa.getTerreno(posEnTierra) == Terreno.TIERRA
						&&	mapa.getOcupante(posEnTierra).getTipo() == Tipo.CELDA_VACIA) {
					return crearEdificio(jugador, posEnTierra);
				}
			}
		}
		throw new RuntimeException();
	}
	
	protected Edificio crearRequeridoEnTierra(Jugador jugador, MapaReal mapa, Posicion posInicial) {
		for (int y = posInicial.getY(); y < mapa.alto(); y++) {
			for (int x = posInicial.getX(); x < mapa.ancho(); x++) {
				Posicion posEnTierra = new Posicion(x, y);
				if (mapa.getTerreno(posEnTierra) == Terreno.TIERRA) {
					return crearEdificioRequerido(jugador, posEnTierra);
				}
			}
		}
		throw new RuntimeException();
	}
	
	// Perdon por este horror, si se les ocurre algo mejor, bienvenido
	protected Edificio crearRequeridoNivel2EnTierra(Jugador jugador, MapaReal mapa, Posicion posInicial) {
		for (int y = posInicial.getY(); y < mapa.alto(); y++) {
			for (int x = posInicial.getX(); x < mapa.ancho(); x++) {
				Posicion posEnTierra = new Posicion(x, y);
				if (mapa.getTerreno(posEnTierra) == Terreno.TIERRA) {
					return crearEdificioRequeridoNivel2(jugador, posEnTierra);
				}
			}
		}
		throw new RuntimeException();
	}
	
	protected Edificio crearFueraDeTierra(Jugador jugador, MapaReal mapa, Posicion posInicial) {
		for (int y = posInicial.getY(); y < mapa.alto(); y++) {
			for (int x = posInicial.getX(); x < mapa.ancho(); x++) {
				Posicion posFueraDeTierra = new Posicion(x, y);
				if (mapa.getTerreno(posFueraDeTierra) != Terreno.TIERRA) {
					return crearEdificio(jugador, posFueraDeTierra);
				}
			}
		}
		throw new RuntimeException();
	}
	
	protected Edificio crearEnRecurso(Jugador jugador, MapaReal mapa, Tipo recurso, Posicion posInicial) {
		for (int y = posInicial.getY(); y < mapa.alto(); y++) {
			for (int x = posInicial.getX(); x < mapa.ancho(); x++) {
				Posicion posConRecurso = new Posicion(x, y);
				if (mapa.getTerreno(posConRecurso) == Terreno.TIERRA
					&&	mapa.getOcupante(posConRecurso).getTipo() == recurso) {
						return crearEdificio(jugador, posConRecurso);
				}
			}
		}
		throw new RuntimeException();
	}
	
	protected Edificio crearFueraDeRecurso(Jugador jugador, MapaReal mapa, Tipo recurso, Posicion posInicial) {
		for (int y = posInicial.getY(); y < mapa.alto(); y++) {
			for (int x = posInicial.getX(); x < mapa.ancho(); x++) {
				Posicion posSinRecurso = new Posicion(x, y);
				if (mapa.getTerreno(posSinRecurso) == Terreno.TIERRA
					&&	mapa.getOcupante(posSinRecurso).getTipo() != recurso) {
						return crearEdificio(jugador, posSinRecurso);
				}
			}
		}
		throw new RuntimeException();
	}
	
}
