package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import factories.EdificiosTerranFactory;
import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.raza.TipoRaza;

public class TestDepositoSuministros {

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosTerranFactory terranFactory;
	private Edificio depositoSuministros;
	
	@Before
	public void setUp() {
		//mapa = new Mapa(6);
		this.jugador = new Jugador(TipoRaza.TERRAN, Color.AZUL, mapa);
		this.terranFactory = new EdificiosTerranFactory();
	}

	//TESTS SIN REQUISITOS POR AHORA!!!
	
	@Test
	public void testCrearDepositoSuministros() {
		this.depositoSuministros = terranFactory.crearIncrementadorPoblacion(jugador, new Posicion(2,4));
		assertEquals(depositoSuministros.getNombre(),"Deposito De Suministros");
	}
	
	@Test
	public void testCrearDepositoSuministrosSinRecursosDebeFallar() {
		while (jugador.getMinerales() >= 100) {
			jugador.agregarMinerales(-10);
		}
		try {
			this.depositoSuministros = terranFactory.crearIncrementadorPoblacion(jugador, new Posicion(2,4));
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}
	}
	
	@Test
	public void testDepositoSubeVidaDuranteConstruccion() {
		this.depositoSuministros = terranFactory.crearIncrementadorPoblacion(jugador, new Posicion(2,4));
		
		int vidaRelativa = depositoSuministros.getVida();
		for(int i = 0; i < 6; i++){
			depositoSuministros.pasarTurno();
			if (depositoSuministros.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = depositoSuministros.getVida();
		}
		assertEquals(vidaRelativa, depositoSuministros.getVidaMaxima());
	}
	
	@Test
	public void testDepositoMientrasConstruyeNoAumentaPoblacion() {
		int poblacionRelativa = jugador.getPoblacion();
		this.depositoSuministros = terranFactory.crearIncrementadorPoblacion(jugador, new Posicion(2,4));
		
		assertEquals(poblacionRelativa, jugador.getPoblacion());
	}

	@Test
	public void testDepositoDespuesDeConstruirAumentaPoblacion() {
		int poblacionRelativa = jugador.getPoblacion();
		this.depositoSuministros = terranFactory.crearIncrementadorPoblacion(jugador, new Posicion(2,4));
		for(int i = 0; i < 6; i++) depositoSuministros.pasarTurno(); // Construyendo
		
		assertEquals(poblacionRelativa + 5, jugador.getPoblacion());
	}
}
