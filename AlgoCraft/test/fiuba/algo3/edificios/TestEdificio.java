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
	
	protected Jugador jugador;
	protected MapaReal mapa;
	
	protected Posicion posRelativa(ObjetoVivo o, int offsetX, int offsetY){
		return new Posicion(o.getPosicion().getX() + offsetX, o.getPosicion().getY() + offsetY);
	}

	// La forma correcta de crear el edificio va aca dentro:
	protected abstract Edificio crearEdificio();
	
	// Para crear de forma especifica:
	// Redefinir llamando a crearEn(..,..,..)!
	protected abstract Edificio crearEdificioEn(Terreno terreno, Tipo tipo);
	
	protected Edificio crearEn(CreadorEdificio creador, Terreno terreno, Tipo tipo) {
		for (int y = 0; y < mapa.alto(); y++) {
			for (int x = 0; x < mapa.ancho(); x++) {
				Posicion posicion = new Posicion(x, y);
				if (mapa.getTerreno(posicion) == terreno
					&&	mapa.getOcupante(posicion).getTipo() == tipo) {
						return creador.crear(jugador, posicion);
				}
			}
		}
		throw new RuntimeException();
	}
	
	// La forma correcta de crear el edificio va aca dentro:
	protected Edificio crearEdificioRequerido() {
		// Si no hay requerido, no redefinir
		throw new RuntimeException();
	}
	
	// La forma correcta de crear el edificio va aca dentro:
	protected Edificio crearEdificioRequeridoNivel2() {
		// Si no hay requerido, no redefinir
		throw new RuntimeException();
	}
		
	protected Edificio crearFueraDeTierra() {
		return crearEdificioEn(Terreno.ESPACIO, Tipo.CELDA_VACIA);
	}
	
	protected Edificio crearEnVolcan() {
		return crearEdificioEn(Terreno.TIERRA, Tipo.VESPENO);
	}
	
	protected Edificio crearEnMineral() {
		return crearEdificioEn(Terreno.TIERRA, Tipo.MINERAL);
	}
	
	protected Edificio crearEnTierra() {
		return crearEdificioEn(Terreno.TIERRA, Tipo.CELDA_VACIA);
	}
	
}
