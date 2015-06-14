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

public class TestNaveDeCiencia extends TestUnidadMagica {
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.unidad = new Unidad(this.jugador, new Posicion(2,4), jugador.getAtributos().getInfanteriaMagica());
		this.otraUnidad = new Unidad(this.jugador, new Posicion(1,3), jugador.getAtributos().getInfanteriaMagica());
		this.edificioPropio = new PuertoEstelar(this.jugador, new Posicion(2,6));
		this.jugadorEnemigo = new Jugador("Enemigo", Color.ROJO, TipoRaza.TERRAN, mapa);
		this.unidadEnemigaTerrestre = new Unidad(jugadorEnemigo, new Posicion(5,4), jugadorEnemigo.getAtributos().getInfanteriaPesadaTerrestre());
		this.unidadEnemigaAerea = new Unidad(jugadorEnemigo, new Posicion(1,2), jugadorEnemigo.getAtributos().getInfanteriaPesadaArea());
		this.unidadEnemigaMagica = new Unidad(jugadorEnemigo, new Posicion(1,2), jugadorEnemigo.getAtributos().getInfanteriaMagica());
		this.edificioEnemigo = new Fabrica(jugadorEnemigo, new Posicion(3,3));	
	
		this.mapa.setOcupante(unidadEnemigaMagica, unidadEnemigaMagica.getPosicion());
	}
	
	@Test
	public void testNaveDeCienciaPuedeVolar() {
		assertTrue(this.unidad.puedeOcuparEspacio());
	}
	
	@Test
	public void testMagiasConsumenEnergia() {
		int energiaRelativa = unidad.getEnergia();
		unidad.getMagiaDeAreaDeEfecto().ejecutar(unidad.getPosicion(), mapa);
		assertEquals(energiaRelativa - 100, unidad.getEnergia());
		
		energiaRelativa = unidad.getEnergia();
		unidad.getMagiaAUnidad().ejecutar(unidadEnemigaTerrestre);
		assertEquals(energiaRelativa -75, unidad.getEnergia());
	}
	
	@Test
	public void testMagiaEMPCausaEfectoAUnidadMagicaCercana() {
		assertTrue(unidadEnemigaMagica.getEnergia() != 0);

		unidad.getMagiaDeAreaDeEfecto().ejecutar(unidad.getPosicion(), mapa);
		assertTrue(unidadEnemigaMagica.getEnergia() == 0);
	}
	
	@Test
	public void testMagiaRadiacionCausaEfectoAUnidadElegida() {
		int vidaRelativa = unidadEnemigaTerrestre.getVida();

		unidad.getMagiaAUnidad().ejecutar(unidadEnemigaTerrestre);
		unidadEnemigaTerrestre.pasarTurno();
		assertTrue(unidadEnemigaTerrestre.getVida() < vidaRelativa);
	}
}
