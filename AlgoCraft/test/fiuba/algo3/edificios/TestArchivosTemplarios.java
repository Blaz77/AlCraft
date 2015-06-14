package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.GasVespenoInsuficiente;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.OrdenConstruccionViolado;
import fiuba.algo3.excepciones.SuministroInsuficiente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.edificios.EdificioEntrenadorUnidades;

public class TestArchivosTemplarios extends TestEdificio {

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosFactory protossFactory;
	private Edificio acceso;
	private Edificio puerto;
	private EdificioEntrenadorUnidades archivos;
	private EdificioEntrenadorUnidades archivosEnConst;
	
	@Override
	protected Edificio crearEdificio(Jugador jugador, Posicion posicion) {
		return protossFactory.crearEntrenadorUnidadesAvanzadas(jugador, posicion);
	}
	
	@Override
	protected Edificio crearEdificioRequerido(Jugador jugador, Posicion posicion) {
		return protossFactory.crearEntrenadorUnidadesBasicas(jugador, posicion);
	}
	
	@Override
	protected Edificio crearEdificioRequeridoNivel2(Jugador jugador, Posicion posicion) {
		return protossFactory.crearEntrenadorUnidadesIntermedias(jugador, posicion);
	}
	
	protected EdificioEntrenadorUnidades crearEnTierra(Jugador jugador, Mapa mapa) {
		return (EdificioEntrenadorUnidades) super.crearEnTierra(jugador, mapa);
	}

	protected EdificioEntrenadorUnidades crearFueraDeTierra(Jugador jugador, Mapa mapa) {
		return (EdificioEntrenadorUnidades) super.crearFueraDeTierra(jugador, mapa);
	}

	@Before
	public void setUp() throws Exception {
		mapa = new Mapa(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.PROTOSS, mapa);
		this.protossFactory = new EdificiosFactory();
		
		// Aseguro recursos
		jugador.agregarGasVespeno(800);
		jugador.agregarMinerales(600);
		this.acceso = crearRequeridoEnTierra(jugador, mapa);
		for(int i = 0; i < 8; i++) acceso.pasarTurno();//Construccion
		this.puerto = crearRequeridoNivel2EnTierra(jugador, mapa);
		for(int i = 0; i < 10; i++) puerto.pasarTurno();//Construccion
		this.archivos = crearEnTierra(jugador, mapa);
		for(int i = 0; i < 9; i++) archivos.pasarTurno();//Construccion

		this.archivosEnConst = crearEnTierra(jugador, mapa);
	}

	
	@Test
	public void testCrearArchivos() {
		assertEquals(archivosEnConst.getNombre(),"Archivos Templarios");
	}
	
	@Test
	public void testCrearArchivosSinBarracaFalla() {
		Jugador jugador2 = new Jugador("Prueba2", Color.AZUL, TipoRaza.PROTOSS, mapa);
		// Aseguro recursos
		jugador2.agregarGasVespeno(500);
		jugador2.agregarMinerales(600);
		Edificio acceso2 = crearRequeridoEnTierra(jugador2, mapa);
		for(int i = 0; i < 8; i++) acceso.pasarTurno();//Construccion
		try {
			EdificioEntrenadorUnidades archivos2 = crearEnTierra(jugador2, mapa);
			fail();
		}
		catch (OrdenConstruccionViolado e) {
			assertTrue(true);
			return;
		}
		fail();
	}
	
	@Test
	public void testCrearArchivosDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 200;
		int costoMineral = 150;
		
		this.archivosEnConst = crearEnTierra(jugador, mapa);
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test
	public void testCrearArchivosFueraDeTierraFalla() {
		try {
			this.archivosEnConst = crearFueraDeTierra(jugador, mapa);
			fail();
		}
		catch (TerrenoInadecuado e) {
			assertTrue(true);
			return;
		}
		fail();
	}
	
	@Test
	public void testCrearArchivosSinMineralesDebeFallar() {
		while (jugador.getMinerales() >= 150) {
			jugador.agregarMinerales(-10);
		}
		try {
			this.archivosEnConst = crearEnTierra(jugador, mapa);
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}
	}
	
	@Test
	public void testCrearArchivosSinGasVespenoDebeFallar() {
		while (jugador.getGasVespeno() >= 200) {
			jugador.agregarGasVespeno(-10);
		}
		try {
			this.archivosEnConst = crearEnTierra(jugador, mapa);
			fail();
		}
		catch (GasVespenoInsuficiente e) {
			assertTrue(true);
			return;
		}
	}
	
