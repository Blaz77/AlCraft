package fiuba.algo3.unidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.raza.TipoRaza;

public class TestScout extends TestUnidadAtaque {
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.PROTOSS, mapa);
		this.unidad = new Unidad(this.jugador, POSICION_A, jugador.getAtributos().getInfanteriaPesadaArea());
		this.otraUnidad = new Unidad(this.jugador, POSICION_B, jugador.getAtributos().getInfanteriaPesadaArea());
		this.edificioPropio = new Edificio(this.jugador, POSICION_C, jugador.getAtributos().getEntrenadorUnidadesAvanzadas());
		this.jugadorEnemigo = new Jugador("Enemigo", Color.ROJO, TipoRaza.TERRAN, mapa);
		this.unidadEnemigaTerrestre = new Unidad(jugadorEnemigo, POSICION_D, jugadorEnemigo.getAtributos().getInfanteriaPesadaTerrestre());
		this.unidadEnemigaAerea = new Unidad(jugadorEnemigo, POSICION_E, jugadorEnemigo.getAtributos().getInfanteriaPesadaArea());
		this.edificioEnemigo = new Edificio(jugadorEnemigo, POSICION_F, jugadorEnemigo.getAtributos().getEntrenadorUnidadesIntermedias());

		this.danioTierra = 8;
		this.danioAire = 14;
		
		this.mapa.setOcupante(unidad, unidad.getPosicion());
	}
	
	@Test
	public void testScoutPuedeVolar() {
		assertTrue(this.unidad.puedeOcuparEspacio());
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
