package fiuba.algo3.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fiuba.algo3.excepciones.FueraDelRangoPermitido;
import fiuba.algo3.excepciones.NoEsUnEnemigo;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.unidades.Unidad;

public abstract class TestUnidadAtaque extends TestUnidad {
	protected int danioTierra;
	protected int danioAire;

	protected Unidad otraUnidad;
	protected Unidad unidadEnemigaTerrestre;
	protected Unidad unidadEnemigaAerea;
	

	@Test
	public void testUnidadPuedeAtacar() {
		assertTrue(this.unidad.puedeAtacar());
	}
	
	@Test
	public void testAtacarUnidadEnemiga() {
		int vidaInicial = unidadEnemigaTerrestre.getVida();
		this.unidad.atacarA(unidadEnemigaTerrestre);
		assertEquals(vidaInicial-danioTierra,unidadEnemigaTerrestre.getVida());
	}

	@Test
	public void testAtacarUnidadAereaEnemiga() {
		int vidaInicial = unidadEnemigaAerea.getVida();
		this.unidad.atacarA(unidadEnemigaAerea);
		assertEquals(vidaInicial-danioAire,unidadEnemigaAerea.getVida());
	}

	@Test
	public void testAtacarEdificioEnemigo() {
		int vidaInicial = edificioEnemigo.getVida();
		this.unidad.atacarA(edificioEnemigo);
		assertEquals(vidaInicial-danioTierra,edificioEnemigo.getVida());
	}

	@Test
	public void testPuedeAtacarFueraDeRangoEsFalse() {
		Unidad enemigoFueraDeRango = new Unidad(jugadorEnemigo, new Posicion(20,40), jugador.getAtributos().getInfanteriaLivianaTerrestre());
		assertFalse(this.unidad.puedeAtacarA(enemigoFueraDeRango));
	}

	@Test(expected = FueraDelRangoPermitido.class)
	public void testAtacarFueraDeRangoLanzaExcepcion() {
		Unidad enemigoFueraDeRango = new Unidad(jugadorEnemigo, new Posicion(20,40), jugador.getAtributos().getInfanteriaLivianaTerrestre());
		this.unidad.atacarA(enemigoFueraDeRango);
	}

	@Test
	public void testPuedeAtacarseASiMismoEsFalse() {
		assertFalse(this.unidad.puedeAtacarA(unidad));
	}

	@Test(expected = NoEsUnEnemigo.class)
	public void testAtacarseASiMismoLanzaExcepcion() {
		this.unidad.atacarA(unidad);
	}

	@Test
	public void testPuedeAtacarEdificioPropioEsFalse() {
		assertFalse(this.unidad.puedeAtacarA(edificioPropio));
	}

	@Test(expected = NoEsUnEnemigo.class)
	public void testAtacarEdificioPropioLanzaExcepcion() {
		this.unidad.atacarA(edificioPropio);
	}

	@Test
	public void testPuedeAtacarUnidadPropiaEsFalse() {
		assertFalse(this.unidad.puedeAtacarA(otraUnidad));
	}

	@Test(expected = NoEsUnEnemigo.class)
	public void testAtacarUnidadPropiaLanzaExcepcion() {
		this.unidad.atacarA(otraUnidad);
	}

	@Test
	public void testUnidadNoPuedeHacerMagia() {
		assertFalse(this.unidad.puedeHacerMagia());
	}

}
