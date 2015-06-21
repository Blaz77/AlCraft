package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.RecursoAusente;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.raza.TipoRaza;

public class TestNexoMineral extends TestEdificio{

	private MapaReal mapa;
	private Jugador jugador;
	private EdificiosFactory protossFactory;
	private Edificio nexoMineral;
	
	@Override
	protected Edificio crearEdificio(Jugador jugador, Posicion posicion) {
		return protossFactory.crearRecolectorMineral(jugador, posicion);
	}
	
	private Edificio crearEnMineral(Jugador jugador, MapaReal mapa) {
		return crearEnRecurso(jugador, mapa, Tipo.MINERAL, new Posicion(0,0));
	}
	
	private Edificio crearFueraDeMineral(Jugador jugador, MapaReal mapa) {
		return crearFueraDeRecurso(jugador, mapa, Tipo.MINERAL, new Posicion(0,0));
	}
	
	@Before
	public void setUp() {
		mapa = new MapaReal(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.PROTOSS, mapa);
		this.protossFactory = new EdificiosFactory();
	}
	
	@Test
	public void testCrearNexoMineral() {
		this.nexoMineral = crearEnMineral(jugador, mapa);
		for(int i = 0; i < 4; i++) nexoMineral.pasarTurno();
		this.nexoMineral = (Edificio) mapa.getOcupante(nexoMineral.getPosicion());
		
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
	public void testPuertoEstelarSubeEscudoDuranteConstruccion() {
		this.nexoMineral = crearEnMineral(jugador, mapa);
		int escudoRelativo = nexoMineral.getEscudo();
		for(int i = 0; i < 4; i++){
			nexoMineral.pasarTurno();
			if (nexoMineral.getEscudo() <= escudoRelativo) 
				fail("No aumenta el escudo en la construccion");
			escudoRelativo = nexoMineral.getEscudo();
		}
		assertEquals(escudoRelativo, nexoMineral.getEscudoMaximo());
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
		this.nexoMineral = (Edificio) mapa.getOcupante(nexoMineral.getPosicion());
		
		int mineralRelativo = jugador.getMinerales();
		
		for(int i = 0; i < 10; i++){
			mineralRelativo += 10;
			nexoMineral.pasarTurno();
			if (jugador.getMinerales() != mineralRelativo)
				fail("El centro de Minerales no esta recolectando Minerales (de a 10)");
		}
	}
	
	@Test
	public void testNexoMineralAlDestruirseDejaMineralEnMapa() {
		this.nexoMineral = crearEnMineral(jugador, mapa);
		for(int i = 0; i < 4; i++) nexoMineral.pasarTurno(); // Construccion
		this.nexoMineral = (Edificio) mapa.getOcupante(nexoMineral.getPosicion());
		
		this.nexoMineral.destruir();
		assertEquals(Tipo.MINERAL, mapa.getOcupante(nexoMineral.getPosicion()).getTipo());
	}

}
