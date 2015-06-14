package fiuba.algo3.unidades;

import org.junit.Before;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.raza.TipoRaza;

public class TestUnidad {

	protected Mapa mapa;
	protected Jugador jugador;
	protected Jugador jugadorEnemigo;
	protected Edificio edificioPropio;
	protected Edificio edificioEnemigo;

	@Before
	public void setUp() throws Exception {
		mapa = new Mapa(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.TERRAN, mapa);
	}

}
