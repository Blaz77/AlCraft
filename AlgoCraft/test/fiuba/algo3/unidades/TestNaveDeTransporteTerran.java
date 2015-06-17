package fiuba.algo3.unidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.NoEsUnAliado;
import fiuba.algo3.excepciones.UnidadNoEsAlmacenable;
import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Barraca;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.raza.TipoRaza;

public class TestNaveDeTransporteTerran extends TestUnidadTransporte {
	
	// this.unidad es el transportador
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.TERRAN, mapa);
		this.unidad = new Unidad(this.jugador, POSICION_A, jugador.getAtributos().getTransporte());
		this.pasajero = new Unidad(this.jugador, POSICION_B, jugador.getAtributos().getInfanteriaLivianaTerrestre());
		this.unidadVoladora = new Unidad(this.jugador, POSICION_C, jugador.getAtributos().getInfanteriaPesadaArea());
		this.edificioPropio = new Barraca(this.jugador, POSICION_D);
		this.jugadorEnemigo = new Jugador("Enemigo", Color.ROJO, TipoRaza.TERRAN, mapa);
		this.unidadEnemigaTerrestre = new Unidad(jugadorEnemigo, POSICION_E, jugadorEnemigo.getAtributos().getInfanteriaLivianaTerrestre());
		this.unidadEnemigaAerea = new Unidad(jugadorEnemigo, POSICION_F, jugadorEnemigo.getAtributos().getInfanteriaPesadaArea());
		this.edificioEnemigo = new Barraca(jugadorEnemigo, POSICION_G);	
		
		this.capacidad = 8;
		
		this.mapa.setOcupante(unidad, unidad.getPosicion());
	}
	
	@Test
	public void testAlmacenarUnidadAliada() {
		unidad.almacenarA(pasajero);
		assertTrue(unidad.getUnidadesAlmacenadas().size() == 1);
	}
	
	@Test
	public void testAlmacenarYLiberarUnidad() {
		unidad.almacenarA(pasajero);
		assertTrue(unidad.getUnidadesAlmacenadas().size() == 1);
		unidad.liberarUnidad(pasajero);
		assertTrue(unidad.getUnidadesAlmacenadas().isEmpty());
		
		// Otra forma
		unidad.almacenarA(pasajero);
		assertTrue(unidad.getUnidadesAlmacenadas().size() == 1);
		unidad.getUnidadesAlmacenadas().get(0).salir();
		assertTrue(unidad.getUnidadesAlmacenadas().isEmpty());
	}
	
	@Test
	public void testNoPuedeAlmacenarAUnidadEnemiga() {
		assertFalse(unidad.puedeAlmacenarA(unidadEnemigaTerrestre));
	}
	
	@Test(expected = NoEsUnAliado.class)
	public void testAlmacenarAUnidadEnemigaDebeFallar() {
		unidad.almacenarA(unidadEnemigaTerrestre);
	}
	
	@Test
	public void testNoPuedeAlmacenarAUnidadVoladora() {
		assertFalse(unidad.puedeAlmacenarA(unidadVoladora));
	}
	
	@Test(expected = UnidadNoEsAlmacenable.class)
	public void testAlmacenarAUnidadVoladoraDebeFallar() {
		unidad.almacenarA(unidadVoladora);
	}
	
	
	/*
	 * No tiene sentido: el chequeo de tipos ya cierra esta posibilidad
	 * Si no: se puede pasar todo a ObjetoVivo.
	 * 
	@Test
	public void testNoPuedeAlmacenarAEdificio() {
		assertFalse(transportador.puedeAlmacenarA(edificioPropio));
	}
	
	@Test(expected = UnidadNoEsAlmacenable.class)
	public void testAlmacenarAEdificioDebeFallar() {
		transportador.almacenarA(edificioPropio);
	}
	
	*/
}
