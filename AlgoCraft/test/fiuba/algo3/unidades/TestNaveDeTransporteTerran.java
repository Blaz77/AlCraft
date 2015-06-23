package fiuba.algo3.unidades;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.CapacidadAlmacenamientoInsuficente;
import fiuba.algo3.excepciones.FueraDelRangoPermitido;
import fiuba.algo3.excepciones.NoEsUnAliado;
import fiuba.algo3.excepciones.UnidadNoEsAlmacenable;
import fiuba.algo3.excepciones.UnidadYaAlmacenada;
import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.unidades.Pasajero;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.raza.TipoRaza;

public class TestNaveDeTransporteTerran extends TestUnidadTransporte {
	
	// this.unidad es el transportador
	
	private Unidad pasajero2;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.TERRAN, mapa);
		this.unidad = new Unidad(this.jugador, POSICION_A, jugador.getAtributos().getTransporte());
		this.pasajero = new Unidad(this.jugador, POSICION_B, jugador.getAtributos().getInfanteriaLivianaTerrestre());
		this.pasajero2 = new Unidad(this.jugador, POSICION_H, jugador.getAtributos().getInfanteriaLivianaTerrestre());
		this.unidadVoladora = new Unidad(this.jugador, POSICION_C, jugador.getAtributos().getInfanteriaPesadaArea());
		this.edificioPropio = new Edificio(this.jugador, POSICION_D, jugador.getAtributos().getEntrenadorUnidadesBasicas());
		this.jugadorEnemigo = new Jugador("Enemigo", Color.ROJO, TipoRaza.TERRAN, mapa);
		this.unidadEnemigaTerrestre = new Unidad(jugadorEnemigo, POSICION_E, jugadorEnemigo.getAtributos().getInfanteriaLivianaTerrestre());
		this.unidadEnemigaAerea = new Unidad(jugadorEnemigo, POSICION_F, jugadorEnemigo.getAtributos().getInfanteriaPesadaArea());
		this.edificioEnemigo = new Edificio(jugadorEnemigo, POSICION_G, jugadorEnemigo.getAtributos().getEntrenadorUnidadesBasicas());	
		
		this.capacidad = 8;
		
		this.mapa.setOcupante(unidad, unidad.getPosicion());
	}
	
	@Test
	public void testAlmacenarUnidadAliada() {
		unidad.almacenarA(pasajero);
		assertEquals(1, unidad.getUnidadesAlmacenadas().size());;
	}
	
	@Test
	public void testAlmacenarYLiberarUnidad() {
		unidad.almacenarA(pasajero);
		assertEquals(1, unidad.getUnidadesAlmacenadas().size());
		unidad.getUnidadesAlmacenadas().get(0).salir();
		assertTrue(unidad.getUnidadesAlmacenadas().isEmpty());
	}
	
	@Test
	public void testAlmacenarUnidadLaSacaDelMapa() {
		mapa.setOcupante(pasajero, pasajero.getPosicion());
		assertSame(pasajero, mapa.getOcupante(pasajero.getPosicion()));
		unidad.almacenarA(pasajero);
		assertNotSame(pasajero, mapa.getOcupante(pasajero.getPosicion()));
	}
	
	@Test
	public void testAlmacenarYLiberarUnidadLaDejaEnElMapa() {
		unidad.almacenarA(pasajero);
		unidad.getUnidadesAlmacenadas().get(0).salir();
		assertSame(pasajero, mapa.getOcupante(pasajero.getPosicion()));
	}
	
	@Test
	public void testAlmacenarUnidadComparteLaPosicionConElTransporte() {
		assertNotEquals(unidad.getPosicion(), pasajero.getPosicion());;
		unidad.almacenarA(pasajero);
		assertEquals(unidad.getPosicion(), pasajero.getPosicion());
	}
	
	@Test
	public void testMoverTransporteConUnidadAlmacenadaActualizaSuPosicion() {
		unidad.almacenarA(pasajero);
		assertEquals(unidad.getPosicion(), pasajero.getPosicion());
		unidad.moverA(POSICION_B);
		assertEquals(unidad.getPosicion(), pasajero.getPosicion());
	}
	
	@Test
	public void testMoverTransporteConVariasUnidadesAlmacenadasActualizaSusPosiciones() {
		unidad.almacenarA(pasajero);
		unidad.almacenarA(pasajero2);
		assertEquals(unidad.getPosicion(), pasajero.getPosicion());
		assertEquals(unidad.getPosicion(), pasajero2.getPosicion());
		unidad.moverA(POSICION_B);
		assertEquals(unidad.getPosicion(), pasajero.getPosicion());
		assertEquals(unidad.getPosicion(), pasajero2.getPosicion());
		unidad.moverA(POSICION_A);
		assertEquals(unidad.getPosicion(), pasajero.getPosicion());
		assertEquals(unidad.getPosicion(), pasajero2.getPosicion());
	}
	
	@Test
	public void testAlmacenarYLiberarVariasUnidades() {
		unidad.almacenarA(pasajero);
		assertEquals(1, unidad.getUnidadesAlmacenadas().size());
		unidad.almacenarA(pasajero2);
		assertEquals(2, unidad.getUnidadesAlmacenadas().size());
		List<Pasajero> psngers = unidad.getUnidadesAlmacenadas();
		psngers.get(0).salir();
		psngers.get(1).salir();
		assertSame(pasajero, mapa.getOcupante(pasajero.getPosicion()));
		assertSame(pasajero2, mapa.getOcupante(pasajero2.getPosicion()));
		assertEquals(0, unidad.getUnidadesAlmacenadas().size());
	}
	
	@Test
	public void testNoPuedeAlmacenarPasadoSuCapacidadMaxima() {
		for (int i = 0; i < capacidad;i++){
			Unidad pasajeroCosto1 = new Unidad(this.jugador, POSICION_B, jugador.getAtributos().getInfanteriaLivianaTerrestre());
			unidad.almacenarA(pasajeroCosto1);
		}
		Unidad pasajeroCosto1 = new Unidad(this.jugador, POSICION_B, jugador.getAtributos().getInfanteriaLivianaTerrestre());
		assertFalse(unidad.puedeAlmacenarA(pasajeroCosto1));
	}
	
	@Test(expected = CapacidadAlmacenamientoInsuficente.class)
	public void testAlmacenarPasadoSuCapacidadMaximaLanzaExcepcion() {
		
		for (int i = 0; i < capacidad;i++){
			Unidad pasajeroCosto1 = new Unidad(this.jugador, POSICION_B, jugador.getAtributos().getInfanteriaLivianaTerrestre());
			unidad.almacenarA(pasajeroCosto1);
		}
		Unidad pasajeroCosto2 = new Unidad(this.jugador, POSICION_B, jugador.getAtributos().getInfanteriaPesadaTerrestre());
		unidad.almacenarA(pasajeroCosto2);
	}
	
	@Test
	public void testNoPuedeAlmacenarUnidadYaAlmacenada() {
		unidad.almacenarA(pasajero);
		assertFalse(unidad.puedeAlmacenarA(pasajero));
	}
	
	@Test(expected = UnidadYaAlmacenada.class)
	public void testAlmacenarUnidadYaAlmacenadaLanzaExcepcion() {
		unidad.almacenarA(pasajero);
		unidad.almacenarA(pasajero);
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
	
	@Test
	public void testNoPuedeAlmacenarAUnidadFueraDeRango() {
		this.pasajero.setPosicion(POSICION_E);
		assertFalse(unidad.puedeAlmacenarA(pasajero));
	}
	
	@Test(expected = FueraDelRangoPermitido.class)
	public void testAlmacenarAUnidadFueraDeRangoLanzaExcepcion() {
		this.pasajero.setPosicion(POSICION_E);
		unidad.almacenarA(pasajero);
	}
	
}
