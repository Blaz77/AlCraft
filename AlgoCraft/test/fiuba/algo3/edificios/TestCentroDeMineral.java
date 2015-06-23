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
import fiuba.algo3.ocupantes.TipoOcupante;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.terreno.Terreno;

public class TestCentroDeMineral extends TestEdificio{

	private Edificio centroMineral;
	
	@Override
	protected Edificio crearEdificio() {
		return crearEnMineral();
	}

	@Override
	protected Edificio crearEdificioEn(Terreno terreno, Tipo tipo) {
		return crearEn((j,p) -> jugador.getEdificador().crearRecolectorMineral(j,p), terreno, tipo);
	}
	
	@Before
	public void setUp() {
		mapa = new MapaReal(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.TERRAN, mapa);
	}
	
	@Test
	public void testCrearCentroDeMineral() {
		this.centroMineral = crearEdificio();
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
		
		this.centroMineral = crearEdificio();
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test(expected = MineralInsuficiente.class)
	public void testCrearCentroDeMineralSinRecursosDebeFallar() {
		while (jugador.getMinerales() >= 50) jugador.agregarMinerales(-10);
		crearEdificio();
	}
	
	@Test(expected = RecursoAusente.class)
	public void testCrearCentroDeMineralFueraDeMineralFalla() {
		crearEnTierra();
	}
	
	@Test
	public void testCentroDeMineralSubeVidaDuranteConstruccion() {
		this.centroMineral = crearEdificio();
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
		this.centroMineral = crearEdificio();
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
		this.centroMineral = crearEdificio();
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
		this.centroMineral = crearEdificio();
		for(int i = 0; i < 4; i++) centroMineral.pasarTurno(); // Construccion
		this.centroMineral = (Edificio) mapa.getOcupante(centroMineral.getPosicion());
		
		this.centroMineral.destruir();
		assertEquals(Tipo.MINERAL, mapa.getOcupante(centroMineral.getPosicion()).getTipo());
	}



}
