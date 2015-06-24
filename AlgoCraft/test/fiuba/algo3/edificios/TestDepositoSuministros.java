package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.terreno.Terreno;

public class TestDepositoSuministros extends TestEdificio {

	private Edificio depositoSuministros;
	
	@Override
	protected Edificio crearEdificio() {
		return crearEnTierra();
	}
	
	@Override
	protected Edificio crearEdificioEn(Terreno terreno, Tipo tipo) {
		return crearEn((j,p) -> jugador.getEdificador().crearIncrementadorPoblacion(j,p), terreno, tipo);
	}

	@Before
	public void setUp() {
		mapa = new MapaReal(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.TERRAN, mapa);
	}
	
	@Test
	public void testCrearDepositoSuministros() {
		this.depositoSuministros = crearEdificio();
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
		
		this.depositoSuministros = crearEdificio();
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test(expected = TerrenoInadecuado.class)
	public void testCrearDepositoSuministrosFueraDeTierraFalla() {
		crearFueraDeTierra();
	}
	
	@Test(expected = MineralInsuficiente.class)
	public void testCrearDepositoSuministrosSinRecursosDebeFallar() {
		while (jugador.getMinerales() >= 100) jugador.agregarMinerales(-10);
		crearEdificio();
	}
	
	@Test
	public void testDepositoSubeVidaDuranteConstruccion() {
		this.depositoSuministros = crearEdificio();
		
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
		this.depositoSuministros = crearEdificio();
		
		assertEquals(poblacionRelativa, jugador.getCapacidadPoblacion());
	}

	@Test
	public void testDepositoDespuesDeConstruirAumentaPoblacion() {
		int poblacionRelativa = jugador.getCapacidadPoblacion();
		this.depositoSuministros = crearEdificio();
		for(int i = 0; i < 6; i++) depositoSuministros.pasarTurno(); // Construyendo
		
		assertEquals(poblacionRelativa + 5, jugador.getCapacidadPoblacion());
	}
	
	@Test
	public void testDespositoAlDestruirDisminuyePoblacion() {		
		int poblacionRelativa = jugador.getCapacidadPoblacion();
		this.depositoSuministros = crearEdificio();
		for(int i = 0; i < 6; i++) depositoSuministros.pasarTurno(); // Construyendo
		this.depositoSuministros = (Edificio) mapa.getOcupante(depositoSuministros.getPosicion());
		
		this.depositoSuministros.destruir();
		assertEquals(poblacionRelativa, jugador.getCapacidadPoblacion());
	}
}
