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

public class TestDepositoSuministros extends TestEdificio {

	private MapaReal mapa;
	private Jugador jugador;
	private EdificiosFactory terranFactory;
	private Edificio depositoSuministros;
	
	
	@Override
	protected Edificio crearEdificio(Jugador jugador, Posicion posicion) {
		return terranFactory.crearIncrementadorPoblacion(jugador, posicion);
	}
	
	@Before
	public void setUp() {
		mapa = new MapaReal(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.TERRAN, mapa);
		this.terranFactory = new EdificiosFactory();
	}
	
	@Test
	public void testCrearDepositoSuministros() {
		this.depositoSuministros = crearEnTierra(jugador, mapa, new Posicion(0,0));
		for(int i = 0; i < 6; i++) depositoSuministros.pasarTurno();
		this.depositoSuministros = (Edificio) mapa.getOcupante(depositoSuministros.getPosicion());
		assertEquals(depositoSuministros.getNombre(),"Deposito De Suministros");
	}
	
	@Test
	public void testCrearDespositoSuministrosDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 0;
		int costoMineral = 100;
		
		this.depositoSuministros = crearEnTierra(jugador, mapa, new Posicion(0,0));
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test
	public void testCrearDepositoSuministrosFueraDeTierraFalla() {
		try {
			this.depositoSuministros = crearFueraDeTierra(jugador, mapa, new Posicion(0,0));
			fail();
		}
		catch (TerrenoInadecuado e) {
			assertTrue(true);
			return;
		}
		fail();
	}
	
	@Test
	public void testCrearDepositoSuministrosSinRecursosDebeFallar() {
		while (jugador.getMinerales() >= 100) {
			jugador.agregarMinerales(-10);
		}
		try {
			this.depositoSuministros = crearEnTierra(jugador, mapa, new Posicion(0,0));
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}
	}
	
	@Test
	public void testDepositoSubeVidaDuranteConstruccion() {
		this.depositoSuministros = crearEnTierra(jugador, mapa, new Posicion(0,0));
		
		int vidaRelativa = depositoSuministros.getVida();
		for(int i = 0; i < 6; i++){
			depositoSuministros.pasarTurno();
			if (depositoSuministros.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = depositoSuministros.getVida();
		}
		assertEquals(vidaRelativa, depositoSuministros.getVidaMaxima());
	}
	
	@Test
	public void testDepositoMientrasConstruyeNoAumentaPoblacion() {
		int poblacionRelativa = jugador.getCapacidadPoblacion();
		this.depositoSuministros = crearEnTierra(jugador, mapa, new Posicion(0,0));
		
		assertEquals(poblacionRelativa, jugador.getCapacidadPoblacion());
	}

	@Test
	public void testDepositoDespuesDeConstruirAumentaPoblacion() {
		int poblacionRelativa = jugador.getCapacidadPoblacion();
		this.depositoSuministros = crearEnTierra(jugador, mapa, new Posicion(0,0));
		for(int i = 0; i < 6; i++) depositoSuministros.pasarTurno(); // Construyendo
		
		assertEquals(poblacionRelativa + 5, jugador.getCapacidadPoblacion());
	}
	
	@Test
	public void testDespositoAlDestruirDisminuyePoblacion() {		
		int poblacionRelativa = jugador.getCapacidadPoblacion();
		this.depositoSuministros = crearEnTierra(jugador, mapa, new Posicion(0,0));
		for(int i = 0; i < 6; i++) depositoSuministros.pasarTurno(); // Construyendo
		this.depositoSuministros = (Edificio) mapa.getOcupante(depositoSuministros.getPosicion());
		
		this.depositoSuministros.destruir();
		assertEquals(poblacionRelativa, jugador.getCapacidadPoblacion());
	}
}
