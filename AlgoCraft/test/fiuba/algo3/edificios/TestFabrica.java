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
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.terreno.Terreno;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.edificios.Edificio;

public class TestFabrica extends TestEdificioEntrenador {

	private Edificio barraca;
	private Edificio fabrica;
	private Edificio fabricaEnConst;
	private final int turnosGolliat = 6;
	
	@Override
	protected Edificio crearEdificio() {
		return crearEnTierra();
	}

	@Override
	protected Edificio crearEdificioEn(Terreno terreno, Tipo tipo) {
		return crearEn((j,p) -> jugador.getEdificador().crearEntrenadorUnidadesIntermedias(j,p), terreno, tipo);
	}
	
	@Override
	protected Edificio crearEdificioRequerido() {
		return crearEn((j,p) -> jugador.getEdificador().crearEntrenadorUnidadesBasicas(j,p),
				Terreno.TIERRA, Tipo.CELDA_VACIA);
	}
	
	@Before
	public void setUp() throws Exception {
		mapa = new MapaReal(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.TERRAN, mapa);
		
		// Aseguro recursos
		jugador.agregarGasVespeno(800);
		jugador.agregarMinerales(800);
		jugador.aumentarCapacidadPoblacion(10);
		this.barraca = crearEdificioRequerido();
		for(int i = 0; i < 12; i++) barraca.pasarTurno();//Construccion
		this.barraca = (Edificio) mapa.getOcupante(barraca.getPosicion());
		this.fabrica = crearEdificio();
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Construccion
		this.fabrica = (Edificio) mapa.getOcupante(fabrica.getPosicion());
		this.fabricaEnConst = crearEdificio();
		entrenador = this.fabrica;
	}

	
	@Test
	public void testCrearFabrica() {
		assertEquals(fabrica.getNombre(),"Fabrica");
	}
	
	@Test(expected = OrdenConstruccionViolado.class)
	public void testCrearFabricaSinBarracaFalla() {
		jugador = new Jugador("Prueba2", Color.AZUL, TipoRaza.TERRAN, mapa);
		// Aseguro recursos
		jugador.agregarGasVespeno(500);
		jugador.agregarMinerales(600);
		crearEdificio();
	}
	
	@Test(expected = OrdenConstruccionViolado.class)
	public void testCrearFabricaLuegoDeDestruirBarracaFalla() {
		barraca.destruir();
		crearEdificio();
	}
	
	@Test
	public void testCrearFabricaDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 100;
		int costoMineral = 200;
		
		this.fabricaEnConst = crearEdificio();
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test(expected = TerrenoInadecuado.class)
	public void testCrearFabricaFueraDeTierraFalla() {
		crearFueraDeTierra();
	}
	
	@Test(expected = MineralInsuficiente.class)
	public void testCrearFabricaSinMineralesDebeFallar() {
		while (jugador.getMinerales() >= 200) jugador.agregarMinerales(-10);
		crearEdificio();
	}
	
	@Test(expected = GasVespenoInsuficiente.class)
	public void testCrearFabricaSinGasVespenoDebeFallar() {
		while (jugador.getGasVespeno() >= 100) jugador.agregarGasVespeno(-10);
		crearEdificio();
	}
	
	@Test
	public void testFabricaSubeVidaDuranteConstruccion() {
		int vidaRelativa = fabricaEnConst.getVida();
		for(int i = 0; i < 12; i++){
			fabricaEnConst.pasarTurno();
			if (fabricaEnConst.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = fabricaEnConst.getVida();
		}
		assertEquals(vidaRelativa, fabrica.getVidaMaxima());
	}
	
	@Test
	public void testFabricaMientrasConstruyeNoPuedeEntrenar() {
		assertFalse(fabricaEnConst.puedeEntrenarUnidades());
	}
	
	@Test
	public void testFabricaTerminadaPuedeEntrenar() {
		assertTrue(fabrica.puedeEntrenarUnidades());
	}
	
	@Test
	public void testFabricaPuedeEntrenarUnaUnidad() {
		assertEquals(1, fabrica.getUnidadesEntrenables().size());
	}
	
	@Test
	public void testFabricaPuedeEntrenarGolliat() {
		puedeEntrenarUnidad(Tipo.GOLLIAT);
	}
	
	@Test
	public void testFabricaMientrasEntrenaGolliatNoEstaDisponible() {
		mientrasEntrenaUnidadNoDisponible(Tipo.GOLLIAT, turnosGolliat);
	}
	
	@Test
	public void testFabricaEntrenaGolliat() {
		entrenarUnidadVerJugador(Tipo.GOLLIAT, turnosGolliat);
	}
	
	@Test
	public void testFabricaEntrenaGolliatDejaEnElMapa() {
		entrenarUnidadVerMapa(Tipo.GOLLIAT, turnosGolliat);
	}
	
	@Test
	public void testFabricaEntrenaGolliatConsumeRecursos() {
		entrenarUnidadConsumeRecursos(Tipo.GOLLIAT, turnosGolliat, 100, 50, 2);
	}
	
	@Test(expected = MineralInsuficiente.class)
	public void testFabricaEntrenaGolliatSinMineralesDebeFallar() {
		entrenarSinMinerales(Tipo.GOLLIAT);
	}
	
	@Test(expected = GasVespenoInsuficiente.class)
	public void testFabricaEntrenaGolliatSinGasVespenoDebeFallar() {
		entrenarSinGasVespeno(Tipo.GOLLIAT);
	}
	
	@Test(expected = SuministroInsuficiente.class)
	public void testFabricaEntrenaGolliatSinPoblacionDebeFallar() {
		entrenarSinCapacidadPoblacion(Tipo.GOLLIAT);
	}
	
	@Test
	public void testFabricaEntrenaVariasUnidadesSinColaDeEspera() {		
		for (int i = 0; i < 3; i++)
			entrenarUnidadVerJugador(Tipo.GOLLIAT, turnosGolliat);
	}
	
	@Test
	public void testFabricaEntrenaVariasUnidadesConColaDeEspera() {
		entrenarVariasUnidadesSostieneElOrden(
					Arrays.asList(fabrica.getUnidadesEntrenables().get(0),
							fabrica.getUnidadesEntrenables().get(0)), 
							turnosGolliat, turnosGolliat);
	}
}
