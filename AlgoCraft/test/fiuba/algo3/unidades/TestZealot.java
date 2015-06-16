package fiuba.algo3.unidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Barraca;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.raza.TipoRaza;

public class TestZealot extends TestUnidadAtaque {
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.PROTOSS, mapa);
		this.unidad = new Unidad(this.jugador, new Posicion(2,4), jugador.getAtributos().getInfanteriaLivianaTerrestre());
		this.otraUnidad = new Unidad(this.jugador, new Posicion(1,3), jugador.getAtributos().getInfanteriaLivianaTerrestre());
		this.edificioPropio = new Barraca(this.jugador, new Posicion(2,6));
		this.jugadorEnemigo = new Jugador("Enemigo", Color.ROJO, TipoRaza.TERRAN, mapa);
		this.unidadEnemigaTerrestre = new Unidad(jugadorEnemigo, new Posicion(3,4), jugadorEnemigo.getAtributos().getInfanteriaLivianaTerrestre());
		this.unidadEnemigaAerea = new Unidad(jugadorEnemigo, new Posicion(1,2), jugadorEnemigo.getAtributos().getInfanteriaPesadaArea());
		this.edificioEnemigo = new Barraca(jugadorEnemigo, new Posicion(2,3));	
		
		this.danioTierra = 8;
		this.danioAire = 0;
	}
	
	@Test
	public void testZealotNoPuedeVolar() {
		assertFalse(this.unidad.puedeOcuparEspacio());
	}
	
	@Override
	public void testAtacarUnidadAereaEnemiga() {
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
