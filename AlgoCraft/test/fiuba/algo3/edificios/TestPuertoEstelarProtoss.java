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

public class TestPuertoEstelarProtoss extends TestEdificio {

	private MapaReal mapa;
	private Jugador jugador;
	private EdificiosFactory protossFactory;
	private Edificio acceso;
	private Edificio puerto;
	private Edificio puertoEnConst;
	
	@Override
	protected Edificio crearEdificio(Jugador jugador, Posicion posicion) {
		return protossFactory.crearEntrenadorUnidadesIntermedias(jugador, posicion);
	}
	
	@Override
	protected Edificio crearEdificioRequerido(Jugador jugador, Posicion posicion) {
		return protossFactory.crearEntrenadorUnidadesBasicas(jugador, posicion);
	}
	
	@Before
	public void setUp() throws Exception {
		mapa = new MapaReal(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.PROTOSS, mapa);
		this.protossFactory = new EdificiosFactory();
		
		// Aseguro recursos
		jugador.agregarGasVespeno(500);
		jugador.agregarMinerales(500);
		this.acceso = crearRequeridoEnTierra(jugador, mapa, new Posicion(0,0));
		for(int i = 0; i < 8; i++) acceso.pasarTurno();//Construccion
		this.acceso = (Edificio) mapa.getOcupante(acceso.getPosicion());
		this.puerto = crearEnTierra(jugador, mapa, posRelativa(acceso, 1, 1));
		for(int i = 0; i < 10; i++) puerto.pasarTurno();//Construccion
		this.puerto = (Edificio) mapa.getOcupante(puerto.getPosicion());
		this.puertoEnConst = crearEnTierra(jugador, mapa, posRelativa(puerto, 1, 1));
	}
	
	@Test
	public void testCrearPuertoEstelar() {
		assertEquals(puerto.getNombre(),"Puerto Estelar");
	}
	
	@Test
	public void testCrearPuertoEstelarSinAccesoFalla() {
		Jugador jugador2 = new Jugador("Prueba2", Color.AZUL, TipoRaza.PROTOSS, mapa);
		// Aseguro recursos
		jugador2.agregarGasVespeno(500);
		jugador2.agregarMinerales(500);
		try {
			Edificio puerto2 = crearEnTierra(jugador2, mapa, posRelativa(puertoEnConst, 1, 1));
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
		int costoGas = 150;
		int costoMineral = 150;
		
		this.puertoEnConst = crearEnTierra(jugador, mapa, posRelativa(puertoEnConst, 1,1));
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test
	public void testCrearPuertoEstelarFueraDeTierraFalla() {
		try {
			this.puertoEnConst = crearFueraDeTierra(jugador, mapa, new Posicion(0,0));
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
			this.puertoEnConst = crearEnTierra(jugador, mapa, new Posicion(0,0));
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}
	}
	
	public void testCrearPuertoEstelarSinGasVespenoDebeFallar() {
		while (jugador.getMinerales() >= 100) {
			jugador.agregarMinerales(-10);
		}
		try {
			this.puertoEnConst = crearEnTierra(jugador, mapa, new Posicion(0,0));
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
		
		for(int i = 0; i < 9; i++) puerto.pasarTurno(); //Entrenar Scout
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Scout");

		puerto.getUnidadesEntrenables().get(1).crear();
		
		for(int i = 0; i < 8; i++) puerto.pasarTurno(); //Entrenar NaveDeTransporte
		assertEquals(jugador.getUnidades().get(1).getNombre(), "Nave de transporte");
	}

	@Test
	public void testPuertoEstelarMientrasConstruyeNoTieneEscudo() {
		assertEquals(puertoEnConst.getEscudo(), 0);
	}
	
	@Test
	public void testPuertoEstelarRecienConstruidoNoTieneEscudo() {
		assertEquals(puerto.getEscudo(), 0);
	}
	
	@Test
	public void testPuertoEstelarSubeEscudoLuegoDeConstruir() {
		int escudoRelativo = puerto.getEscudo();
		for(int i = 0; i < 10; i++){
			puerto.pasarTurno();
			if (puerto.getEscudo() <= escudoRelativo) 
				fail("No aumenta el escudo luego de construir");
			escudoRelativo = puerto.getEscudo();
		}
		assertEquals(escudoRelativo, puerto.getEscudoMaximo());
	}
}
