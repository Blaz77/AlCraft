package fiuba.algo3.tp_final;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestJugador {
	Jugador jugadorNuevo;
	
	@Before
	public void setUp() {
		jugadorNuevo = new Jugador(TipoRaza.TERRAN, Color.ROJO);
	}

	@Test
	public void testJugadorCreadoPreservaDatos() {
		Assert.assertTrue(jugadorNuevo.getColor() == Color.ROJO);
		Assert.assertTrue(jugadorNuevo.getRaza() == TipoRaza.TERRAN);
	}

}
