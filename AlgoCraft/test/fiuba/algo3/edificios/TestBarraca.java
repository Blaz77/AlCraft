package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.SuministroInsuficiente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.terreno.Terreno;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.edificios.Edificio;

public class TestBarraca extends TestEdificioEntrenador {

	private Edificio barraca;
	private Edificio barracaEnConst;
	private final int turnosMarine = 3;
	
	@Override
	protected Edificio crearEdificio() {
		return crearEnTierra();
	}

	@Override
	protected Edificio crearEdificioEn(Terreno terreno, Tipo tipo) {
		return crearEn((j,p) -> jugador.getEdificador().crearEntrenadorUnidadesBasicas(j,p), terreno, tipo);
	}
	
	@Before
	public void setUp() {
		mapa = new MapaReal(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.TERRAN, mapa);
		this.jugador.agregarMinerales(300);
		this.jugador.agregarGasVespeno(300);
		this.barraca = crearEdificio();
		for(int i = 0; i < 12; i++) barraca.pasarTurno();//Construccion
		this.barraca = (Edificio) mapa.getOcupante(barraca.getPosicion());
		this.barracaEnConst = crearEdificio();
		entrenador = this.barraca;
	}

	//TESTS SIN REQUISITOS POR AHORA!!!
	
	@Test
	public void testCrearBarraca() {
		assertEquals(barraca.getNombre(),"Barraca");
	}
	
	@Test
	public void testCrearBarracaDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 0;
		int costoMineral = 150;
		
		this.barraca = crearEdificio();
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test(expected = TerrenoInadecuado.class)
	public void testCrearBarracaFueraDeTierraFalla() {
		crearFueraDeTierra();
	}
	
	@Test(expected = MineralInsuficiente.class)
	public void testCrearBarracaSinRecursosDebeFallar() {
		while (jugador.getMinerales() >= 150) jugador.agregarMinerales(-10);
		crearEdificio();
	}
	
	@Test
	public void testBarracaSubeVidaDuranteConstruccion() {
		if (barracaEnConst.getVida() == barracaEnConst.getVidaMaxima())
			fail("La construccion inicio con la vida maxima");
		
		int vidaRelativa = barracaEnConst.getVida();		
		for(int i = 0; i < 12; i++){
			barracaEnConst.pasarTurno();
			if (barracaEnConst.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = barracaEnConst.getVida();
		}
		assertEquals(vidaRelativa, barracaEnConst.getVidaMaxima());
	}
	
	@Test
	public void testBarracaMientrasConstruyeNoPuedeEntrenar() {
		assertFalse(barracaEnConst.puedeEntrenarUnidades());
	}
	
	@Test
	public void testBarracaTerminadaPuedeEntrenar() {
		assertTrue(barraca.puedeEntrenarUnidades());
	}
	
	@Test
	public void testBarracaPuedeEntrenarUnaUnidad() {
		assertEquals(1, barraca.getUnidadesEntrenables().size());
	}
	
	@Test
	public void testBarracaPuedeEntrenarMarine() {
		puedeEntrenarUnidad(Tipo.MARINE);
	}
	
	@Test
	public void testBarracaMientrasEntrenaUnidadNoEstaDisponible() {
		mientrasEntrenaUnidadNoDisponible(Tipo.MARINE, turnosMarine);
	}
	
	@Test
	public void testBarracaEntrenaUnidad() {
		entrenarUnidadVerJugador(Tipo.MARINE, turnosMarine);
	}
	
	@Test
	public void testBarracaEntrenaUnidadDejaEnElMapa() {
		entrenarUnidadVerMapa(Tipo.MARINE, turnosMarine);
	}
	
	@Test
	public void testBarracaEntrenaMarineConsumeRecursos() {
		entrenarUnidadConsumeRecursos(Tipo.MARINE, turnosMarine, 50, 0, 1);
	}
	
	@Test(expected = MineralInsuficiente.class)
	public void testBarracaEntrenaUnidadSinMineralesDebeFallar() {
		entrenarSinMinerales(Tipo.MARINE);
	}
	
	@Test(expected = SuministroInsuficiente.class)
	public void testBarracaEntrenaUnidadSinPoblacionDebeFallar() {
		entrenarSinCapacidadPoblacion(Tipo.MARINE);
	}
	
	@Test
	public void testBarracaEntrenarVariasUnidadesSinColaDeEspera() {		
		for (int i = 0; i < 3; i++)
			entrenarUnidadVerJugador(Tipo.MARINE, turnosMarine);
	}
	
	@Test
	public void testBarracaEntrenarVariasUnidadesConColaDeEspera() {
		entrenarVariasUnidadesSostieneElOrden(
					Arrays.asList(barraca.getUnidadesEntrenables().get(0),
							barraca.getUnidadesEntrenables().get(0)), turnosMarine, turnosMarine);
	}

}
