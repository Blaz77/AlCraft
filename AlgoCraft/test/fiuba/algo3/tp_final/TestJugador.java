package fiuba.algo3.tp_final;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.raza.TipoRaza;

public class TestJugador {
	Jugador jugadorTerran;
	
	@Before
	public void setUp() {
		jugadorTerran = new Jugador("Prueba", Color.ROJO, TipoRaza.TERRAN, new Mapa(6));
	}

	@Test
	public void testJugadorCreadoPreservaDatos() {
		Assert.assertTrue(jugadorTerran.getColor() == Color.ROJO);
		Assert.assertTrue(jugadorTerran.getRaza() == TipoRaza.TERRAN);
	}
	

}
