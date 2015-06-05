package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import factories.EdificiosTerranFactory;
import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.unidades.Marine;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;

public class TestBarraca {

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosTerranFactory terranFactory; //EdificiosAbstractFactory
	private EdificioEntrenadorUnidades barraca;
	
	@Before
	public void setUp() throws Exception {
		//mapa = new Mapa(6);
		this.jugador = new Jugador(TipoRaza.TERRAN, Color.AZUL, mapa);
		this.terranFactory = new EdificiosTerranFactory();
		this.barraca = terranFactory.crearEntrenadorUnidadesBasicas(jugador, 20, 40);
	}

	//TESTS SIN REQUISITOS POR AHORA!!!
	
	@Test
	public void testCrearBarraca() {
		assertEquals(barraca.getNombre(),"Barraca");
	}
	
	@Test
	public void testBarracaSubeVidaDuranteConstruccion() {
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
			barraca.getUnidadesEntrenables().get(0).crear(jugador);
			for(int i = 0; i < 3; i++) barraca.pasarTurno(); //Entrenar Marine
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

}
