package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.RecursoAusente;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.mapa.recurso.TipoOcupante;
import fiuba.algo3.raza.TipoRaza;

public class TestRefineria extends TestEdificio {

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosFactory terranFactory;
	private Edificio refineria;
	
	@Override
	protected Construccion crearEdificio(Jugador jugador, Posicion posicion) {
		return terranFactory.crearRecolectorGasVespeno(jugador, posicion);
	}
	
	private Edificio crearEnVolcan(Jugador jugador, Mapa mapa) {
		return crearEnRecurso(jugador, mapa, TipoOcupante.VESPENO);
	}
	
	private Edificio crearFueraDeVolcan(Jugador jugador, Mapa mapa) {
		return crearFueraDeRecurso(jugador, mapa, TipoOcupante.VESPENO);
	}
	
	@Before
	public void setUp() throws Exception {
		mapa = new Mapa(6);
		this.jugador = new Jugador(TipoRaza.TERRAN, Color.AZUL, mapa);
		this.terranFactory = new EdificiosFactory();
		//this.refineria = terranFactory.crearRecolectorGasVespeno(jugador, 20, 40);
	}

	//TESTS SIN REQUISITOS POR AHORA!!!
	
	@Test
	public void testCrearRefineriaEnVolcan() {
		refineria = crearEnVolcan(jugador, mapa);
		
		assertNotNull(refineria);
		assertEquals(refineria.getNombre(),"Refineria");
	}
	
	@Test
	public void testCrearRefineriaSinMineralesDebeFallar() {
		while (jugador.getMinerales() >= 100) {
			jugador.agregarMinerales(-10);
		}
		try {
			refineria = crearEnVolcan(jugador, mapa);
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
			this.refineria = crearFueraDeVolcan(jugador, mapa);
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
		refineria = crearEnVolcan(jugador, mapa);
		
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
	public void testRefineriaRecolectaGasVespeno() {
		refineria = crearEnVolcan(jugador, mapa);
		
		for(int i = 0; i < 6; i++) refineria.pasarTurno(); // Construyendo
		int gasRelativo = jugador.getGasVespeno();
		
		this.refineria = ((Construccion)this.refineria).getEdificioTerminado();
		for(int i = 0; i < 10; i++){
			gasRelativo += 10;
			refineria.pasarTurno();
			if (jugador.getGasVespeno() != gasRelativo)
				fail("La refineria no esta recolectando gas (de a 10)");
		}
	}

}
