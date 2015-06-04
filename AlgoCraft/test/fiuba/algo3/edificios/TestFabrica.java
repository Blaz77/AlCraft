package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.tp_final.*;
import fiuba.algo3.unidades.Golliat;
import fiuba.algo3.unidades.Marine;
import fiuba.algo3.mapa.*;

public class TestFabrica {

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosTerranFactory terranFactory;
	private Edificio fabrica;
	
	@Before
	public void setUp() throws Exception {
		//mapa = new Mapa(6);
		this.jugador = new Jugador(TipoRaza.TERRAN, Color.AZUL, mapa);
		this.terranFactory = new EdificiosTerranFactory();
		this.fabrica = terranFactory.crearEntrenadorUnidadesIntermedias(jugador, 20, 40);
	}

	//TESTS SIN REQUISITOS POR AHORA!!!
	
	@Test
	public void testCrearFabrica() {
		assertEquals(fabrica.getNombre(),"Fabrica");
	}
	
	@Test
	public void testFabricaSubeVidaDuranteConstruccion() {
		int vidaRelativa = fabrica.getVida();
		for(int i = 0; i < 12; i++){
			fabrica.pasarTurno();
			if (fabrica.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = fabrica.getVida();
		}
		assertEquals(vidaRelativa, fabrica.getVidaMaxima());
	}
	
	
	@Test
	public void testFabricaEntrenaUnidad() {
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Construccion
		
		fabrica.getUnidadesEntrenables().get(0).crear(jugador);
		
		for(int i = 0; i < 6; i++) fabrica.pasarTurno();//Entrenar Golliat
		
		assertEquals(jugador.getUnidades().get(0).getClass(), Golliat.class);

	}

}
