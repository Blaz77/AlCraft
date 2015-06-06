package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import factories.EdificiosTerranFactory;
import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.excepciones.GasVespenoInsuficiente;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.SuministroInsuficiente;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.unidades.Golliat;
import fiuba.algo3.unidades.Marine;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;

public class TestFabrica {

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosTerranFactory terranFactory;
	private EdificioEntrenadorUnidades fabrica;
	
	@Before
	public void setUp() throws Exception {
		//mapa = new Mapa(6);
		this.jugador = new Jugador(TipoRaza.TERRAN, Color.AZUL, mapa);
		this.terranFactory = new EdificiosTerranFactory();
		this.fabrica = terranFactory.crearEntrenadorUnidadesIntermedias(jugador, new Posicion(2,4));
	}

	//TESTS SIN REQUISITOS POR AHORA!!!
	
	@Test
	public void testCrearFabrica() {
		assertEquals(fabrica.getNombre(),"Fabrica");
	}
	
	@Test
	public void testFabricaSubeVidaDuranteConstruccion() {
		int vidaRelativa = fabrica.getVida();
		for(int i = 0; i < 12; i++){
			fabrica.pasarTurno();
			if (fabrica.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = fabrica.getVida();
		}
		assertEquals(vidaRelativa, fabrica.getVidaMaxima());
	}
	
	
	@Test
	public void testFabricaEntrenaUnidad() {
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Construccion
		
		fabrica.getUnidadesEntrenables().get(0).crear(jugador);
		
		for(int i = 0; i < 6; i++) fabrica.pasarTurno();//Entrenar Golliat
		
		assertEquals(jugador.getUnidades().get(0).getClass(), Golliat.class);

	}

	@Test
	public void testFabricaEntrenaUnidadConsumeRecursos() {
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Construccion
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		
		fabrica.getUnidadesEntrenables().get(0).crear(jugador);
		for(int i = 0; i < 6; i++) fabrica.pasarTurno();//Entrenar Golliat
		
		assertEquals(jugador.getMinerales(), mineralRelativo - 100);
		assertEquals(jugador.getGasVespeno(), gasRelativo - 50);

	}
	
	@Test
	public void testFabricaEntrenaUnidadSinMineralesDebeFallar() {
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Construccion
		
		while (jugador.getMinerales() >= 100) {
			jugador.agregarMinerales(-10);
		}
		try {
			fabrica.getUnidadesEntrenables().get(0).crear(jugador);
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}

	}
	
	@Test
	public void testFabricaEntrenaUnidadSinGasVespenoDebeFallar() {
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Construccion
		
		while (jugador.getGasVespeno() >= 50) {
			jugador.agregarGasVespeno(-10);
		}
		try {
			fabrica.getUnidadesEntrenables().get(0).crear(jugador);
			fail();
		}
		catch (GasVespenoInsuficiente e) {
			assertTrue(true);
			return;
		}

	}
	
	@Test
	public void testFabricaEntrenarVariasUnidadesSinColaDeEspera() {
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Construccion
		
		// Aseguro recursos
		jugador.agregarGasVespeno(100);
		
		fabrica.getUnidadesEntrenables().get(0).crear(jugador);
		for(int i = 0; i < 6; i++) fabrica.pasarTurno();//Entrenar Golliat
		assertEquals(jugador.getUnidades().get(0).getClass(), Golliat.class);
		
		fabrica.getUnidadesEntrenables().get(0).crear(jugador);
		for(int i = 0; i < 6; i++) fabrica.pasarTurno();//Entrenar Golliat
		assertEquals(jugador.getUnidades().get(1).getClass(), Golliat.class);
	}
	
	@Test
	public void testFabricaEntrenarVariasUnidadesConColaDeEspera() {
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Construccion
		
		// Aseguro recursos
		jugador.agregarGasVespeno(100);
		
		fabrica.getUnidadesEntrenables().get(0).crear(jugador);
		fabrica.getUnidadesEntrenables().get(0).crear(jugador);
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Entrenar 2 Golliats
		assertEquals(jugador.getUnidades().get(0).getClass(), Golliat.class);
		assertEquals(jugador.getUnidades().get(1).getClass(), Golliat.class);
	}

	@Test
	public void testFabricaEntrenaUnidadSinPoblacionDebeFallar() {
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Construccion
		
		while (jugador.getCapacidadPoblacion() > 1) {
			jugador.aumentarCapacidadPoblacion(-1);
		}
		try {
			fabrica.getUnidadesEntrenables().get(0).crear(jugador);
			fail();
		}
		catch (SuministroInsuficiente e) {
			assertTrue(true);
			return;
		}

	}
}
