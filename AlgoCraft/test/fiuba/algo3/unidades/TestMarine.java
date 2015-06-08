package fiuba.algo3.unidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.edificios.Barraca;
import fiuba.algo3.excepciones.EnemigoFueraDeRango;
import fiuba.algo3.excepciones.NoEsUnEnemigo;
import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.raza.TipoRaza;

public class TestMarine {
	
	private Mapa mapa;
	private Jugador jugador;
	private Jugador jugadorEnemigo;
	private UnidadAtaque marine;
	private UnidadAtaque otroMarine;
	private UnidadAtaque marineEnemigo;
	private UnidadAtaque espectroEnemigo;
	private Barraca barraca;
	private Barraca barracaEnemiga;

	@Before
	public void setUp() throws Exception {
		mapa = new Mapa(6);
		this.jugador = new Jugador(TipoRaza.TERRAN, Color.AZUL, mapa);
		this.marine = new UnidadAtaque(this.jugador, new Posicion(2,4), jugador.getAtributos().getInfanteriaLivianaTerrestre());
		this.otroMarine = new UnidadAtaque(this.jugador, new Posicion(1,3), jugador.getAtributos().getInfanteriaLivianaTerrestre());
		this.barraca = new Barraca(this.jugador, new Posicion(2,6));
		this.jugadorEnemigo = new Jugador(TipoRaza.TERRAN, Color.ROJO, mapa);
		this.marineEnemigo = new UnidadAtaque(jugadorEnemigo, new Posicion(5,4), jugadorEnemigo.getAtributos().getInfanteriaLivianaTerrestre());
		this.espectroEnemigo = new UnidadAtaque(jugadorEnemigo, new Posicion(1,2), jugadorEnemigo.getAtributos().getInfanteriaPesadaArea());
		this.barracaEnemiga = new Barraca(jugadorEnemigo, new Posicion(3,3));		
	}
	
	@Test
	public void testMarinePuedeAtacar() {
		assertTrue(this.marine.puedeAtacar());
	}
	
	@Test
	public void testMarinePuedeMoverse() {
		assertTrue(this.marine.puedeMoverse());
	}
	
	@Test
	public void testMarineNoPuedeHacerMagia() {
		assertFalse(this.marine.puedeHacerMagia());
	}
	
	@Test
	public void testMarineNoPuedeVolar() {
		assertFalse(this.marine.puedeVolar());
	}

	@Test
	public void testAtacarUnidadEnemiga() {
		int danioTierra = 6;
		int vidaInicial = marineEnemigo.getVida();
		this.marine.atacarA(marineEnemigo);
		assertEquals(vidaInicial-danioTierra,marineEnemigo.getVida());
	}
	
	@Test
	public void testAtacarUnidadAereaEnemiga() {
		int danioAire = 6;
		int vidaInicial = espectroEnemigo.getVida();
		this.marine.atacarA(espectroEnemigo);
		assertEquals(vidaInicial-danioAire,espectroEnemigo.getVida());
	}
	
	@Test
	public void testAtacarEdificioEnemigo() {
		int danioTierra = 6;
		int vidaInicial = barracaEnemiga.getVida();
		this.marine.atacarA(barracaEnemiga);
		assertEquals(vidaInicial-danioTierra,barracaEnemiga.getVida());
	}
	
	@Test
	public void testPuedeAtacarFueraDeRangoEsFalse() {
		UnidadAtaque enemigoFueraDeRango = new UnidadAtaque(jugadorEnemigo, new Posicion(20,40), jugador.getAtributos().getInfanteriaLivianaTerrestre());
		assertFalse(this.marine.puedeAtacarA(enemigoFueraDeRango));
	}
	
	@Test(expected = EnemigoFueraDeRango.class)
	public void testAtacarFueraDeRangoLanzaExcepcion() {
		UnidadAtaque enemigoFueraDeRango = new UnidadAtaque(jugadorEnemigo, new Posicion(20,40), jugador.getAtributos().getInfanteriaLivianaTerrestre());
		this.marine.atacarA(enemigoFueraDeRango);
	}
	
	@Test
	public void testPuedeAtacarseASiMismoEsFalse() {
		assertFalse(this.marine.puedeAtacarA(marine));
	}
	
	@Test(expected = NoEsUnEnemigo.class)
	public void testAtacarseASiMismoLanzaExcepcion() {
		this.marine.atacarA(marine);
	}
	
	@Test
	public void testPuedeAtacarEdificioPropioEsFalse() {
		assertFalse(this.marine.puedeAtacarA(barraca));
	}
	
	@Test(expected = NoEsUnEnemigo.class)
	public void testAtacarEdificioPropioLanzaExcepcion() {
		this.marine.atacarA(barraca);
	}
	
	@Test
	public void testPuedeAtacarUnidadPropiaEsFalse() {
		assertFalse(this.marine.puedeAtacarA(otroMarine));
	}
	
	@Test(expected = NoEsUnEnemigo.class)
	public void testAtacarUnidadPropiaLanzaExcepcion() {
		this.marine.atacarA(otroMarine);
	}
	
	
}
