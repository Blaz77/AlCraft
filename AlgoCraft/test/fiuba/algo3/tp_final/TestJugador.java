package fiuba.algo3.tp_final;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestJugador {
	Jugador jugadorTerran;
	
	@Before
	public void setUp() {
		jugadorTerran = new Jugador(TipoRaza.TERRAN, Color.ROJO);
	}

	@Test
	public void testJugadorCreadoPreservaDatos() {
		Assert.assertTrue(jugadorTerran.getColor() == Color.ROJO);
		Assert.assertTrue(jugadorTerran.getRaza() == TipoRaza.TERRAN);
	}
	

}
