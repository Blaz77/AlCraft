package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.tp_final.*;
import fiuba.algo3.mapa.*;

public class TestDepositoSuministros {

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosTerranFactory terranFactory;
	private Edificio depositoSuministros;
	
	@Before
	public void setUp() throws Exception {
		//mapa = new Mapa(6);
		this.jugador = new Jugador(TipoRaza.TERRAN, Color.AZUL, mapa);
		this.terranFactory = new EdificiosTerranFactory();
	}

	//TESTS SIN REQUISITOS POR AHORA!!!
	
	@Test
	public void testCrearDepositoSuministros() {
		this.depositoSuministros = terranFactory.crearIncrementadorPoblacion(jugador, 20, 40);
		assertEquals(depositoSuministros.getNombre(),"Deposito De Suministros");
	}
	
	@Test
	public void testDepositoSubeVidaDuranteConstruccion() {
		this.depositoSuministros = terranFactory.crearIncrementadorPoblacion(jugador, 20, 40);
		
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
		this.depositoSuministros = terranFactory.crearIncrementadorPoblacion(jugador, 20, 40);
		
		assertEquals(poblacionRelativa, jugador.getPoblacion());
	}

	@Test
	public void testDepositoDespuesDeConstruirAumentaPoblacion() {
		int poblacionRelativa = jugador.getPoblacion();
		this.depositoSuministros = terranFactory.crearIncrementadorPoblacion(jugador, 20, 40);
		for(int i = 0; i < 6; i++) depositoSuministros.pasarTurno(); // Construyendo
		
		assertEquals(poblacionRelativa + 5, jugador.getPoblacion());
	}
}
