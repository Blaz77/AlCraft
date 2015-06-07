package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import factories.EdificiosTerranFactory;
import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.RecursoAusente;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.mapa.recurso.TipoOcupante;
import fiuba.algo3.mapa.recurso.TipoRecurso;
import fiuba.algo3.raza.TipoRaza;

public class TestCentroDeMineral extends TestEdificio{

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosTerranFactory terranFactory;
	private Edificio centroMineral;
	
	@Override
	protected Edificio crearEdificio(Jugador jugador, Posicion posicion) {
		return terranFactory.crearRecolectorMineral(jugador, posicion);
	}
	
	private Edificio crearEnMineral(Jugador jugador, Mapa mapa) {
		return crearEnRecurso(jugador, mapa, TipoOcupante.MINERAL);
	}
	
	@Before
	public void setUp() throws Exception {
		mapa = new Mapa(6);
		this.jugador = new Jugador(TipoRaza.TERRAN, Color.AZUL, mapa);
		this.terranFactory = new EdificiosTerranFactory();
	}

	//TESTS SIN REQUISITOS POR AHORA!!!
	
	@Test
	public void testCrearCentroDeMineral() {
		this.centroMineral = crearEnMineral(jugador, mapa);
		assertEquals(centroMineral.getNombre(),"Centro de Mineral");
	}
	
	@Test
	public void testCrearCentroDeMineralSinRecursosDebeFallar() {
		while (jugador.getMinerales() >= 50) {
			jugador.agregarMinerales(-10);
		}
		try {
			this.centroMineral = crearEnMineral(jugador, mapa);
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}
	}
	
	@Test
	public void testCrearRefineriaFueraDeMineralFalla() {
		for (int y = 0; y < mapa.alto(); y++) {
			for (int x = 0; x < mapa.ancho(); x++) {
				Posicion posSinMineral = new Posicion(x, y);
				if (mapa.getOcupante(posSinMineral).getTipo() != TipoOcupante.MINERAL) {
					try {
						centroMineral = terranFactory.crearRecolectorMineral(jugador, posSinMineral);
						fail();
					}
					catch (RecursoAusente e) {
						assertTrue(true);
						return;
					}
				}
			}
		}
		fail();
	}
	
	@Test
	public void testCentroDeMineralSubeVidaDuranteConstruccion() {
		this.centroMineral = crearEnMineral(jugador, mapa);
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
	public void testCentroDeMineralRecolectaMinerales() {
		this.centroMineral = crearEnMineral(jugador, mapa);
		for(int i = 0; i < 4; i++) centroMineral.pasarTurno();
		int mineralRelativo = jugador.getMinerales();
		
		for(int i = 0; i < 10; i++){
			mineralRelativo += 10;
			centroMineral.pasarTurno();
			if (jugador.getMinerales() != mineralRelativo)
				fail("El centro de Minerales no esta recolectando Minerales (de a 10)");
		}
	}



}
