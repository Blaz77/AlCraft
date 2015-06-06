package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import factories.EdificiosTerranFactory;
import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.mapa.recurso.TipoOcupante;
import fiuba.algo3.raza.TipoRaza;

public class TestRefineria {

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosTerranFactory terranFactory;
	private Edificio refineria;
	
	private Edificio crearEnVolcan(Jugador jugador, Mapa mapa) {
		for (int y = 0; y < mapa.alto(); y++) {
			for (int x = 0; x < mapa.ancho(); x++) {
				Posicion posConVespeno = new Posicion(x, y);
				if (mapa.getOcupante(posConVespeno).getTipo() == TipoOcupante.VESPENO) {
					return terranFactory.crearRecolectorGasVespeno(jugador, posConVespeno);
				}
			}
		}
		return null;
	}
	
	@Before
	public void setUp() throws Exception {
		mapa = new Mapa(6);
		this.jugador = new Jugador(TipoRaza.TERRAN, Color.AZUL, mapa);
		this.terranFactory = new EdificiosTerranFactory();
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
		for (int y = 0; y < mapa.alto(); y++) {
			for (int x = 0; x < mapa.ancho(); x++) {
				Posicion posSinVespeno = new Posicion(x, y);
				if (mapa.getOcupante(posSinVespeno).getTipo() != TipoOcupante.VESPENO) {
					try {
						refineria = terranFactory.crearRecolectorGasVespeno(jugador, posSinVespeno);
						fail();
					}
					catch (RuntimeException e) { // Crear excepcion propia
						assertTrue(true);
						return;
					}
				}
			}
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
		
		for(int i = 0; i < 10; i++){
			gasRelativo += 10;
			refineria.pasarTurno();
			if (jugador.getGasVespeno() != gasRelativo)
				fail("La refineria no esta recolectando gas (de a 10)");
		}
	}

}
