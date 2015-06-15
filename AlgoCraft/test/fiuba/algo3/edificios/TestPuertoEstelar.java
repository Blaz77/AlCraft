package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.GasVespenoInsuficiente;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.OrdenConstruccionViolado;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.ocupantes.edificios.Edificio;

public class TestPuertoEstelar extends TestEdificio {

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosFactory terranFactory;
	private Edificio fabrica;
	private Edificio barraca;
	private Edificio puerto;
	private Edificio puertoEnConst;
	
	@Override
	protected Edificio crearEdificio(Jugador jugador, Posicion posicion) {
		return terranFactory.crearEntrenadorUnidadesAvanzadas(jugador, posicion);
	}
	
	@Override
	protected Edificio crearEdificioRequerido(Jugador jugador, Posicion posicion) {
		return terranFactory.crearEntrenadorUnidadesBasicas(jugador, posicion);
	}
	
	@Override
	protected Edificio crearEdificioRequeridoNivel2(Jugador jugador, Posicion posicion) {
		return terranFactory.crearEntrenadorUnidadesIntermedias(jugador, posicion);
	}
	
	@Before
	public void setUp() throws Exception {
		mapa = new Mapa(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.TERRAN, mapa);
		this.terranFactory = new EdificiosFactory();
		
		// Aseguro recursos
		jugador.agregarGasVespeno(600);
		jugador.agregarMinerales(850);
		this.barraca = crearRequeridoEnTierra(jugador, mapa, new Posicion(0,0));
		for(int i = 0; i < 12; i++) barraca.pasarTurno();//Construccion
		this.barraca = (Edificio) mapa.getOcupante(barraca.getPosicion());
		this.fabrica = crearRequeridoNivel2EnTierra(jugador, mapa, posRelativa(barraca, 1, 1));
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Construccion
		this.fabrica = (Edificio) mapa.getOcupante(fabrica.getPosicion());
		this.puerto = crearEnTierra(jugador, mapa, posRelativa(fabrica, 1, 1));
		for(int i = 0; i < 10; i++) puerto.pasarTurno();//Construccion
		this.puerto = (Edificio) mapa.getOcupante(puerto.getPosicion());
		this.puertoEnConst = crearEnTierra(jugador, mapa, posRelativa(puerto, 1, 1));
	}
	
	@Test
	public void testCrearPuertoEstelar() {
		assertEquals(puerto.getNombre(),"Puerto Estelar");
	}
	
	@Test
	public void testCrearPuertoEstelarSinFabricaFalla() {
		Jugador jugador2 = new Jugador("Prueba2", Color.AZUL, TipoRaza.TERRAN, mapa);
		// Aseguro recursos
		jugador2.agregarGasVespeno(500);
		jugador2.agregarMinerales(600);
		Edificio barraca2 = crearRequeridoEnTierra(jugador2, mapa, new Posicion(16, 16));
		for(int i = 0; i < 12; i++) barraca.pasarTurno();//Construccion
		try {
			Edificio puerto2 = crearEnTierra(jugador2, mapa, new Posicion(16, 16));
			fail();
		}
		catch (OrdenConstruccionViolado e) {
			assertTrue(true);
			return;
		}
		fail();
	}
	
	@Test
	public void testCrearPuertoEstelarDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 100;
		int costoMineral = 150;
		
		this.puertoEnConst = crearEnTierra(jugador, mapa, new Posicion(16, 16));
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test
	public void testCrearPuertoEstelarFueraDeTierraFalla() {
		try {
			this.puertoEnConst = crearFueraDeTierra(jugador, mapa, new Posicion(16, 16));
			fail();
		}
		catch (TerrenoInadecuado e) {
			assertTrue(true);
			return;
		}
		fail();
	}
	
	@Test
	public void testCrearPuertoEstelarSinMineralesDebeFallar() {
		while (jugador.getMinerales() >= 150) {
			jugador.agregarMinerales(-10);
		}
		try {
			this.puertoEnConst = crearEnTierra(jugador, mapa, new Posicion(16, 16));
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}
	}
	
	@Test
	public void testCrearPuertoEstelarSinGasVespenoDebeFallar() {
		while (jugador.getGasVespeno() >= 100) {
			jugador.agregarGasVespeno(-10);
		}
		try {
			this.puertoEnConst = crearEnTierra(jugador, mapa, new Posicion(16, 16));
			fail();
		}
		catch (GasVespenoInsuficiente e) {
			assertTrue(true);
			return;
		}
	}
	
	@Test
	public void testPuertoEstelarSubeVidaDuranteConstruccion() {
		int vidaRelativa = puertoEnConst.getVida();
		for(int i = 0; i < 10; i++){
			puertoEnConst.pasarTurno();
			if (puertoEnConst.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = puertoEnConst.getVida();
		}
		assertEquals(vidaRelativa, puerto.getVidaMaxima());
	}
	
	@Test
	public void testPuertoEstelarMientrasConstruyeNoPuedeEntrenar() {
		assertFalse(puertoEnConst.puedeEntrenarUnidades());
	}
	
	@Test
	public void testPuertoEstelarTerminadoPuedeEntrenar() {
		assertTrue(puerto.puedeEntrenarUnidades());
	}
	
	@Test
	public void testPuertoEstelarEntrenaUnidad() {
		//Junto recursos y poblacion para entrenar
		jugador.agregarGasVespeno(1000);
		jugador.agregarMinerales(1000);
		jugador.aumentarCapacidadPoblacion(10);
		
		puerto.getUnidadesEntrenables().get(0).crear();
		
		for(int i = 0; i < 8; i++) puerto.pasarTurno(); //Entrenar Espectro
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Espectro");

		puerto.getUnidadesEntrenables().get(1).crear();
		
		for(int i = 0; i < 10; i++) puerto.pasarTurno(); //Entrenar NaveDeCiencia
		assertEquals(jugador.getUnidades().get(1).getNombre(), "Nave de ciencia");
		
		puerto.getUnidadesEntrenables().get(2).crear();
		
		for(int i = 0; i < 7; i++) puerto.pasarTurno(); //Entrenar NaveDeTransporte
		assertEquals(jugador.getUnidades().get(2).getNombre(), "Nave de transporte");
	}

}
