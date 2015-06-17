package fiuba.algo3.integracion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Juego;
import fiuba.algo3.juego.Opciones;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.raza.TipoRaza;

public class TestSoloTerran {
	final int MINERAL_INICIAL = 200;
	final int GASVESPENO_INICIAL = 50;
	final int SUMINISTRO_INICIAL = 5; // Capacidad de poblacion
	
	private Juego juego;

	@Before
	public void setUp() {
		Opciones opciones = new Opciones();
		opciones.setCantidadBases(6);
		opciones.setDatosJugador(1, "Osvaldoide", Color.AZUL, TipoRaza.TERRAN);
		opciones.setDatosJugador(2, "waiter", Color.ROJO, TipoRaza.TERRAN);
		
		this.juego = new Juego(opciones);
	}
	
	@Test
	public void testDesarrolloSinBatallas() {
		// Datos iniciales
		assertEquals(juego.getJugadorActual().getColor(), Color.AZUL);
		assertEquals(juego.getJugadorActual().getMinerales(), MINERAL_INICIAL);
		assertEquals(juego.getJugadorActual().getGasVespeno(), GASVESPENO_INICIAL);
		assertEquals(juego.getJugadorActual().getCapacidadPoblacion(), SUMINISTRO_INICIAL);
		
		// Conocimiento parcial del mapa
		Posicion posInicial = juego.getJugadorActual().getPosicionInicial();
		
		assertTrue(juego.getJugadorActual().getMapa().getVisibilidad(
				posInicial).verTerreno()
				);
		assertTrue(juego.getJugadorActual().getMapa().getVisibilidad(
				posInicial).verOcupante()
				);
		assertFalse(juego.getJugadorActual().getMapa().getVisibilidad(
				new Posicion(50, 10)).verTerreno()
				);
		assertFalse(juego.getJugadorActual().getMapa().getVisibilidad(
				new Posicion(50, 10)).verOcupante()
				);
	}
}
