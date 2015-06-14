package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.GasVespenoInsuficiente;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.OrdenConstruccionViolado;
import fiuba.algo3.excepciones.SuministroInsuficiente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.ocupantes.edificios.Edificio;

public class TestFabrica extends TestEdificio {

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosFactory terranFactory;
	private Edificio barraca;
	private Edificio fabrica;
	private Edificio fabricaEnConst;
	
	@Override
	protected Edificio crearEdificio(Jugador jugador, Posicion posicion) {
		return terranFactory.crearEntrenadorUnidadesIntermedias(jugador, posicion);
	}
	
	@Override
	protected Edificio crearEdificioRequerido(Jugador jugador, Posicion posicion) {
		return terranFactory.crearEntrenadorUnidadesBasicas(jugador, posicion);
	}
	
	@Before
	public void setUp() throws Exception {
		mapa = new Mapa(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.TERRAN, mapa);
		this.terranFactory = new EdificiosFactory();
		
		// Aseguro recursos
		jugador.agregarGasVespeno(500);
		jugador.agregarMinerales(600);
		this.barraca = crearRequeridoEnTierra(jugador, mapa);
		for(int i = 0; i < 12; i++) barraca.pasarTurno();//Construccion
		this.fabrica = crearEnTierra(jugador, mapa);
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Construccion

		this.fabricaEnConst = crearEnTierra(jugador, mapa);
	}

	
	@Test
	public void testCrearFabrica() {
		assertEquals(fabricaEnConst.getNombre(),"Fabrica");
	}
	
	@Test
	public void testCrearFabricaSinBarracaFalla() {
		Jugador jugador2 = new Jugador("Prueba2", Color.AZUL, TipoRaza.TERRAN, mapa);
		// Aseguro recursos
		jugador2.agregarGasVespeno(500);
		jugador2.agregarMinerales(600);
		try {
			Edificio fabrica2 = crearEnTierra(jugador2, mapa);
			fail();
		}
		catch (OrdenConstruccionViolado e) {
			assertTrue(true);
			return;
		}
		fail();
	}
	
	@Test
	public void testCrearFabricaDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 100;
		int costoMineral = 200;
		
		this.fabricaEnConst = crearEnTierra(jugador, mapa);
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test
	public void testCrearFabricaFueraDeTierraFalla() {
		try {
			this.fabricaEnConst = crearFueraDeTierra(jugador, mapa);
			fail();
		}
		catch (TerrenoInadecuado e) {
			assertTrue(true);
			return;
		}
		fail();
	}
	
	@Test
	public void testCrearFabricaSinMineralesDebeFallar() {
		while (jugador.getMinerales() >= 200) {
			jugador.agregarMinerales(-10);
		}
		try {
			this.fabricaEnConst = crearEnTierra(jugador, mapa);
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}
	}
	
	@Test
	public void testCrearFabricaSinGasVespenoDebeFallar() {
		while (jugador.getGasVespeno() >= 100) {
			jugador.agregarGasVespeno(-10);
		}
		try {
			this.fabricaEnConst = crearEnTierra(jugador, mapa);
			fail();
		}
		catch (GasVespenoInsuficiente e) {
			assertTrue(true);
			return;
		}
	}
	
	@Test
	public void testFabricaSubeVidaDuranteConstruccion() {
		int vidaRelativa = fabricaEnConst.getVida();
		for(int i = 0; i < 12; i++){
			fabricaEnConst.pasarTurno();
			if (fabricaEnConst.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = fabricaEnConst.getVida();
		}
		assertEquals(vidaRelativa, fabrica.getVidaMaxima());
	}
	
	@Test
	public void testFabricaMientrasConstruyeNoPuedeEntrenar() {
		assertFalse(fabricaEnConst.puedeEntrenarUnidades());
	}
	
	@Test
	public void testBarracaTerminadaPuedeEntrenar() {
		assertTrue(fabrica.puedeEntrenarUnidades());
	}
	
	@Test
	public void testFabricaEntrenaUnidad() {
		fabrica.getUnidadesEntrenables().get(0).crear();
		
		for(int i = 0; i < 6; i++) fabrica.pasarTurno();//Entrenar Golliat
		
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Golliat");

	}

	@Test
	public void testFabricaEntrenaUnidadConsumeRecursos() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		
		fabrica.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 6; i++) fabrica.pasarTurno();//Entrenar Golliat
		
		assertEquals(jugador.getMinerales(), mineralRelativo - 100);
		assertEquals(jugador.getGasVespeno(), gasRelativo - 50);

	}
	
	@Test
	public void testFabricaEntrenaUnidadSinMineralesDebeFallar() {
		while (jugador.getMinerales() >= 100) {
			jugador.agregarMinerales(-10);
		}
		try {
			fabrica.getUnidadesEntrenables().get(0).crear();
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}

	}
	
	@Test
	public void testFabricaEntrenaUnidadSinGasVespenoDebeFallar() {
		while (jugador.getGasVespeno() >= 50) {
			jugador.agregarGasVespeno(-10);
		}
		try {
			fabrica.getUnidadesEntrenables().get(0).crear();
			fail();
		}
		catch (GasVespenoInsuficiente e) {
			assertTrue(true);
			return;
		}

	}
	
	@Test
	public void testFabricaEntrenarVariasUnidadesSinColaDeEspera() {
		// Aseguro recursos
		jugador.agregarGasVespeno(100);
		
		fabrica.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 6; i++) fabrica.pasarTurno();//Entrenar Golliat
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Golliat");
		
		fabrica.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 6; i++) fabrica.pasarTurno();//Entrenar Golliat
		assertEquals(jugador.getUnidades().get(1).getNombre(), "Golliat");
	}
	
	@Test
	public void testFabricaEntrenarVariasUnidadesConColaDeEspera() {
		// Aseguro recursos
		jugador.agregarGasVespeno(100);
		
		fabrica.getUnidadesEntrenables().get(0).crear();
		fabrica.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Entrenar 2 Golliats
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Golliat");
		assertEquals(jugador.getUnidades().get(1).getNombre(), "Golliat");
	}

	@Test
	public void testFabricaEntrenaUnidadSinPoblacionDebeFallar() {
		while (jugador.getCapacidadPoblacion() > 1) {
			jugador.aumentarCapacidadPoblacion(-1);
		}
		try {
			fabrica.getUnidadesEntrenables().get(0).crear();
			fail();
		}
		catch (SuministroInsuficiente e) {
			assertTrue(true);
			return;
		}

	}
}
