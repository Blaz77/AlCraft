package fiuba.algo3.unidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.raza.TipoRaza;

public class TestNaveDeCiencia extends TestUnidadMagica {
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.TERRAN, mapa);
		this.unidad = new Unidad(this.jugador, POSICION_A, jugador.getAtributos().getInfanteriaMagica());
		this.otraUnidad = new Unidad(this.jugador, POSICION_B, jugador.getAtributos().getInfanteriaMagica());
		this.edificioPropio = new Edificio(this.jugador, POSICION_C, jugador.getAtributos().getEntrenadorUnidadesAvanzadas());
		this.jugadorEnemigo = new Jugador("Enemigo", Color.ROJO, TipoRaza.TERRAN, mapa);
		this.unidadEnemigaTerrestre = new Unidad(jugadorEnemigo, POSICION_D, jugadorEnemigo.getAtributos().getInfanteriaPesadaTerrestre());
		this.unidadEnemigaAerea = new Unidad(jugadorEnemigo, POSICION_E, jugadorEnemigo.getAtributos().getInfanteriaPesadaArea());
		this.unidadEnemigaMagica = new Unidad(jugadorEnemigo, POSICION_F, jugadorEnemigo.getAtributos().getInfanteriaMagica());
		this.edificioEnemigo = new Edificio(jugadorEnemigo, POSICION_G, jugadorEnemigo.getAtributos().getEntrenadorUnidadesIntermedias());
	
		this.mapa.setOcupante(unidad, unidad.getPosicion());
		this.mapa.setOcupante(otraUnidad, otraUnidad.getPosicion());
		this.mapa.setOcupante(edificioPropio, edificioPropio.getPosicion());
		this.mapa.setOcupante(unidadEnemigaTerrestre, unidadEnemigaTerrestre.getPosicion());
		this.mapa.setOcupante(unidadEnemigaAerea, unidadEnemigaAerea.getPosicion());
		this.mapa.setOcupante(unidadEnemigaMagica, unidadEnemigaMagica.getPosicion());
		this.mapa.setOcupante(edificioEnemigo, edificioEnemigo.getPosicion());
	}
	
	private void llenarEnergia(){
		while (unidad.getEnergia() < 200) unidad.pasarTurno();
	}
	
	@Test
	public void testNaveDeCienciaPuedeVolar() {
		assertTrue(this.unidad.puedeOcuparEspacio());
	}
	
	@Test
	public void testNaveDeCienciaCreadaTiene50Energia() {
		assertEquals(50, this.unidad.getEnergia());
	}
	
	@Test
	public void testNaveDeCienciaRegeneraEnergiaDeA10() {
		int energiaARegen = 10;
		
		for (int i = 0; i < 5; i++){
			int energiaRelativa = unidad.getEnergia();
			unidad.pasarTurno();
			assertEquals(energiaRelativa + energiaARegen, this.unidad.getEnergia());
		}
	}
	
	@Test
	public void testNaveDeCienciaRegeneraHasta200() {
		for (int i = 0; i < 100; i++){
			int energiaRelativa = unidad.getEnergia();
			unidad.pasarTurno();
			if (energiaRelativa == 200)
				assertTrue(energiaRelativa == unidad.getEnergia());
			else
				assertTrue(energiaRelativa < unidad.getEnergia());
		}
	}
	
	@Test
	public void testMagiasConsumenEnergia() {
		this.llenarEnergia();
		
		int energiaRelativa = unidad.getEnergia();
		unidad.getMagiaDeAreaDeEfecto().ejecutar(unidad.getPosicion());
		assertEquals(energiaRelativa - 100, unidad.getEnergia());
		
		energiaRelativa = unidad.getEnergia();
		unidad.getMagiaAUnidad().ejecutar(unidadEnemigaTerrestre);
		assertEquals(energiaRelativa -75, unidad.getEnergia());
	}
	
	@Test
	public void testMagiaEMPCausaEfectoAUnidadMagicaCercana() {
		this.llenarEnergia();
		
		assertTrue(unidadEnemigaMagica.getEnergia() != 0);

		unidad.getMagiaDeAreaDeEfecto().ejecutar(unidad.getPosicion());
		assertTrue(unidadEnemigaMagica.getEnergia() == 0);
	}
	
	@Test
	public void testMagiaEMPNoAfectaAUnidadesAliadas() {
		this.llenarEnergia();
		
		int energiaRelativa = otraUnidad.getEnergia();
		assertTrue(energiaRelativa != 0);

		unidad.getMagiaDeAreaDeEfecto().ejecutar(unidad.getPosicion());
		assertEquals(otraUnidad.getEnergia(), energiaRelativa);
	}
	
	@Test
	public void testMagiaRadiacionCausaEfectoAUnidadElegida() {
		this.llenarEnergia();
		
		int vidaRelativa = unidadEnemigaTerrestre.getVida();

		unidad.getMagiaAUnidad().ejecutar(unidadEnemigaTerrestre);
		unidadEnemigaTerrestre.pasarTurno();
		assertTrue(unidadEnemigaTerrestre.getVida() < vidaRelativa);
	}
}
