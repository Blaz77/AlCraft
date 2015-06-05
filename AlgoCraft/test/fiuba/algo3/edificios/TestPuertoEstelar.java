package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import factories.EdificiosTerranFactory;
import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.unidades.Espectro;
import fiuba.algo3.unidades.Golliat;
import fiuba.algo3.unidades.Marine;
import fiuba.algo3.unidades.NaveDeCiencia;
import fiuba.algo3.unidades.NaveDeTransporte;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;

public class TestPuertoEstelar {

	private Mapa mapa;
	private Jugador jugador;
	private EdificiosTerranFactory terranFactory;
	private Edificio puerto;
	
	@Before
	public void setUp() throws Exception {
		//mapa = new Mapa(6);
		this.jugador = new Jugador(TipoRaza.TERRAN, Color.AZUL, mapa);
		this.terranFactory = new EdificiosTerranFactory();
		this.puerto = terranFactory.crearEntrenadorUnidadesAvanzadas(jugador, 20, 40);
	}

	//TESTS SIN REQUISITOS POR AHORA!!!
	
	@Test
	public void testCrearPuertoEstelar() {
		assertEquals(puerto.getNombre(),"Puerto Estelar");
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
		
		puerto.getUnidadesEntrenables().get(0).crear(jugador);
		
		for(int i = 0; i < 8; i++) puerto.pasarTurno(); //Entrenar Espectro
		assertEquals(jugador.getUnidades().get(0).getClass(), Espectro.class);

		puerto.getUnidadesEntrenables().get(1).crear(jugador);
		
		for(int i = 0; i < 10; i++) puerto.pasarTurno(); //Entrenar NaveDeCiencia
		assertEquals(jugador.getUnidades().get(1).getClass(), NaveDeCiencia.class);
		
		puerto.getUnidadesEntrenables().get(2).crear(jugador);
		
		for(int i = 0; i < 7; i++) puerto.pasarTurno(); //Entrenar NaveDeTransporte
		assertEquals(jugador.getUnidades().get(2).getClass(), NaveDeTransporte.class);
	}

}