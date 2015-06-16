package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.RecursoAusente;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;
import fiuba.algo3.raza.TipoRaza;

public class TestAsimilador extends TestEdificio {

	private MapaReal mapa;
	private Jugador jugador;
	private EdificiosFactory protossFactory;
	private Edificio asimilador;
	
	@Override
	protected Edificio crearEdificio(Jugador jugador, Posicion posicion) {
		return protossFactory.crearRecolectorGasVespeno(jugador, posicion);
	}
	
	private Edificio crearEnVolcan(Jugador jugador, MapaReal mapa) {
		return crearEnRecurso(jugador, mapa, TipoOcupante.VESPENO, new Posicion(0,0));
	}
	
	private Edificio crearFueraDeVolcan(Jugador jugador, MapaReal mapa) {
		return crearFueraDeRecurso(jugador, mapa, TipoOcupante.VESPENO, new Posicion(0,0));
	}
	
	@Before
	public void setUp() throws Exception {
		mapa = new MapaReal(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.PROTOSS, mapa);
		this.protossFactory = new EdificiosFactory();
	}
	
	@Test
	public void testCrearAsimiladorEnVolcan() {
		asimilador = crearEnVolcan(jugador, mapa);
		for(int i = 0; i < 6; i++) asimilador.pasarTurno(); // Construyendo
		this.asimilador = (Edificio) mapa.getOcupante(asimilador.getPosicion());
		
		assertNotNull(asimilador);
		assertEquals(asimilador.getNombre(),"Asimilador");
	}
	
	@Test
	public void testCrearAsimiladorDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 0;
		int costoMineral = 100;
		
		this.asimilador = crearEnVolcan(jugador, mapa);
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test
	public void testCrearAsimiladorSinMineralesDebeFallar() {
		while (jugador.getMinerales() >= 100) {
			jugador.agregarMinerales(-10);
		}
		try {
			asimilador = crearEnVolcan(jugador, mapa);
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}
	}
	
	@Test
	public void testCrearAsimiladorFueraDeVolcanFalla() {
		try {
			this.asimilador = crearFueraDeVolcan(jugador, mapa);
			fail();
		}
		catch (RecursoAusente e) {
			assertTrue(true);
			return;
		}
		fail();
	}
	
	@Test
	public void testAsimiladorSubeVidaDuranteConstruccion() {
		asimilador = crearEnVolcan(jugador, mapa);
		
		int vidaRelativa = asimilador.getVida();
		for(int i = 0; i < 6; i++){
			asimilador.pasarTurno();
			if (asimilador.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = asimilador.getVida();
		}
		assertEquals(vidaRelativa, asimilador.getVidaMaxima());
	}
	
	@Test
	public void testAsimiliadorSubeEscudoDuranteConstruccion() {
		asimilador = crearEnVolcan(jugador, mapa);
		
		int escudoRelativo = asimilador.getEscudo();
		for(int i = 0; i < 6; i++){
			asimilador.pasarTurno();
			if (asimilador.getEscudo() <= escudoRelativo) 
				fail("No aumenta el escudo en la construccion");
			escudoRelativo = asimilador.getEscudo();
		}
		assertEquals(escudoRelativo, asimilador.getEscudoMaximo());
	}
	
	@Test
	public void testAsimiladorMientrasConstruyeNoRecolecta() {
		this.asimilador = crearEnVolcan(jugador, mapa);
		int mineralRelativo = jugador.getMinerales();
		
		for(int i = 0; i < 6; i++) {
			asimilador.pasarTurno();
			if (jugador.getMinerales() != mineralRelativo)
				fail();
		}
		assertTrue(true);
	}
	
	@Test
	public void testAsimiladorRecolectaGasVespeno() {
		asimilador = crearEnVolcan(jugador, mapa);
		for(int i = 0; i < 6; i++) asimilador.pasarTurno(); // Construyendo
		this.asimilador = (Edificio) mapa.getOcupante(asimilador.getPosicion());
		
		int gasRelativo = jugador.getGasVespeno();

		for(int i = 0; i < 10; i++){
			gasRelativo += 10;
			asimilador.pasarTurno();
			if (jugador.getGasVespeno() != gasRelativo)
				fail("La refineria no esta recolectando gas (de a 10)");
		}
	}
	
}
