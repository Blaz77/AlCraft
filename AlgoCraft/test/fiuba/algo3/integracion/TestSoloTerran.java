package fiuba.algo3.integracion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Juego;
import fiuba.algo3.juego.Opciones;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;
import fiuba.algo3.raza.TipoRaza;

public class TestSoloTerran {
	final int MINERAL_INICIAL = 200;
	final int GASVESPENO_INICIAL = 50;
	final int SUMINISTRO_INICIAL = 5; // Capacidad de poblacion
	
	private Juego juego;
	private EdificiosFactory edificador;

	private Posicion buscarOcupante(Mapa mapa, Posicion origen, int distancia, TipoOcupante ocupante) {
		for (int y = origen.getY()-distancia; y < origen.getY()+distancia; y++) {
			for (int x = origen.getX()-distancia; x < origen.getX()+distancia; x++) {
				if (mapa.getOcupante(new Posicion(x, y)).getTipo() == ocupante) {
					return new Posicion(x, y);
				}
			}
		}
		return null;
	}
	
	@Before
	public void setUp() {
		Opciones opciones = new Opciones();
		opciones.setCantidadBases(6);
		opciones.setDatosJugador(1, "Osvaldoide", Color.AZUL, TipoRaza.TERRAN);
		opciones.setDatosJugador(2, "waiter", Color.ROJO, TipoRaza.TERRAN);
		
		this.juego = new Juego(opciones);
		this.edificador = new EdificiosFactory();
	}
	
	@Test
	public void testDesarrolloSinBatallas() {
		/* Datos iniciales */
		assertEquals(juego.getJugadorActual().getColor(), Color.AZUL);
		assertEquals(juego.getJugadorActual().getMinerales(), MINERAL_INICIAL);
		assertEquals(juego.getJugadorActual().getGasVespeno(), GASVESPENO_INICIAL);
		assertEquals(juego.getJugadorActual().getCapacidadPoblacion(), SUMINISTRO_INICIAL);
		
		/* Conocimiento parcial del mapa */
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
		
		/* Recoleccion */
		
		Posicion destino = buscarOcupante(juego.getJugadorActual().getMapa(), posInicial, 8, TipoOcupante.MINERAL);
		assertNotNull(destino);
		edificador.crearRecolectorMineral(juego.getJugadorActual(), destino);
		
		destino = buscarOcupante(juego.getJugadorActual().getMapa(), posInicial, 8, TipoOcupante.VESPENO);
		assertNotNull(destino);
		edificador.crearRecolectorGasVespeno(juego.getJugadorActual(), destino);
		
		// 20 turnos mas tarde...
		for (int i=0; i<20; i++) {
			juego.nextJugadorActual();
			juego.nextJugadorActual();
		}
		
		assertEquals(juego.getJugadorActual().getColor(), Color.AZUL);
		assertTrue(juego.getJugadorActual().getMinerales() > MINERAL_INICIAL);
		assertTrue(juego.getJugadorActual().getMinerales() > GASVESPENO_INICIAL);
		
		assertTrue(juego.getJugadorActual().getEdificios().size() == 2);
		
		/* Creacion de unidades militares */
		destino = buscarOcupante(juego.getJugadorActual().getMapa(), posInicial, 8, TipoOcupante.CELDA_VACIA);
		edificador.crearEntrenadorUnidadesBasicas(juego.getJugadorActual(), destino);
		
		// 15 turnos mas tarde...
		for (int i=0; i<15; i++) {
			juego.nextJugadorActual();
			juego.nextJugadorActual();
		}
		
		assertTrue(juego.getJugadorActual().getEdificios().size() == 3);
		Edificio barraca = juego.getJugadorActual().getEdificios().get(2);
		barraca.getUnidadesEntrenables().get(0).crear();
		barraca.getUnidadesEntrenables().get(0).crear();
		barraca.getUnidadesEntrenables().get(0).crear();
		barraca.getUnidadesEntrenables().get(0).crear();
		
		// 15 turnos mas tarde...
		for (int i=0; i<15; i++) {
			juego.nextJugadorActual();
			juego.nextJugadorActual();
		}
		
		assertTrue(juego.getJugadorActual().getUnidades().size() == 4);

		/* Movimientos */
		
	}
}
