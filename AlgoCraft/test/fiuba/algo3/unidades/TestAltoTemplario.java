package fiuba.algo3.unidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.FueraDelRangoPermitido;
import fiuba.algo3.excepciones.NoEsUnAliado;
import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.raza.TipoRaza;

public class TestAltoTemplario extends TestUnidadMagica {
	final int DANIO_TORMENTA = 100;
	final int COSTO_TORMENTA = 75;
	final int COSTO_ALUCINACION = 100;
	private Unidad unidadAliadaAtaque;
	
	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.PROTOSS, mapa);
		this.unidad = new Unidad(this.jugador, POSICION_A, jugador.getAtributos().getInfanteriaMagica());
		this.otraUnidad = new Unidad(this.jugador, POSICION_B, jugador.getAtributos().getInfanteriaMagica());
		this.unidadAliadaAtaque = new Unidad(jugador, POSICION_E, jugador.getAtributos().getInfanteriaLivianaTerrestre());
		this.edificioPropio = new Edificio(this.jugador, POSICION_C, jugador.getAtributos().getEntrenadorUnidadesAvanzadas());
		this.jugadorEnemigo = new Jugador("Enemigo", Color.ROJO, TipoRaza.TERRAN, mapa);
		this.unidadEnemigaTerrestre = new Unidad(jugadorEnemigo, POSICION_D, jugadorEnemigo.getAtributos().getInfanteriaPesadaTerrestre());
		this.unidadEnemigaMagica = new Unidad(jugadorEnemigo, POSICION_F, jugadorEnemigo.getAtributos().getInfanteriaMagica());
		this.edificioEnemigo = new Edificio(jugadorEnemigo, POSICION_G, jugadorEnemigo.getAtributos().getEntrenadorUnidadesIntermedias());	
	
		this.mapa.setOcupante(unidad, unidad.getPosicion());
		this.mapa.setOcupante(otraUnidad, otraUnidad.getPosicion());
		this.mapa.setOcupante(unidadAliadaAtaque, unidadAliadaAtaque.getPosicion());
		this.mapa.setOcupante(edificioPropio, edificioPropio.getPosicion());
		this.mapa.setOcupante(unidadEnemigaTerrestre, unidadEnemigaTerrestre.getPosicion());
		this.mapa.setOcupante(unidadEnemigaMagica, unidadEnemigaMagica.getPosicion());
		this.mapa.setOcupante(edificioEnemigo, edificioEnemigo.getPosicion());
	}
	
	private void llenarEnergia(){
		while (unidad.getEnergia() < 200) unidad.pasarTurno();
	}
	
	@Test
	public void testAltoTemplarioNoPuedeVolar() {
		assertFalse(this.unidad.puedeOcuparEspacio());
	}
	
	@Test
	public void testAltoTemplarioCreadoTiene50Energia() {
		assertEquals(50, this.unidad.getEnergia());
	}
	
	@Test
	public void testAltoTemplarioRegeneraEnergiaDeA15() {
		int energiaARegen = 15;
		
		for (int i = 0; i < 5; i++){
			int energiaRelativa = unidad.getEnergia();
			unidad.pasarTurno();
			assertEquals(energiaRelativa + energiaARegen, this.unidad.getEnergia());
		}
	}
	
	@Test
	public void testAltoTemplarioRegeneraHasta200() {
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
		assertEquals(energiaRelativa - COSTO_TORMENTA, unidad.getEnergia());
		
		energiaRelativa = unidad.getEnergia();
		unidad.getMagiaAUnidad().ejecutar(unidadAliadaAtaque);
		assertEquals(energiaRelativa - COSTO_ALUCINACION, unidad.getEnergia());
	}
	
	@Test(expected = FueraDelRangoPermitido.class)
	public void testMagiaAUnidadFueraDeRangoLanzaExcepcion() {
		this.llenarEnergia();
		otraUnidad.setPosicion(new Posicion(40,20));
		unidad.getMagiaAUnidad().ejecutar(otraUnidad);
	}
	
	@Test(expected = FueraDelRangoPermitido.class)
	public void testMagiaDeAreaDeEfectoFueraDeRangoLanzaExcepcion() {
		this.llenarEnergia();
		unidad.getMagiaDeAreaDeEfecto().ejecutar(new Posicion(40,20));
	}
	
	@Test
	public void testMagiaTormentaPsionicaCausaDanioAUnidadCercana() {
		this.llenarEnergia();
		
		assertTrue(unidadEnemigaTerrestre.getVida() != 0);
		int vidaRelativa = unidadEnemigaTerrestre.getVida();

		unidad.getMagiaDeAreaDeEfecto().ejecutar(unidad.getPosicion());
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
	public void testMagiaTormentaPsionicaNoCausaDanioAUnidadAliada() {
		this.llenarEnergia();
		
		int vidaRelativa = otraUnidad.getVida();
		assertTrue(vidaRelativa != 0);

		unidad.getMagiaDeAreaDeEfecto().ejecutar(unidad.getPosicion());
		assertEquals(otraUnidad.getVida(), vidaRelativa);
		
		unidad.pasarTurno();
		assertEquals(otraUnidad.getVida(), vidaRelativa);
		unidad.pasarTurno();
		assertEquals(otraUnidad.getVida(), vidaRelativa);
		
		// Ya deberia pasar el efecto de la magia
		unidad.pasarTurno();
		assertEquals(otraUnidad.getVida(), vidaRelativa);
	}
	
	@Test (expected = NoEsUnAliado.class)
	public void testMagiaAlucinacionAplicadoAEnemigoLanzaExcepcion() {
		this.llenarEnergia();
		unidad.getMagiaAUnidad().ejecutar(unidadEnemigaTerrestre);		
	}
	
	@Test
	public void testMagiaAlucinacionCrea2UnidadesNuevas() {
		this.llenarEnergia();
		
		int cantUnidades = jugador.getUnidades().size();
		
		unidad.getMagiaAUnidad().ejecutar(unidadAliadaAtaque);
		
		assertEquals(cantUnidades + 2, jugador.getUnidades().size());
	}
	
	@Test
	public void testMagiaAlucinacionUnidadesSonCopias() {
		this.llenarEnergia();
		
		int cantUnidades = jugador.getUnidades().size();
		
		unidad.getMagiaAUnidad().ejecutar(unidadAliadaAtaque);
		
		Unidad copia1 = jugador.getUnidades().get(cantUnidades);
		Unidad copia2 = jugador.getUnidades().get(cantUnidades+1);
		
		assertEquals(copia1.getNombre(), unidadAliadaAtaque.getNombre());
		assertEquals(copia2.getNombre(), unidadAliadaAtaque.getNombre());
	}
	
	@Test
	public void testMagiaAlucinacionUnidadesAlucinadesPuedenAtacar() {
		this.llenarEnergia();
		
		int cantUnidades = jugador.getUnidades().size();
		
		unidad.getMagiaAUnidad().ejecutar(unidadAliadaAtaque);
		
		Unidad copia1 = jugador.getUnidades().get(cantUnidades);
		Unidad copia2 = jugador.getUnidades().get(cantUnidades+1);
		
		assertTrue(copia1.puedeAtacar());
		assertTrue(copia2.puedeAtacar());
	}
	
	@Test
	public void testMagiaAlucinacionUnidadesAlucinadesAtacanSinDanio() {
		this.llenarEnergia();
		
		int cantUnidades = jugador.getUnidades().size();
		
		unidad.getMagiaAUnidad().ejecutar(unidadAliadaAtaque);
		
		Unidad copia1 = jugador.getUnidades().get(cantUnidades);
		Unidad copia2 = jugador.getUnidades().get(cantUnidades+1);
		
		int vidaRelativa = unidadEnemigaTerrestre.getVida();
		copia1.atacarA(unidadEnemigaTerrestre);
		assertEquals(vidaRelativa, unidadEnemigaTerrestre.getVida());
		
		vidaRelativa = unidadEnemigaMagica.getVida();
		copia2.atacarA(unidadEnemigaMagica);
		assertEquals(vidaRelativa, unidadEnemigaMagica.getVida());
	}
	
	@Test
	public void testMagiaAlucinacionUnidadesAlucinadesEstanEnElMapa() {
		this.llenarEnergia();
		
		int cantUnidades = jugador.getUnidades().size();
		
		unidad.getMagiaAUnidad().ejecutar(unidadAliadaAtaque);
		
		Unidad copia1 = jugador.getUnidades().get(cantUnidades);
		Unidad copia2 = jugador.getUnidades().get(cantUnidades+1);
		
		assertSame(copia1, mapa.getOcupante(copia1.getPosicion()));
		assertSame(copia2, mapa.getOcupante(copia2.getPosicion()));
	}
	
	@Test
	public void testMagiaAlucinacionUnidadesAlucinadesMuestranVidaMaxima() {
		this.llenarEnergia();
		
		int cantUnidades = jugador.getUnidades().size();
		
		unidad.getMagiaAUnidad().ejecutar(unidadAliadaAtaque);
		
		Unidad copia1 = jugador.getUnidades().get(cantUnidades);
		Unidad copia2 = jugador.getUnidades().get(cantUnidades+1);
		
		assertEquals(copia1.getVida(), unidadAliadaAtaque.getVidaMaxima());
		assertEquals(copia2.getVida(), unidadAliadaAtaque.getVidaMaxima());
	}
	
	@Test
	public void testMagiaAlucinacionUnidadesAlucinadesMuerenConEscudoEn0() {
		this.llenarEnergia();
		
		int cantUnidades = jugador.getUnidades().size();
		
		unidad.getMagiaAUnidad().ejecutar(unidadAliadaAtaque);
		
		Unidad copia1 = jugador.getUnidades().get(cantUnidades);
		Unidad copia2 = jugador.getUnidades().get(cantUnidades+1);
		
		copia1.recibirDanio(copia1.getEscudoMaximo());
		copia2.recibirDanio(copia2.getEscudoMaximo());
		
		assertNotSame(copia1, mapa.getOcupante(copia1.getPosicion()));
		assertNotSame(copia2, mapa.getOcupante(copia2.getPosicion()));
	}
	
	@Test
	public void testMagiaAlucinacionUnidadesAlucinadesRegeneranEscudo() {
		this.llenarEnergia();
		
		int cantUnidades = jugador.getUnidades().size();
		
		unidad.getMagiaAUnidad().ejecutar(unidadAliadaAtaque);
		
		Unidad copia1 = jugador.getUnidades().get(cantUnidades);
		Unidad copia2 = jugador.getUnidades().get(cantUnidades+1);
		
		copia1.recibirDanio((int) (copia1.getEscudoMaximo()*0.9));
		copia2.recibirDanio((int) (copia2.getEscudoMaximo()*0.7));
		
		int escudoRelativo1 = copia1.getEscudo();
		int escudoRelativo2 = copia2.getEscudo();
		
		copia1.pasarTurno();
		copia2.pasarTurno();
		
		assertTrue(copia1.getEscudo() > escudoRelativo1);
		assertTrue(copia2.getEscudo() > escudoRelativo2);
	}
	
	@Test
	public void testMagiaAlucinacionCreadoAPartirDeUnidadLastimadaNoCambia() {
		this.llenarEnergia();
		
		int cantUnidades = jugador.getUnidades().size();
		
		unidadAliadaAtaque.recibirDanio(unidadAliadaAtaque.getEscudoMaximo() + 
										(int)(unidadAliadaAtaque.getVidaMaxima()*0.4));
		unidad.getMagiaAUnidad().ejecutar(unidadAliadaAtaque);
		
		Unidad copia1 = jugador.getUnidades().get(cantUnidades);
		Unidad copia2 = jugador.getUnidades().get(cantUnidades+1);
		
		assertEquals(copia1.getVida(), unidadAliadaAtaque.getVidaMaxima());
		assertEquals(copia1.getEscudo(), unidadAliadaAtaque.getEscudoMaximo());
		
		assertEquals(copia2.getVida(), unidadAliadaAtaque.getVidaMaxima());
		assertEquals(copia2.getEscudo(), unidadAliadaAtaque.getEscudoMaximo());
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