	@Test
	public void testArchivosSubeVidaDuranteConstruccion() {
		int vidaRelativa = archivosEnConst.getVida();
		for(int i = 0; i < 9; i++){
			archivosEnConst.pasarTurno();
			if (archivosEnConst.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = archivosEnConst.getVida();
		}
		assertEquals(vidaRelativa, archivos.getVidaMaxima());
	}
	
	@Test
	public void testArchivosMientrasConstruyeNoPuedeEntrenar() {
		assertFalse(archivosEnConst.puedeEntrenarUnidades());
	}
	
	@Test
	public void testArchivosTerminadaPuedeEntrenar() {
		assertTrue(archivos.puedeEntrenarUnidades());
	}
	
	@Test
	public void testArchivosEntrenaUnidad() {
		archivos.getUnidadesEntrenables().get(0).crear();
		
		for(int i = 0; i < 7; i++) archivos.pasarTurno();//Entrenar AltoTemplario
		
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Alto Templario");

	}

	@Test
	public void testArchivosaEntrenaUnidadConsumeRecursos() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		
		archivos.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 7; i++) archivos.pasarTurno();//Entrenar AltoTemplario
		
		assertEquals(jugador.getMinerales(), mineralRelativo - 50);
		assertEquals(jugador.getGasVespeno(), gasRelativo - 150);

	}
	
	@Test
	public void testArchivosEntrenaUnidadSinMineralesDebeFallar() {
		while (jugador.getMinerales() >= 50) {
			jugador.agregarMinerales(-10);
		}
		try {
			archivos.getUnidadesEntrenables().get(0).crear();
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}

	}
	
	@Test
	public void testFabricaEntrenaUnidadSinGasVespenoDebeFallar() {
		while (jugador.getGasVespeno() >= 150) {
			jugador.agregarGasVespeno(-10);
		}
		try {
			archivos.getUnidadesEntrenables().get(0).crear();
			fail();
		}
		catch (GasVespenoInsuficiente e) {
			assertTrue(true);
			return;
		}

	}
	
	@Test
	public void testFabricaEntrenarVariasUnidadesSinColaDeEspera() {
		// Aseguro recursos
		jugador.agregarGasVespeno(100);
		
		archivos.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 7; i++) archivos.pasarTurno();//Entrenar AltoTemplario
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Alto Templario");
		
		archivos.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 7; i++) archivos.pasarTurno();//Entrenar AltoTemplario
		assertEquals(jugador.getUnidades().get(1).getNombre(), "Alto Templario");
	}
	
	@Test
	public void testFabricaEntrenarVariasUnidadesConColaDeEspera() {
		// Aseguro recursos
		jugador.agregarGasVespeno(300);
		
		archivos.getUnidadesEntrenables().get(0).crear();
		archivos.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 14; i++) archivos.pasarTurno();//Entrenar 2 AltoTemplarios
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Alto Templario");
		assertEquals(jugador.getUnidades().get(1).getNombre(), "Alto Templario");
	}

	@Test
	public void testFabricaEntrenaUnidadSinPoblacionDebeFallar() {
		while (jugador.getCapacidadPoblacion() > 1) {
			jugador.aumentarCapacidadPoblacion(-1);
		}
		try {
			archivos.getUnidadesEntrenables().get(0).crear();
			fail();
		}
		catch (SuministroInsuficiente e) {
			assertTrue(true);
			return;
		}

	}
	
	@Test
	public void testNexoMineralMientrasConstruyeNoTieneEscudo() {
		assertEquals(archivosEnConst.getEscudo(), 0);
	}
	
	@Test
	public void testNexoMineralRecienConstruidoNoTieneEscudo() {
		assertEquals(archivos.getEscudo(), 0);
	}
	
	@Test
	public void testNexoMineralSubeEscudoLuegoDeConstruir() {
		int escudoRelativo = archivos.getEscudo();
		for(int i = 0; i < 10; i++){
			archivos.pasarTurno();
			if (archivos.getEscudo() <= escudoRelativo) 
				fail("No aumenta el escudo luego de construir");
			escudoRelativo = archivos.getEscudo();
		}
		assertEquals(escudoRelativo, archivos.getEscudoMaximo());
	}
}
