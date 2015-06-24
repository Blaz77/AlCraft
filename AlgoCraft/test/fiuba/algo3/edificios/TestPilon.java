package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.terreno.Terreno;

public class TestPilon extends TestEdificio {

	private Edificio pilon;
	
	
	@Override
	protected Edificio crearEdificio() {
		return crearEnTierra();
	}
	
	@Override
	protected Edificio crearEdificioEn(Terreno terreno, Tipo tipo) {
		return crearEn((j,p) -> jugador.getEdificador().crearIncrementadorPoblacion(j,p), terreno, tipo);
	}

	
	@Before
	public void setUp() {
		mapa = new MapaReal(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.PROTOSS, mapa);
	}
	
	@Test
	public void testCrearPilon() {
		this.pilon = crearEdificio();
		for(int i = 0; i < 5; i++) pilon.pasarTurno(); //Construccion
		this.pilon = (Edificio) mapa.getOcupante(pilon.getPosicion());
		
		assertEquals(pilon.getNombre(),"Pilon");
	}
	
	@Test
	public void testCrearPilonDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 0;
		int costoMineral = 100;
		
		this.pilon = crearEdificio();
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test(expected = TerrenoInadecuado.class)
	public void testCrearPilonFueraDeTierraFalla() {
		crearFueraDeTierra();
	}
	
	@Test(expected = MineralInsuficiente.class)
	public void testCrearPilonSinRecursosDebeFallar() {
		while (jugador.getMinerales() >= 100) jugador.agregarMinerales(-10);
		crearEdificio();
	}
	
	@Test
	public void testPilonSubeVidaDuranteConstruccion() {
		this.pilon = crearEdificio();
		
		int vidaRelativa = pilon.getVida();
		for(int i = 0; i < 5; i++){
			pilon.pasarTurno();
			if (pilon.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = pilon.getVida();
		}
		assertEquals(vidaRelativa, pilon.getVidaMaxima());
	}
	
	@Test
	public void testPilonSubeEscudoDuranteConstruccion() {
		this.pilon = crearEdificio();
		
		int escudoRelativo = pilon.getEscudo();
		for(int i = 0; i < 5; i++){
			pilon.pasarTurno();
			if (pilon.getEscudo() <= escudoRelativo) 
				fail("No aumenta el escudo en la construccion");
			escudoRelativo = pilon.getEscudo();
		}
		assertEquals(escudoRelativo, pilon.getEscudoMaximo());
	}
	
	@Test
	public void testPilonMientrasConstruyeNoAumentaPoblacion() {
		int poblacionRelativa = jugador.getCapacidadPoblacion();
		this.pilon = crearEdificio();
		
		assertEquals(poblacionRelativa, jugador.getCapacidadPoblacion());
	}

	@Test
	public void testPilonDespuesDeConstruirAumentaPoblacion() {
		int poblacionRelativa = jugador.getCapacidadPoblacion();
		this.pilon = crearEdificio();
		for(int i = 0; i < 5; i++) pilon.pasarTurno(); // Construyendo
		this.pilon = (Edificio) mapa.getOcupante(pilon.getPosicion());
		
		assertEquals(poblacionRelativa + 5, jugador.getCapacidadPoblacion());
	}
	
	@Test
	public void testPilonAlDestruirDisminuyePoblacion() {
		int poblacionRelativa = jugador.getCapacidadPoblacion();
		this.pilon = crearEdificio();
		for(int i = 0; i < 5; i++) pilon.pasarTurno(); // Construyendo
		this.pilon = (Edificio) mapa.getOcupante(pilon.getPosicion());
		
		this.pilon.destruir();
		assertEquals(poblacionRelativa, jugador.getCapacidadPoblacion());
	}
	
}
