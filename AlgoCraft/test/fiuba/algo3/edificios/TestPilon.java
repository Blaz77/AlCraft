package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.raza.TipoRaza;

public class TestPilon extends TestEdificio {

	private MapaReal mapa;
	private Jugador jugador;
	private EdificiosFactory protossFactory;
	private Edificio pilon;
	
	
	@Override
	protected Edificio crearEdificio(Jugador jugador, Posicion posicion) {
		return protossFactory.crearIncrementadorPoblacion(jugador, posicion);
	}
	
	@Before
	public void setUp() {
		mapa = new MapaReal(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.PROTOSS, mapa);
		this.protossFactory = new EdificiosFactory();
	}
	
	@Test
	public void testCrearPilon() {
		this.pilon = crearEnTierra(jugador, mapa, new Posicion(0,0));
		for(int i = 0; i < 5; i++) pilon.pasarTurno(); //Construccion
		this.pilon = (Edificio) mapa.getOcupante(pilon.getPosicion());
		
		assertEquals(pilon.getNombre(),"Pilon");
	}
	
	@Test
	public void testCrearPilonDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 0;
		int costoMineral = 100;
		
		this.pilon = crearEnTierra(jugador, mapa, new Posicion(0,0));
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test
	public void testCrearPilonFueraDeTierraFalla() {
		try {
			this.pilon = crearFueraDeTierra(jugador, mapa, new Posicion(0,0));
			fail();
		}
		catch (TerrenoInadecuado e) {
			assertTrue(true);
			return;
		}
		fail();
	}
	
	@Test
	public void testCrearPilonSinRecursosDebeFallar() {
		while (jugador.getMinerales() >= 100) {
			jugador.agregarMinerales(-10);
		}
		try {
			this.pilon = crearEnTierra(jugador, mapa, new Posicion(0,0));
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}
	}
	
	@Test
	public void testPilonSubeVidaDuranteConstruccion() {
		this.pilon = crearEnTierra(jugador, mapa, new Posicion(0,0));
		
		int vidaRelativa = pilon.getVida();
		for(int i = 0; i < 5; i++){
			pilon.pasarTurno();
			if (pilon.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = pilon.getVida();
		}
		assertEquals(vidaRelativa, pilon.getVidaMaxima());
	}
	
	@Test
	public void testPilonSubeEscudoDuranteConstruccion() {
		this.pilon = crearEnTierra(jugador, mapa, new Posicion(0,0));
		
		int escudoRelativo = pilon.getEscudo();
		for(int i = 0; i < 5; i++){
			pilon.pasarTurno();
			if (pilon.getEscudo() <= escudoRelativo) 
				fail("No aumenta el escudo en la construccion");
			escudoRelativo = pilon.getEscudo();
		}
		assertEquals(escudoRelativo, pilon.getEscudoMaximo());
	}
	
	@Test
	public void testPilonMientrasConstruyeNoAumentaPoblacion() {
		int poblacionRelativa = jugador.getCapacidadPoblacion();
		this.pilon = crearEnTierra(jugador, mapa, new Posicion(0,0));
		
		assertEquals(poblacionRelativa, jugador.getCapacidadPoblacion());
	}

	@Test
	public void testPilonDespuesDeConstruirAumentaPoblacion() {
		int poblacionRelativa = jugador.getCapacidadPoblacion();
		this.pilon = crearEnTierra(jugador, mapa, new Posicion(0,0));
		for(int i = 0; i < 5; i++) pilon.pasarTurno(); // Construyendo
		this.pilon = (Edificio) mapa.getOcupante(pilon.getPosicion());
		
		assertEquals(poblacionRelativa + 5, jugador.getCapacidadPoblacion());
	}
	
	@Test
	public void testPilonAlDestruirDisminuyePoblacion() {
		int poblacionRelativa = jugador.getCapacidadPoblacion();
		this.pilon = crearEnTierra(jugador, mapa, new Posicion(0,0));
		for(int i = 0; i < 5; i++) pilon.pasarTurno(); // Construyendo
		this.pilon = (Edificio) mapa.getOcupante(pilon.getPosicion());
		
		this.pilon.destruir();
		assertEquals(poblacionRelativa, jugador.getCapacidadPoblacion());
	}
	
}
