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

public class TestNexoMineral extends TestEdificio{

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosFactory protossFactory;
	private Edificio nexoMineral;
	
	@Override
	protected Edificio crearEdificio(Jugador jugador, Posicion posicion) {
		return protossFactory.crearRecolectorMineral(jugador, posicion);
	}
	
	private Edificio crearEnMineral(Jugador jugador, Mapa mapa) {
		return crearEnRecurso(jugador, mapa, TipoOcupante.MINERAL);
	}
	
	private Edificio crearFueraDeMineral(Jugador jugador, Mapa mapa) {
		return crearFueraDeRecurso(jugador, mapa, TipoOcupante.MINERAL);
	}
	
	@Before
	public void setUp() {
		mapa = new Mapa(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.PROTOSS, mapa);
		this.protossFactory = new EdificiosFactory();
	}
	
	@Test
	public void testCrearNexoMineral() {
		this.nexoMineral = crearEnMineral(jugador, mapa);
		assertEquals(nexoMineral.getNombre(),"Nexo Mineral");
	}
	
	@Test
	public void testCrearNexoMineralDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 0;
		int costoMineral = 50;
		
		this.nexoMineral = crearEnMineral(jugador, mapa);
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test
	public void testCrearNexoMineralSinRecursosDebeFallar() {
		while (jugador.getMinerales() >= 50) {
			jugador.agregarMinerales(-10);
		}
		try {
			this.nexoMineral = crearEnMineral(jugador, mapa);
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}
	}
	
	@Test
	public void testCrearNexoMineralFueraDeMineralFalla() {
		try {
			this.nexoMineral = crearFueraDeMineral(jugador, mapa);
			fail();
		}
		catch (RecursoAusente e) {
			assertTrue(true);
			return;
		}
		fail();
	}
	
	@Test
	public void testNexoMineralSubeVidaDuranteConstruccion() {
		this.nexoMineral = crearEnMineral(jugador, mapa);
		int vidaRelativa = nexoMineral.getVida();
		for(int i = 0; i < 4; i++){
			nexoMineral.pasarTurno();
			if (nexoMineral.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = nexoMineral.getVida();
		}
		assertEquals(vidaRelativa, nexoMineral.getVidaMaxima());
	}
	
	@Test
	public void testNexoMineralMientrasConstruyeNoRecolecta() {
		this.nexoMineral = crearEnMineral(jugador, mapa);
		int mineralRelativo = jugador.getMinerales();
		
		for(int i = 0; i < 4; i++) {
			nexoMineral.pasarTurno();
			if (jugador.getMinerales() != mineralRelativo)
				fail();
		}
		assertTrue(true);
	}
	
	@Test
	public void testNexoMineralRecolectaMinerales() {
		this.nexoMineral = crearEnMineral(jugador, mapa);
		for(int i = 0; i < 4; i++) nexoMineral.pasarTurno(); // Construccion
		int mineralRelativo = jugador.getMinerales();
		
		for(int i = 0; i < 10; i++){
			mineralRelativo += 10;
			nexoMineral.pasarTurno();
			if (jugador.getMinerales() != mineralRelativo)
				fail("El centro de Minerales no esta recolectando Minerales (de a 10)");
		}
	}

	@Test
	public void testNexoMineralMientrasConstruyeNoTieneEscudo() {
		this.nexoMineral = crearEnMineral(jugador, mapa);
		
		assertEquals(nexoMineral.getEscudo(), 0);
	}
	
	@Test
	public void testNexoMineralRecienConstruidoNoTieneEscudo() {
		this.nexoMineral = crearEnMineral(jugador, mapa);
		for(int i = 0; i < 4; i++) nexoMineral.pasarTurno(); // Construyendo
		
		assertEquals(nexoMineral.getEscudo(), 0);
	}
	
	@Test
	public void testNexoMineralSubeEscudoLuegoDeConstruir() {
		this.nexoMineral = crearEnMineral(jugador, mapa);
		for(int i = 0; i < 4; i++) nexoMineral.pasarTurno(); // Construyendo
		
		int escudoRelativo = nexoMineral.getEscudo();
		for(int i = 0; i < 10; i++){
			nexoMineral.pasarTurno();
			if (nexoMineral.getEscudo() <= escudoRelativo) 
				fail("No aumenta el escudo luego de construir");
			escudoRelativo = nexoMineral.getEscudo();
		}
		assertEquals(escudoRelativo, nexoMineral.getEscudoMaximo());
	}

}
