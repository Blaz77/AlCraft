package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.SuministroInsuficiente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.edificios.EdificioEntrenadorUnidades;

public class TestAcceso extends TestEdificio {

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosFactory protossFactory; //EdificiosAbstractFactory
	private EdificioEntrenadorUnidades acceso;
	private EdificioEntrenadorUnidades accesoEnConst;
	
	@Override
	protected Edificio crearEdificio(Jugador jugador, Posicion posicion) {
		return protossFactory.crearEntrenadorUnidadesBasicas(jugador, posicion);
	}
	
	protected EdificioEntrenadorUnidades crearEnTierra(Jugador jugador, Mapa mapa) {
		return (EdificioEntrenadorUnidades) super.crearEnTierra(jugador, mapa);
	}

	protected EdificioEntrenadorUnidades crearFueraDeTierra(Jugador jugador, Mapa mapa) {
		return (EdificioEntrenadorUnidades) super.crearFueraDeTierra(jugador, mapa);
	}
	
	@Before
	public void setUp() {
		mapa = new Mapa(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.PROTOSS, mapa);
		this.jugador.agregarMinerales(300);
		this.jugador.agregarGasVespeno(300);
		this.protossFactory = new EdificiosFactory();
		this.acceso = crearEnTierra(jugador, mapa);
		for(int i = 0; i < 8; i++) acceso.pasarTurno();//Construccion
		this.accesoEnConst = crearEnTierra(jugador, mapa);
	}

	//TESTS SIN REQUISITOS POR AHORA!!!
	
	@Test
	public void testCrearAcceso() {
		assertEquals(acceso.getNombre(),"Acceso");
	}
	
	@Test
	public void testCrearAccesoDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 0;
		int costoMineral = 150;
		
		this.acceso = crearEnTierra(jugador, mapa);
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test
	public void testCrearAccesoFueraDeTierraFalla() {
		try {
			this.acceso = crearFueraDeTierra(jugador, mapa);
			fail();
		}
		catch (TerrenoInadecuado e) {
			assertTrue(true);
			return;
		}
		fail();
	}
	
	@Test
	public void testCrearAccesoSinRecursosDebeFallar() {
		while (jugador.getMinerales() >= 150) {
			jugador.agregarMinerales(-10);
		}
		try {
			this.acceso = crearEnTierra(jugador, mapa);
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}
	}
	
	@Test
	public void testAccesoSubeVidaDuranteConstruccion() {
		if (accesoEnConst.getVida() == accesoEnConst.getVidaMaxima())
			fail("La construccion inicio con la vida maxima");
		
		int vidaRelativa = accesoEnConst.getVida();		
		for(int i = 0; i < 12; i++){
			accesoEnConst.pasarTurno();
			if (accesoEnConst.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = accesoEnConst.getVida();
		}
		assertEquals(vidaRelativa, accesoEnConst.getVidaMaxima());
	}
	
	@Test
	public void testAccesoMientrasConstruyeNoPuedeEntrenar() {
		assertFalse(accesoEnConst.puedeEntrenarUnidades());
	}
	
	@Test
	public void testAccesoTerminadaPuedeEntrenar() {
		assertTrue(acceso.puedeEntrenarUnidades());
	}
	
	@Test
	public void testAccesoEntrenaZealot() {
		acceso.getUnidadesEntrenables().get(0).crear();
		
		for(int i = 0; i < 3; i++) acceso.pasarTurno();//Entrenar Zealot
		
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Zealot");

	}
	
	@Test
	public void testAccesoEntrenaDragon() {
		acceso.getUnidadesEntrenables().get(1).crear();
		
		for(int i = 0; i < 3; i++) acceso.pasarTurno();//Entrenar Dragon
		
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Dragon");

	}
	
	@Test
	public void testAccesoEntrenaUnidadConsumeRecursos() {
		int mineralRelativo = jugador.getMinerales();
		
		acceso.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 3; i++) acceso.pasarTurno();//Entrenar Zealot
		
		assertEquals(jugador.getMinerales(), mineralRelativo - 50);

	}
	
	@Test
	public void testAccesoEntrenaUnidadSinRecursosDebeFallar() {
		while (jugador.getMinerales() >= 50) {
			jugador.agregarMinerales(-10);
		}
		try {
			acceso.getUnidadesEntrenables().get(0).crear();
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}

	}
	
	@Test
	public void testAccesoEntrenarVariasUnidadesSinColaDeEspera() {		
		acceso.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 3; i++) acceso.pasarTurno();//Entrenar Zealot
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Zealot");
		
		acceso.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 3; i++) acceso.pasarTurno();//Entrenar Zealot
		assertEquals(jugador.getUnidades().get(1).getNombre(), "Zealot");
	}
	
	@Test
	public void testAccesoEntrenarVariasUnidadesConColaDeEspera() {
		acceso.getUnidadesEntrenables().get(0).crear();
		acceso.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 6; i++) acceso.pasarTurno();//Entrenar 2 Zealots
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Zealot");
		assertEquals(jugador.getUnidades().get(1).getNombre(), "Zealot");
	}

	@Test
	public void testAccesoEntrenaUnidadSinPoblacionDebeFallar() {
		while (jugador.getCapacidadPoblacion() > 1) {
			jugador.aumentarCapacidadPoblacion(-1);
		}
		acceso.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 3; i++) acceso.pasarTurno();//Entrenar Zealot
		try {
			acceso.getUnidadesEntrenables().get(0).crear();
			fail();
		}
		catch (SuministroInsuficiente e) {
			assertTrue(true);
			return;
		}

	}

}