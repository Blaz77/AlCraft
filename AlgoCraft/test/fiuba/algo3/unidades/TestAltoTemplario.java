package fiuba.algo3.unidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Fabrica;
import fiuba.algo3.ocupantes.edificios.PuertoEstelar;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.raza.TipoRaza;

public class TestAltoTemplario extends TestUnidadMagica {
	final int DANIO_TORMENTA = 100;
	final int COSTO_TORMENTA = 75;
	
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.PROTOSS, mapa);
		this.unidad = new Unidad(this.jugador, POSICION_A, jugador.getAtributos().getInfanteriaMagica());
		this.otraUnidad = new Unidad(this.jugador, POSICION_B, jugador.getAtributos().getInfanteriaMagica());
		this.edificioPropio = new PuertoEstelar(this.jugador, POSICION_C);
		this.jugadorEnemigo = new Jugador("Enemigo", Color.ROJO, TipoRaza.TERRAN, mapa);
		this.unidadEnemigaTerrestre = new Unidad(jugadorEnemigo, POSICION_D, jugadorEnemigo.getAtributos().getInfanteriaPesadaTerrestre());
		this.unidadEnemigaAerea = new Unidad(jugadorEnemigo, POSICION_E, jugadorEnemigo.getAtributos().getInfanteriaPesadaArea());
		this.unidadEnemigaMagica = new Unidad(jugadorEnemigo, POSICION_F, jugadorEnemigo.getAtributos().getInfanteriaMagica());
		this.edificioEnemigo = new Fabrica(jugadorEnemigo, POSICION_G);	
	
		this.mapa.setOcupante(unidad, unidad.getPosicion());
		this.mapa.setOcupante(otraUnidad, otraUnidad.getPosicion());
		//this.mapa.setOcupante(edificioPropio, edificioPropio.getPosicion());
		this.mapa.setOcupante(unidadEnemigaTerrestre, unidadEnemigaTerrestre.getPosicion());
		//this.mapa.setOcupante(unidadEnemigaAerea, unidadEnemigaAerea.getPosicion());
		//this.mapa.setOcupante(unidadEnemigaMagica, unidadEnemigaMagica.getPosicion());
		//this.mapa.setOcupante(edificioEnemigo, edificioEnemigo.getPosicion());
	}
	
	@Test
	public void testAltoTemplarioPuedeVolar() {
		assertTrue(this.unidad.puedeOcuparEspacio());
	}
	
	@Test
	public void testMagiasConsumenEnergia() {
		int energiaRelativa = unidad.getEnergia();
		unidad.getMagiaDeAreaDeEfecto().ejecutar(unidad.getPosicion(), mapa);
		assertEquals(energiaRelativa - COSTO_TORMENTA, unidad.getEnergia());
		
		//energiaRelativa = unidad.getEnergia();
		//unidad.getMagiaAUnidad().ejecutar(unidadEnemigaTerrestre);
		//assertEquals(energiaRelativa -75, unidad.getEnergia());
	}
	
	@Test
	public void testMagiaTormentaPsionicaCausaDanioAUnidadCercana() {
		assertTrue(unidadEnemigaTerrestre.getVida() != 0);
		int vidaRelativa = unidadEnemigaTerrestre.getVida();

		unidad.getMagiaDeAreaDeEfecto().ejecutar(unidad.getPosicion(), mapa);
		assertEquals(unidadEnemigaTerrestre.getVida(), vidaRelativa);
		
		unidad.pasarTurno();
		assertEquals(unidadEnemigaTerrestre.getVida(), vidaRelativa - DANIO_TORMENTA);
		
		// Lo curo para que no se muera
		unidadEnemigaTerrestre.regenerarVida(DANIO_TORMENTA);
		unidad.pasarTurno();
		assertEquals(unidadEnemigaTerrestre.getVida(), vidaRelativa - DANIO_TORMENTA);
		
		// Lo curo para que no se muera, ya deberia pasar el efecto de la magia
		unidadEnemigaTerrestre.regenerarVida(DANIO_TORMENTA);
		unidad.pasarTurno();
		assertEquals(unidadEnemigaTerrestre.getVida(), vidaRelativa);
	}
	
	@Test
	public void testMagiaAlgunTestDeAlucinacion() {
		// Esa magia esta dificil
		assertTrue(false);
	}
	
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
