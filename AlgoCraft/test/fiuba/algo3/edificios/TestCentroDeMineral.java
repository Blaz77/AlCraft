package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.RecursoAusente;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;
import fiuba.algo3.raza.TipoRaza;

public class TestCentroDeMineral extends TestEdificio{

	private MapaReal mapa;
	private Jugador jugador;
	private EdificiosFactory terranFactory;
	private Edificio centroMineral;
	
	@Override
	protected Edificio crearEdificio(Jugador jugador, Posicion posicion) {
		return terranFactory.crearRecolectorMineral(jugador, posicion);
	}
	
	private Edificio crearEnMineral(Jugador jugador, MapaReal mapa, Posicion inicial) {
		return crearEnRecurso(jugador, mapa, TipoOcupante.MINERAL, inicial);
	}
	
	private Edificio crearFueraDeMineral(Jugador jugador, MapaReal mapa, Posicion inicial) {
		return crearFueraDeRecurso(jugador, mapa, TipoOcupante.MINERAL, inicial);
	}
	
	@Before
	public void setUp() {
		mapa = new MapaReal(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.TERRAN, mapa);
		this.terranFactory = new EdificiosFactory();
	}
	
	@Test
	public void testCrearCentroDeMineral() {
		this.centroMineral = crearEnMineral(jugador, mapa, new Posicion(0,0));
		for(int i = 0; i < 4; i++) centroMineral.pasarTurno();
		this.centroMineral = (Edificio) mapa.getOcupante(centroMineral.getPosicion());
		assertEquals(centroMineral.getNombre(),"Centro de Mineral");
	}
	
	@Test
	public void testCrearCentroDeMineralDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 0;
		int costoMineral = 50;
		
		this.centroMineral = crearEnMineral(jugador, mapa, new Posicion(0,0));
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test
	public void testCrearCentroDeMineralSinRecursosDebeFallar() {
		while (jugador.getMinerales() >= 50) {
			jugador.agregarMinerales(-10);
		}
		try {
			this.centroMineral = crearEnMineral(jugador, mapa, new Posicion(0,0));
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}
	}
	
	@Test
	public void testCrearCentroDeMineralFueraDeMineralFalla() {
		try {
			this.centroMineral = crearFueraDeMineral(jugador, mapa, new Posicion(0,0));
			fail();
		}
		catch (RecursoAusente e) {
			assertTrue(true);
			return;
		}
		fail();
	}
	
	@Test
	public void testCentroDeMineralSubeVidaDuranteConstruccion() {
		this.centroMineral = crearEnMineral(jugador, mapa, new Posicion(0,0));
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
	public void testCentroDeMineralMientrasConstruyeNoRecolecta() {
		this.centroMineral = crearEnMineral(jugador, mapa, new Posicion(0,0));
		int mineralRelativo = jugador.getMinerales();
		
		for(int i = 0; i < 4; i++) {
			centroMineral.pasarTurno();
			if (jugador.getMinerales() != mineralRelativo)
				fail();
		}
		assertTrue(true);
	}
	
	@Test
	public void testCentroDeMineralRecolectaMinerales() {
		this.centroMineral = crearEnMineral(jugador, mapa, new Posicion(0,0));
		for(int i = 0; i < 4; i++) centroMineral.pasarTurno(); // Construccion
		this.centroMineral = (Edificio) mapa.getOcupante(centroMineral.getPosicion());
	
		int mineralRelativo = jugador.getMinerales();
			
		for(int i = 0; i < 10; i++){
			mineralRelativo += 10;
			centroMineral.pasarTurno();
			if (jugador.getMinerales() != mineralRelativo)
				fail("El centro de Minerales no esta recolectando Minerales (de a 10)");
		}
	}
	
	@Test
	public void testCentroDeMineralAlDestruirseDejaMineralEnMapa() {
		this.centroMineral = crearEnMineral(jugador, mapa, new Posicion(0, 0));
		for(int i = 0; i < 4; i++) centroMineral.pasarTurno(); // Construccion
		this.centroMineral = (Edificio) mapa.getOcupante(centroMineral.getPosicion());
		
		this.centroMineral.destruir();
		assertEquals(TipoOcupante.MINERAL, mapa.getOcupante(centroMineral.getPosicion()).getTipo());
	}



}
