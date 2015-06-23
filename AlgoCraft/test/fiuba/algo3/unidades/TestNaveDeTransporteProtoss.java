package fiuba.algo3.unidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.NoEsUnAliado;
import fiuba.algo3.excepciones.UnidadNoEsAlmacenable;
import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.raza.TipoRaza;

public class TestNaveDeTransporteProtoss extends TestUnidadTransporte {
	
	// this.unidad es el transportador
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.PROTOSS, mapa);
		this.unidad = new Unidad(this.jugador, POSICION_A, jugador.getAtributos().getTransporte());
		this.pasajero = new Unidad(this.jugador, POSICION_B, jugador.getAtributos().getInfanteriaLivianaTerrestre());
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
	 * -No tiene sentido: el chequeo de tipos ya cierra esta posibilidad
	 * Si no: se puede pasar todo a ObjetoVivo.
	 * -Pero si el usuario selecciona un edificio el modelo debe poder responder
	 * de alguna forma
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
	
	@Test
	public void testTieneEscudo() {
		assertTrue(this.unidad.tieneEscudo());
	}
	
	@Test
	public void testAlrecibirDanioBajaEscudo() {
		int vidaRelativa = this.unidad.getVida();
		int escudoRelativo = this.unidad.getEscudo();
		
		this.unidad.recibirDanio(8);
		assertEquals(this.unidad.getEscudo(), escudoRelativo - 8);
		assertEquals(this.unidad.getVida(), vidaRelativa);
	}
	
	@Test
	public void testRegeneraEscudoLuegoDeRecibirDanio() {
		this.unidad.recibirDanio(8);
		int escudoRelativo = this.unidad.getEscudo();
		
		this.unidad.pasarTurno();
		
		assertTrue(this.unidad.getEscudo() > escudoRelativo);
	}
	
	@Test
	public void testAlrecibirMuchoDanioBajaVida() {
		int vidaRelativa = this.unidad.getVida();
		int escudoRelativo = this.unidad.getEscudo();
		
		this.unidad.recibirDanio(escudoRelativo + 10);
		assertEquals(this.unidad.getEscudo(), 0);
		assertEquals(this.unidad.getVida(), vidaRelativa - 10);
	}
}
