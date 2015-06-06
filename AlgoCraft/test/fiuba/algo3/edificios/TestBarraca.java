package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import factories.EdificiosTerranFactory;
import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.SuministroInsuficiente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.terreno.TipoTerreno;
import fiuba.algo3.unidades.Marine;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.mapa.recurso.TipoRecurso;

public class TestBarraca {

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosTerranFactory terranFactory; //EdificiosAbstractFactory
	private EdificioEntrenadorUnidades barraca;
	
	private EdificioEntrenadorUnidades crearEnTierra(Jugador jugador, Mapa mapa) {
		for (int y = 0; y < mapa.alto(); y++) {
			for (int x = 0; x < mapa.ancho(); x++) {
				Posicion posEnTierra = new Posicion(x, y);
				if (mapa.getTerreno(posEnTierra).getTipo() == TipoTerreno.TIERRA) {
					return terranFactory.crearEntrenadorUnidadesBasicas(jugador, posEnTierra);
				}
			}
		}
		return null;
	}
	
	@Before
	public void setUp() {
		mapa = new Mapa(6);
		this.jugador = new Jugador(TipoRaza.TERRAN, Color.AZUL, mapa);
		this.terranFactory = new EdificiosTerranFactory();
		this.barraca = crearEnTierra(jugador, mapa);
	}

	//TESTS SIN REQUISITOS POR AHORA!!!
	
	@Test
	public void testCrearBarraca() {
		assertEquals(barraca.getNombre(),"Barraca");
	}
	
	@Test
	public void testCrearBarracaFueraDeTierraFalla() {
		for (int y = 0; y < mapa.alto(); y++) {
			for (int x = 0; x < mapa.ancho(); x++) {
				Posicion posFueraDeTierra = new Posicion(x, y);
				if (mapa.getTerreno(posFueraDeTierra).getTipo() != TipoTerreno.TIERRA) {
					try {
						this.barraca = terranFactory.crearEntrenadorUnidadesBasicas(jugador, posFueraDeTierra);
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
	public void testCrearBarracaSinRecursosDebeFallar() {
		while (jugador.getMinerales() >= 150) {
			jugador.agregarMinerales(-10);
		}
		try {
			this.barraca = terranFactory.crearEntrenadorUnidadesBasicas(jugador, new Posicion(2,4));
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}
	}
	
	@Test
	public void testBarracaSubeVidaDuranteConstruccion() {
		if (barraca.getVida() == barraca.getVidaMaxima())
			fail("La construccion inicio con la vida maxima");
		
		int vidaRelativa = barraca.getVida();		
		for(int i = 0; i < 12; i++){
			barraca.pasarTurno();
			if (barraca.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = barraca.getVida();
		}
		assertEquals(vidaRelativa, barraca.getVidaMaxima());
	}
	
	
	@Test
	public void testBarracaEntrenaUnidad() {
		for(int i = 0; i < 12; i++) barraca.pasarTurno();//Construccion
		
		barraca.getUnidadesEntrenables().get(0).crear(jugador);
		
		for(int i = 0; i < 3; i++) barraca.pasarTurno();//Entrenar Marine
		
		assertEquals(jugador.getUnidades().get(0).getClass(), Marine.class);

	}
	
	@Test
	public void testBarracaEntrenaUnidadConsumeRecursos() {
		for(int i = 0; i < 12; i++) barraca.pasarTurno();//Construccion
		int mineralRelativo = jugador.getMinerales();
		
		barraca.getUnidadesEntrenables().get(0).crear(jugador);
		for(int i = 0; i < 3; i++) barraca.pasarTurno();//Entrenar Marine
		
		assertEquals(jugador.getMinerales(), mineralRelativo - 50);

	}
	
	@Test
	public void testBarracaEntrenaUnidadSinRecursosDebeFallar() {
		for(int i = 0; i < 12; i++) barraca.pasarTurno();//Construccion
		
		while (jugador.getMinerales() >= 50) {
			jugador.agregarMinerales(-10);
		}
		try {
			barraca.getUnidadesEntrenables().get(0).crear(jugador);
			fail();
		}
		catch (MineralInsuficiente e) {
			assertTrue(true);
			return;
		}

	}
	
	@Test
	public void testBarracaEntrenarVariasUnidadesSinColaDeEspera() {
		for(int i = 0; i < 12; i++) barraca.pasarTurno();//Construccion
		
		barraca.getUnidadesEntrenables().get(0).crear(jugador);
		for(int i = 0; i < 3; i++) barraca.pasarTurno();//Entrenar Marine
		assertEquals(jugador.getUnidades().get(0).getClass(), Marine.class);
		
		barraca.getUnidadesEntrenables().get(0).crear(jugador);
		for(int i = 0; i < 3; i++) barraca.pasarTurno();//Entrenar Marine
		assertEquals(jugador.getUnidades().get(1).getClass(), Marine.class);
	}
	
	@Test
	public void testBarracaEntrenarVariasUnidadesConColaDeEspera() {
		for(int i = 0; i < 12; i++) barraca.pasarTurno();//Construccion
		
		barraca.getUnidadesEntrenables().get(0).crear(jugador);
		barraca.getUnidadesEntrenables().get(0).crear(jugador);
		for(int i = 0; i < 6; i++) barraca.pasarTurno();//Entrenar 2 Marines
		assertEquals(jugador.getUnidades().get(0).getClass(), Marine.class);
		assertEquals(jugador.getUnidades().get(1).getClass(), Marine.class);
	}

	@Test
	public void testBarracaEntrenaUnidadSinPoblacionDebeFallar() {
		for(int i = 0; i < 12; i++) barraca.pasarTurno();//Construccion
		
		while (jugador.getCapacidadPoblacion() > 1) {
			jugador.aumentarCapacidadPoblacion(-1);
		}
		barraca.getUnidadesEntrenables().get(0).crear(jugador);
		for(int i = 0; i < 3; i++) barraca.pasarTurno();//Entrenar Marine
		try {
			barraca.getUnidadesEntrenables().get(0).crear(jugador);
			fail();
		}
		catch (SuministroInsuficiente e) {
			assertTrue(true);
			return;
		}

	}
}
