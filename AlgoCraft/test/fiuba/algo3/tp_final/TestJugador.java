package fiuba.algo3.tp_final;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.MapaReal;
import fiuba.algo3.raza.TipoRaza;

public class TestJugador {
	Jugador jugadorTerran;
	
	@Before
	public void setUp() {
		jugadorTerran = new Jugador("Prueba", Color.ROJO, TipoRaza.TERRAN, new MapaReal(6));
	}

	@Test
	public void testJugadorCreadoPreservaDatos() {
		assertTrue(jugadorTerran.getColor() == Color.ROJO);
		assertTrue(jugadorTerran.getRaza() == TipoRaza.TERRAN);
	}
	
	@Test
	public void testJugadorCreadoDatosIniciales() {
		assertEquals(jugadorTerran.getCapacidadPoblacion(), 5);
		assertEquals(jugadorTerran.getPoblacion(), 0);
		assertEquals(jugadorTerran.getMinerales(), 200);
		assertEquals(jugadorTerran.getGasVespeno(), 50);

		assertTrue(jugadorTerran.getUnidades().isEmpty());
	}
	
	@Test
	public void testJugadorLimitaLaPoblacionMaximaA200() {
		
		jugadorTerran.aumentarCapacidadPoblacion(400);
		
		assertEquals(200, jugadorTerran.getCapacidadPoblacion());
	}
	
	@Test
	public void testJugadorDisminuyeCapacidadSostieneLaPoblacion() {
		
		jugadorTerran.aumentarCapacidadPoblacion(100);
		jugadorTerran.aumentarPoblacion(50);
		
		int poblacionRelativa = jugadorTerran.getPoblacion();
		
		jugadorTerran.aumentarCapacidadPoblacion(-100);
		
		assertEquals(poblacionRelativa, jugadorTerran.getPoblacion());
	}

}
