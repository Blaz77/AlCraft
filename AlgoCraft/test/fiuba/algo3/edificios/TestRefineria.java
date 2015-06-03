package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.tp_final.*;
import fiuba.algo3.mapa.*;

public class TestRefineria {

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosTerranFactory terranFactory;
	private Edificio refineria;
	
	private Edificio crearEnVolcan(Jugador jugador, Mapa mapa) {
		for (Celda punto : mapa) {
			if (punto.getRecurso().getTipo() == TipoRecurso.VESPENO) {
				return terranFactory.crearRecolectorGasVespeno(jugador, punto.getX(), punto.getY());
			}
		}
		return null;
	}
	
	@Before
	public void setUp() throws Exception {
		//mapa = new Mapa(6);
		this.jugador = new Jugador(TipoRaza.TERRAN, Color.AZUL);
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
	
	public void testCrearRefineriaFueraDeVolcanFalla() {
		for (Celda punto : mapa) {
			if (punto.getRecurso().getTipo() != TipoRecurso.VESPENO) {
				try {
					refineria = terranFactory.crearRecolectorGasVespeno(jugador, punto.getX(), punto.getY());
					fail();
				}
				catch (RuntimeException e) { // Crear excepcion propia
					assertTrue(true);
				}
			}
		}
		
		assertNotNull(refineria);
		assertEquals(refineria.getNombre(),"Refineria");
	}
	
	@Test
	public void testRefineriaSubeVidaDuranteConstruccion() {
		int vidaRelativa = refineria.getVida();
		for(int i = 0; i < 4; i++){
			refineria.pasarTurno();
			if (refineria.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = refineria.getVida();
		}
		assertEquals(vidaRelativa, refineria.getVidaMaxima());
	}
	/*
	@Test
	public void testCentroDeMineralRecolectaMinerales() {
		for(int i = 0; i < 4; i++) refineria.pasarTurno();
		int mineralRelativo = jugador.getMinerales();
		
		for(int i = 0; i < 10; i++){
			mineralRelativo += 10;
			centroMineral.pasarTurno();
			if (jugador.getMinerales() != mineralRelativo)
				fail("El centro de Minerales no esta recolectando Minerales (de a 10)");
		}
	}
*/
}
