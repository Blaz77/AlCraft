package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.GasVespenoInsuficiente;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.SuministroInsuficiente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.terreno.TipoTerreno;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;

public class TestFabrica extends TestEdificio {

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosFactory terranFactory;
	private EdificioEntrenadorUnidades fabrica;
	
	@Override
	protected Edificio crearEdificio(Jugador jugador, Posicion posicion) {
		return terranFactory.crearEntrenadorUnidadesIntermedias(jugador, posicion);
	}
	
	protected EdificioEntrenadorUnidades crearEnTierra(Jugador jugador, Mapa mapa) {
		return (EdificioEntrenadorUnidades) super.crearEnTierra(jugador, mapa);
	}

	protected EdificioEntrenadorUnidades crearFueraDeTierra(Jugador jugador, Mapa mapa) {
		return (EdificioEntrenadorUnidades) super.crearFueraDeTierra(jugador, mapa);
	}
	
	
	@Before
	public void setUp() throws Exception {
		mapa = new Mapa(6);
		this.jugador = new Jugador(TipoRaza.TERRAN, Color.AZUL, mapa);
		this.terranFactory = new EdificiosFactory();
		
		// Aseguro recursos
		jugador.agregarGasVespeno(50);
		this.fabrica = crearEnTierra(jugador, mapa);
	}

	//TESTS SIN REQUISITOS POR AHORA!!!
	
	@Test
	public void testCrearFabrica() {
		assertEquals(fabrica.getNombre(),"Fabrica");
	}
	
	@Test
	public void testCrearFabricaFueraDeTierraFalla() {
		try {
			this.fabrica = crearFueraDeTierra(jugador, mapa);
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
			this.fabrica = crearEnTierra(jugador, mapa);
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
			this.fabrica = crearEnTierra(jugador, mapa);
			fail();
		}
		catch (GasVespenoInsuficiente e) {
			assertTrue(true);
			return;
		}
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
		
		fabrica.getUnidadesEntrenables().get(0).crear();
		
		for(int i = 0; i < 6; i++) fabrica.pasarTurno();//Entrenar Golliat
		
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Golliat");

	}

	@Test
	public void testFabricaEntrenaUnidadConsumeRecursos() {
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Construccion
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		
		fabrica.getUnidadesEntrenables().get(0).crear();
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
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Construccion
		
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
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Construccion
		
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
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Construccion
		
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
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Construccion
		
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
