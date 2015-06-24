package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import java.util.Arrays;

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

public class TestArchivosTemplarios extends TestEdificioEntrenador {

	private Edificio acceso;
	private Edificio puerto;
	private Edificio archivos;
	private Edificio archivosEnConst;
	private final int turnosAltoTemplario = 7;
	
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
		jugador.agregarGasVespeno(1200);
		jugador.agregarMinerales(1200);
		jugador.aumentarCapacidadPoblacion(20);
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
		entrenador = this.archivos;
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
	public void testArchivosTerminadoPuedeEntrenar() {
		assertTrue(archivos.puedeEntrenarUnidades());
	}
	
	@Test
	public void testArchivosPuedeEntrenarUnaUnidad() {
		assertEquals(1, archivos.getUnidadesEntrenables().size());
	}
	
	@Test
	public void testArchivosPuedeEntrenarAltoTemplario() {
		puedeEntrenarUnidad(Tipo.ALTO_TEMPLARIO);
	}
	
	@Test
	public void testArchivosMientrasEntrenaAltoTemplarioNoEstaDisponible() {
		mientrasEntrenaUnidadNoDisponible(Tipo.ALTO_TEMPLARIO, turnosAltoTemplario);
	}
	
	@Test
	public void testArchivosEntrenaAltoTemplario() {
		entrenarUnidadVerJugador(Tipo.ALTO_TEMPLARIO, turnosAltoTemplario);
	}
	
	@Test
	public void testArchivosEntrenaAltoTemplarioDejaEnElMapa() {
		entrenarUnidadVerMapa(Tipo.ALTO_TEMPLARIO, turnosAltoTemplario);
	}
	
	@Test
	public void testArchivosEntrenaAltoTemplarioConsumeRecursos() {
		entrenarUnidadConsumeRecursos(Tipo.ALTO_TEMPLARIO, turnosAltoTemplario, 50, 150, 2);
	}
	
	@Test(expected = MineralInsuficiente.class)
	public void testArchivosEntrenaAltoTemplarioSinMineralesDebeFallar() {
		entrenarSinMinerales(Tipo.ALTO_TEMPLARIO);
	}
	
	@Test(expected = GasVespenoInsuficiente.class)
	public void testArchivosEntrenaAltoTemplarioSinGasVespenoDebeFallar() {
		entrenarSinGasVespeno(Tipo.ALTO_TEMPLARIO);
	}
	
	@Test(expected = SuministroInsuficiente.class)
	public void testArchivosEntrenaAltoTemplarioSinPoblacionDebeFallar() {
		entrenarSinCapacidadPoblacion(Tipo.ALTO_TEMPLARIO);
	}
	
	@Test
	public void testArchivosEntrenaVariasUnidadesSinColaDeEspera() {		
		for (int i = 0; i < 3; i++)
			entrenarUnidadVerJugador(Tipo.ALTO_TEMPLARIO, turnosAltoTemplario);
	}
	
	@Test
	public void testArchivosEntrenaVariasUnidadesConColaDeEspera() {
		entrenarVariasUnidadesSostieneElOrden(
					Arrays.asList(archivos.getUnidadesEntrenables().get(0),
							archivos.getUnidadesEntrenables().get(0)), 
							turnosAltoTemplario, turnosAltoTemplario);
	}	
}
