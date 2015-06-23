package fiuba.algo3.edificios;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Edificio;

@FunctionalInterface
public interface CreadorEdificio {

	public Edificio crear(Jugador jugador,Posicion posicion);
}
