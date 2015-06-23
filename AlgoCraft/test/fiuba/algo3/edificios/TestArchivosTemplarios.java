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
import fiuba.algo3.terreno.Terreno;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.edificios.Edificio;

public class TestArchivosTemplarios extends TestEdificio {

	private Edificio acceso;
	private Edificio puerto;
	private Edificio archivos;
	private Edificio archivosEnConst;
	
	@Override
	protected Edificio crearEdificio() {
		return crearEnTierra();
	}

	@Override
	protected Edificio crearEdificioEn(Terreno terreno, Tipo tipo) {
		return crearEn((j,p) -> jugador.getEdificador().crearEntrenadorUnidadesAvanzadas(j,p), terreno, tipo);
	}
	
	@Override
	protected Edificio crearEdificioRequerido() {
		return crearEn((j,p) -> jugador.getEdificador().crearEntrenadorUnidadesBasicas(j,p),
				Terreno.TIERRA, Tipo.CELDA_VACIA);
	}
	
	@Override
	protected Edificio crearEdificioRequeridoNivel2() {
		return crearEn((j,p) -> jugador.getEdificador().crearEntrenadorUnidadesIntermedias(j,p),
				Terreno.TIERRA, Tipo.CELDA_VACIA);
	}

	@Before
	public void setUp() throws Exception {
		mapa = new MapaReal(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.PROTOSS, mapa);
		
		// Aseguro recursos
		jugador.agregarGasVespeno(800);
		jugador.agregarMinerales(600);
		this.acceso = crearEdificioRequerido();
		for(int i = 0; i < 8; i++) acceso.pasarTurno();//Construccion
		this.acceso = (Edificio) mapa.getOcupante(acceso.getPosicion());
		this.puerto = crearEdificioRequeridoNivel2();
		for(int i = 0; i < 10; i++) puerto.pasarTurno();//Construccion
		this.puerto = (Edificio) mapa.getOcupante(puerto.getPosicion());
		this.archivos = crearEdificio();
		for(int i = 0; i < 9; i++) archivos.pasarTurno();//Construccion
		this.archivos = (Edificio) mapa.getOcupante(archivos.getPosicion());
		this.archivosEnConst = crearEdificio();
	}

	
	@Test
	public void testCrearArchivos() {
		assertEquals(archivos.getNombre(),"Archivos Templarios");
	}
	
	@Test(expected = OrdenConstruccionViolado.class)
	public void testCrearArchivosSinPuertoFalla() {
		jugador = new Jugador("Prueba2", Color.AZUL, TipoRaza.PROTOSS, mapa);
		// Aseguro recursos
		jugador.agregarGasVespeno(500);
		jugador.agregarMinerales(600);
		Edificio acceso = crearEdificioRequerido();
		for(int i = 0; i < 8; i++) acceso.pasarTurno();//Construccion
		crearEdificio();
	}
	
	@Test(expected = OrdenConstruccionViolado.class)
	public void testCrearArchivosLuegoDeDestruirPuertoFalla() {
		puerto.destruir();
		crearEdificio();
	}
	
	@Test
	public void testCrearArchivosDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 200;
		int costoMineral = 150;
		
		this.archivosEnConst = crearEdificio();
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test(expected = TerrenoInadecuado.class)
	public void testCrearArchivosFueraDeTierraFalla() {
		crearFueraDeTierra();
	}
	
	@Test(expected = MineralInsuficiente.class)
	public void testCrearArchivosSinMineralesDebeFallar() {
		while (jugador.getMinerales() >= 150) jugador.agregarMinerales(-10);
		crearEdificio();
	}
	
	@Test(expected = GasVespenoInsuficiente.class)
	public void testCrearArchivosSinGasVespenoDebeFallar() {
		while (jugador.getGasVespeno() >= 200) jugador.agregarGasVespeno(-10);
		crearEdificio();
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
	public void testArchivosSubeEscudoDuranteConstruccion() {
		int escudoRelativo = archivosEnConst.getEscudo();
		for(int i = 0; i < 9; i++){
			archivosEnConst.pasarTurno();
			if (archivosEnConst.getEscudo() <= escudoRelativo) 
				fail("No aumenta el escudo en la construccion");
			escudoRelativo = archivosEnConst.getEscudo();
		}
		assertEquals(escudoRelativo, archivosEnConst.getEscudoMaximo());
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
	public void testArchivosEntrenaUnidadConsumeRecursos() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		
		archivos.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 7; i++) archivos.pasarTurno();//Entrenar AltoTemplario
		
		assertEquals(jugador.getMinerales(), mineralRelativo - 50);
		assertEquals(jugador.getGasVespeno(), gasRelativo - 150);

	}
	
	@Test(expected = MineralInsuficiente.class)
	public void testArchivosEntrenaUnidadSinMineralesDebeFallar() {
		while (jugador.getMinerales() >= 50) jugador.agregarMinerales(-10);
		archivos.getUnidadesEntrenables().get(0).crear();
	}
	
	@Test(expected = GasVespenoInsuficiente.class)
	public void testArchivosEntrenaUnidadSinGasVespenoDebeFallar() {
		while (jugador.getGasVespeno() >= 150) jugador.agregarGasVespeno(-10);
		archivos.getUnidadesEntrenables().get(0).crear();
	}
	
	@Test
	public void testArchivosEntrenarVariasUnidadesSinColaDeEspera() {
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
	public void testArchivosEntrenarVariasUnidadesConColaDeEspera() {
		// Aseguro recursos
		jugador.agregarGasVespeno(300);
		
		archivos.getUnidadesEntrenables().get(0).crear();
		archivos.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 14; i++) archivos.pasarTurno();//Entrenar 2 AltoTemplarios
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Alto Templario");
		assertEquals(jugador.getUnidades().get(1).getNombre(), "Alto Templario");
	}

	@Test(expected = SuministroInsuficiente.class)
	public void testArchviosEntrenaUnidadSinPoblacionDebeFallar() {
		while (jugador.getCapacidadPoblacion() > 1) jugador.aumentarCapacidadPoblacion(-1);
		archivos.getUnidadesEntrenables().get(0).crear();
	}
	
}
