package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import factories.EdificiosTerranFactory;
import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.raza.TipoRaza;

public class TestCentroDeMineral {

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosTerranFactory terranFactory;
	private Edificio centroMineral;
	
	@Before
	public void setUp() throws Exception {
		//mapa = new Mapa(6);
		this.jugador = new Jugador(TipoRaza.TERRAN, Color.AZUL, mapa);
		this.terranFactory = new EdificiosTerranFactory();
		this.centroMineral = terranFactory.crearRecolectorMineral(jugador, 20, 40);
	}

	//TESTS SIN REQUISITOS POR AHORA!!!
	
	@Test
	public void testCrearCentroDeMineral() {
		assertEquals(centroMineral.getNombre(),"Centro de Mineral");
	}
	
	@Test
	public void testCentroDeMineralSubeVidaDuranteConstruccion() {
		int vidaRelativa = centroMineral.getVida();
		for(int i = 0; i < 4; i++){
			centroMineral.pasarTurno();
			if (centroMineral.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = centroMineral.getVida();
		}
		assertEquals(vidaRelativa, centroMineral.getVidaMaxima());
	}
	
	@Test
	public void testCentroDeMineralRecolectaMinerales() {
		for(int i = 0; i < 4; i++) centroMineral.pasarTurno();
		int mineralRelativo = jugador.getMinerales();
		
		for(int i = 0; i < 10; i++){
			mineralRelativo += 10;
			centroMineral.pasarTurno();
			if (jugador.getMinerales() != mineralRelativo)
				fail("El centro de Minerales no esta recolectando Minerales (de a 10)");
		}
	}

}
