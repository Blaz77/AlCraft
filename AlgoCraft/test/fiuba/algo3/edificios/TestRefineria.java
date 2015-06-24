package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.RecursoAusente;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.TipoOcupante;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.terreno.Terreno;

public class TestRefineria extends TestEdificio {

	private Edificio refineria;
	
	@Override
	protected Edificio crearEdificio() {
		return crearEnVolcan();
	}

	@Override
	protected Edificio crearEdificioEn(Terreno terreno, Tipo tipo) {
		return crearEn((j,p) -> jugador.getEdificador().crearRecolectorGasVespeno(j,p), terreno, tipo);
	}
	
	@Before
	public void setUp() throws Exception {
		mapa = new MapaReal(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.TERRAN, mapa);
	}
	
	@Test
	public void testCrearRefineriaEnVolcan() {
		refineria = crearEdificio();
		for(int i = 0; i < 6; i++) refineria.pasarTurno(); // Construyendo
		refineria = (Edificio) mapa.getOcupante(refineria.getPosicion());
		
		assertNotNull(refineria);
		assertEquals(refineria.getNombre(),"Refineria");
	}
	
	@Test
	public void testCrearRefineriaDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 0;
		int costoMineral = 100;
		
		this.refineria = crearEdificio();
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test(expected = MineralInsuficiente.class)
	public void testCrearRefineriaSinMineralesDebeFallar() {
		while (jugador.getMinerales() >= 100) jugador.agregarMinerales(-10);
		crearEdificio();
	}
	
	@Test(expected = RecursoAusente.class)
	public void testCrearRefineriaFueraDeVolcanFalla() {
		crearEnTierra();
	}
	
	@Test
	public void testRefineriaSubeVidaDuranteConstruccion() {
		refineria = crearEdificio();
		
		int vidaRelativa = refineria.getVida();
		for(int i = 0; i < 6; i++){
			refineria.pasarTurno();
			if (refineria.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = refineria.getVida();
		}
		assertEquals(vidaRelativa, refineria.getVidaMaxima());
	}
	
	@Test
	public void testRefineriaMientrasConstruyeNoRecolecta() {
		this.refineria = crearEdificio();
		int mineralRelativo = jugador.getMinerales();
		
		for(int i = 0; i < 6; i++) {
			refineria.pasarTurno();
			if (jugador.getMinerales() != mineralRelativo)
				fail();
		}
		assertTrue(true);
	}
	
	@Test
	public void testRefineriaRecolectaGasVespeno() {
		refineria = crearEdificio();
		for(int i = 0; i < 6; i++) refineria.pasarTurno(); // Construyendo
		refineria = (Edificio) mapa.getOcupante(refineria.getPosicion());
		
		int gasRelativo = jugador.getGasVespeno();

		for(int i = 0; i < 10; i++){
			gasRelativo += 10;
			refineria.pasarTurno();
			if (jugador.getGasVespeno() != gasRelativo)
				fail("La refineria no esta recolectando gas (de a 10)");
		}
	}
	
	@Test
	public void testRefineriaAlDestruirseDejaGasVespenoEnMapa() {
		this.refineria = crearEdificio();
		for(int i = 0; i < 6; i++) refineria.pasarTurno(); // Construccion
		this.refineria = (Edificio) mapa.getOcupante(refineria.getPosicion());
		
		this.refineria.destruir();
		assertEquals(Tipo.VESPENO, mapa.getOcupante(refineria.getPosicion()).getTipo());
	}

}
