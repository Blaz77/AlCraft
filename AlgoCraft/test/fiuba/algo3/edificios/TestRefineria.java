package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.RecursoAusente;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.TipoOcupante;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.raza.TipoRaza;

public class TestRefineria extends TestEdificio {

	private MapaReal mapa;
	private Jugador jugador;
	private EdificiosFactory terranFactory;
	private Edificio refineria;
	
	@Override
	protected Edificio crearEdificio(Jugador jugador, Posicion posicion) {
		return terranFactory.crearRecolectorGasVespeno(jugador, posicion);
	}
	
	private Edificio crearEnVolcan(Jugador jugador, MapaReal mapa, Posicion inicial) {
		return crearEnRecurso(jugador, mapa, Tipo.VESPENO, inicial);
	}
	
	private Edificio crearFueraDeVolcan(Jugador jugador, MapaReal mapa, Posicion inicial) {
		return crearFueraDeRecurso(jugador, mapa, Tipo.VESPENO, inicial);
	}
	
	@Before
	public void setUp() throws Exception {
		mapa = new MapaReal(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.TERRAN, mapa);
		this.terranFactory = new EdificiosFactory();
		//this.refineria = terranFactory.crearRecolectorGasVespeno(jugador, 20, 40);
	}
	
	@Test
	public void testCrearRefineriaEnVolcan() {
		refineria = crearEnVolcan(jugador, mapa, new Posicion(0,0));
		for(int i = 0; i < 6; i++) refineria.pasarTurno(); // Construyendo
		refineria = (Edificio) mapa.getOcupante(refineria.getPosicion());
		
		assertNotNull(refineria);
		assertEquals(refineria.getNombre(),"Refineria");
	}
	
	@Test
	public void testCrearRefineriaDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 0;
		int costoMineral = 100;
		
		this.refineria = crearEnVolcan(jugador, mapa, new Posicion(0,0));
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test
	public void testCrearRefineriaSinMineralesDebeFallar() {
		while (jugador.getMinerales() >= 100) {
			jugador.agregarMinerales(-10);
		}
		try {
			refineria = crearEnVolcan(jugador, mapa, new Posicion(0,0));
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}
	}
	
	@Test
	public void testCrearRefineriaFueraDeVolcanFalla() {
		try {
			this.refineria = crearFueraDeVolcan(jugador, mapa, new Posicion(0,0));
			fail();
		}
		catch (RecursoAusente e) {
			assertTrue(true);
			return;
		}
		fail();
	}
	
	@Test
	public void testRefineriaSubeVidaDuranteConstruccion() {
		refineria = crearEnVolcan(jugador, mapa, new Posicion(0,0));
		
		int vidaRelativa = refineria.getVida();
		for(int i = 0; i < 6; i++){
			refineria.pasarTurno();
			if (refineria.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = refineria.getVida();
		}
		assertEquals(vidaRelativa, refineria.getVidaMaxima());
	}
	
	@Test
	public void testRefineriaMientrasConstruyeNoRecolecta() {
		this.refineria = crearEnVolcan(jugador, mapa, new Posicion(0,0));
		int mineralRelativo = jugador.getMinerales();
		
		for(int i = 0; i < 6; i++) {
			refineria.pasarTurno();
			if (jugador.getMinerales() != mineralRelativo)
				fail();
		}
		assertTrue(true);
	}
	
	@Test
	public void testRefineriaRecolectaGasVespeno() {
		refineria = crearEnVolcan(jugador, mapa, new Posicion(0,0));
		for(int i = 0; i < 6; i++) refineria.pasarTurno(); // Construyendo
		refineria = (Edificio) mapa.getOcupante(refineria.getPosicion());
		
		int gasRelativo = jugador.getGasVespeno();

		for(int i = 0; i < 10; i++){
			gasRelativo += 10;
			refineria.pasarTurno();
			if (jugador.getGasVespeno() != gasRelativo)
				fail("La refineria no esta recolectando gas (de a 10)");
		}
	}
	
	@Test
	public void testRefineriaAlDestruirseDejaGasVespenoEnMapa() {
		this.refineria = crearEnVolcan(jugador, mapa, new Posicion(0, 0));
		for(int i = 0; i < 6; i++) refineria.pasarTurno(); // Construccion
		this.refineria = (Edificio) mapa.getOcupante(refineria.getPosicion());
		
		this.refineria.destruir();
		assertEquals(Tipo.VESPENO, mapa.getOcupante(refineria.getPosicion()).getTipo());
	}

}
