package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.SuministroInsuficiente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.edificios.EdificioEntrenadorUnidades;

public class TestBarraca extends TestEdificio {

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosFactory terranFactory; //EdificiosAbstractFactory
	private Edificio barraca;
	private Edificio barracaEnConst;
	
	@Override
	protected Edificio crearEdificio(Jugador jugador, Posicion posicion) {
		return terranFactory.crearEntrenadorUnidadesBasicas(jugador, posicion);
	}
	
	@Before
	public void setUp() {
		mapa = new Mapa(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.TERRAN, mapa);
		this.jugador.agregarMinerales(300);
		this.jugador.agregarGasVespeno(300);
		this.terranFactory = new EdificiosFactory();
		this.barraca = crearEnTierra(jugador, mapa);
		for(int i = 0; i < 12; i++) barraca.pasarTurno();//Construccion
		this.barracaEnConst = crearEnTierra(jugador, mapa);
	}

	//TESTS SIN REQUISITOS POR AHORA!!!
	
	@Test
	public void testCrearBarraca() {
		assertEquals(barraca.getNombre(),"Barraca");
	}
	
	@Test
	public void testCrearBarracaDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 0;
		int costoMineral = 150;
		
		this.barraca = crearEnTierra(jugador, mapa);
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test
	public void testCrearBarracaFueraDeTierraFalla() {
		try {
			this.barraca = crearFueraDeTierra(jugador, mapa);
			fail();
		}
		catch (TerrenoInadecuado e) {
			assertTrue(true);
			return;
		}
		fail();
	}
	
	@Test
	public void testCrearBarracaSinRecursosDebeFallar() {
		while (jugador.getMinerales() >= 150) {
			jugador.agregarMinerales(-10);
		}
		try {
			this.barraca = crearEnTierra(jugador, mapa);
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}
	}
	
	@Test
	public void testBarracaSubeVidaDuranteConstruccion() {
		if (barracaEnConst.getVida() == barracaEnConst.getVidaMaxima())
			fail("La construccion inicio con la vida maxima");
		
		int vidaRelativa = barracaEnConst.getVida();		
		for(int i = 0; i < 12; i++){
			barracaEnConst.pasarTurno();
			if (barracaEnConst.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = barracaEnConst.getVida();
		}
		assertEquals(vidaRelativa, barracaEnConst.getVidaMaxima());
	}
	
	@Test
	public void testBarracaMientrasConstruyeNoPuedeEntrenar() {
		assertFalse(barracaEnConst.puedeEntrenarUnidades());
	}
	
	@Test
	public void testBarracaTerminadaPuedeEntrenar() {
		assertTrue(barraca.puedeEntrenarUnidades());
	}
	
	@Test
	public void testBarracaEntrenaUnidad() {
		barraca.getUnidadesEntrenables().get(0).crear();
		
		for(int i = 0; i < 3; i++) barraca.pasarTurno();//Entrenar Marine
		
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Marine");

	}
	
	@Test
	public void testBarracaEntrenaUnidadConsumeRecursos() {
		int mineralRelativo = jugador.getMinerales();
		
		barraca.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 3; i++) barraca.pasarTurno();//Entrenar Marine
		
		assertEquals(jugador.getMinerales(), mineralRelativo - 50);

	}
	
	@Test
	public void testBarracaEntrenaUnidadSinRecursosDebeFallar() {
		while (jugador.getMinerales() >= 50) {
			jugador.agregarMinerales(-10);
		}
		try {
			barraca.getUnidadesEntrenables().get(0).crear();
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}

	}
	
	@Test
	public void testBarracaEntrenarVariasUnidadesSinColaDeEspera() {		
		barraca.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 3; i++) barraca.pasarTurno();//Entrenar Marine
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Marine");
		
		barraca.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 3; i++) barraca.pasarTurno();//Entrenar Marine
		assertEquals(jugador.getUnidades().get(1).getNombre(), "Marine");
	}
	
	@Test
	public void testBarracaEntrenarVariasUnidadesConColaDeEspera() {
		barraca.getUnidadesEntrenables().get(0).crear();
		barraca.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 6; i++) barraca.pasarTurno();//Entrenar 2 Marines
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Marine");
		assertEquals(jugador.getUnidades().get(1).getNombre(), "Marine");
	}

	@Test
	public void testBarracaEntrenaUnidadSinPoblacionDebeFallar() {
		while (jugador.getCapacidadPoblacion() > 1) {
			jugador.aumentarCapacidadPoblacion(-1);
		}
		barraca.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 3; i++) barraca.pasarTurno();//Entrenar Marine
		try {
			barraca.getUnidadesEntrenables().get(0).crear();
			fail();
		}
		catch (SuministroInsuficiente e) {
			assertTrue(true);
			return;
		}

	}

}
