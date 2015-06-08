package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.excepciones.GasVespenoInsuficiente;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.terreno.TipoTerreno;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;

public class TestPuertoEstelar extends TestEdificio {

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosFactory terranFactory;
	private EdificioEntrenadorUnidades puerto;
	
	@Override
	protected Edificio crearEdificio(Jugador jugador, Posicion posicion) {
		return terranFactory.crearEntrenadorUnidadesAvanzadas(jugador, posicion);
	}
	
	protected EdificioEntrenadorUnidades crearEnTierra(Jugador jugador, Mapa mapa) {
		return (EdificioEntrenadorUnidades) super.crearEnTierra(jugador, mapa);
	}
	
	@Before
	public void setUp() throws Exception {
		mapa = new Mapa(6);
		this.jugador = new Jugador(TipoRaza.TERRAN, Color.AZUL, mapa);
		this.terranFactory = new EdificiosFactory();
		
		// Aseguro recursos
		jugador.agregarGasVespeno(50);
		this.puerto = crearEnTierra(jugador, mapa);
	}

	//TESTS SIN REQUISITOS POR AHORA!!!
	
	@Test
	public void testCrearPuertoEstelar() {
		assertEquals(puerto.getNombre(),"Puerto Estelar");
	}
	
	@Test
	public void testCrearPuertoEstelarFueraDeTierraFalla() {
		for (int y = 0; y < mapa.alto(); y++) {
			for (int x = 0; x < mapa.ancho(); x++) {
				Posicion posFueraDeTierra = new Posicion(x, y);
				if (mapa.getTerreno(posFueraDeTierra).getTipo() != TipoTerreno.TIERRA) {
					try {
						this.puerto = terranFactory.crearEntrenadorUnidadesAvanzadas(jugador, posFueraDeTierra);
						fail();
					}
					catch (TerrenoInadecuado e) {
						assertTrue(true);
						return;
					}
				}
			}
		}
		fail();
	}
	
	@Test
	public void testCrearPuertoEstelarSinMineralesDebeFallar() {
		while (jugador.getMinerales() >= 150) {
			jugador.agregarMinerales(-10);
		}
		try {
			this.puerto = crearEnTierra(jugador, mapa);
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
			this.puerto = crearEnTierra(jugador, mapa);
			fail();
		}
		catch (GasVespenoInsuficiente e) {
			assertTrue(true);
			return;
		}
	}
	
	@Test
	public void testPuertoEstelarSubeVidaDuranteConstruccion() {
		int vidaRelativa = puerto.getVida();
		for(int i = 0; i < 10; i++){
			puerto.pasarTurno();
			if (puerto.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = puerto.getVida();
		}
		assertEquals(vidaRelativa, puerto.getVidaMaxima());
	}
	
	
	@Test
	public void testPuertoEstelarEntrenaUnidad() {
		//Junto recursos y poblacion para entrenar
		jugador.agregarGasVespeno(1000);
		jugador.agregarMinerales(1000);
		jugador.aumentarCapacidadPoblacion(10);
		
		
		for(int i = 0; i < 10; i++) puerto.pasarTurno();//Construccion
		
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
