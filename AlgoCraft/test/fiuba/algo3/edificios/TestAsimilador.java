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

public class TestAsimilador extends TestEdificio {

	private Edificio asimilador;
	
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
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.PROTOSS, mapa);
	}
	
	@Test
	public void testCrearAsimiladorEnVolcan() {
		asimilador = crearEdificio();
		for(int i = 0; i < 6; i++) asimilador.pasarTurno(); // Construyendo
		this.asimilador = (Edificio) mapa.getOcupante(asimilador.getPosicion());
		
		assertNotNull(asimilador);
		assertEquals(asimilador.getNombre(),"Asimilador");
	}
	
	@Test
	public void testCrearAsimiladorDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 0;
		int costoMineral = 100;
		
		this.asimilador = crearEdificio();
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test(expected = MineralInsuficiente.class)
	public void testCrearAsimiladorSinMineralesDebeFallar() {
		while (jugador.getMinerales() >= 100) jugador.agregarMinerales(-10);
		crearEdificio();
	}
	
	@Test(expected = RecursoAusente.class)
	public void testCrearAsimiladorFueraDeVolcanFalla() {
		crearEnTierra();
	}
	
	@Test
	public void testAsimiladorSubeVidaDuranteConstruccion() {
		asimilador = crearEdificio();
		
		int vidaRelativa = asimilador.getVida();
		for(int i = 0; i < 6; i++){
			asimilador.pasarTurno();
			if (asimilador.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = asimilador.getVida();
		}
		assertEquals(vidaRelativa, asimilador.getVidaMaxima());
	}
	
	@Test
	public void testAsimiladorSubeEscudoDuranteConstruccion() {
		asimilador = crearEdificio();
		
		int escudoRelativo = asimilador.getEscudo();
		for(int i = 0; i < 6; i++){
			asimilador.pasarTurno();
			if (asimilador.getEscudo() <= escudoRelativo) 
				fail("No aumenta el escudo en la construccion");
			escudoRelativo = asimilador.getEscudo();
		}
		assertEquals(escudoRelativo, asimilador.getEscudoMaximo());
	}
	
	@Test
	public void testAsimiladorMientrasConstruyeNoRecolecta() {
		this.asimilador = crearEdificio();
		int mineralRelativo = jugador.getMinerales();
		
		for(int i = 0; i < 6; i++) {
			asimilador.pasarTurno();
			if (jugador.getMinerales() != mineralRelativo)
				fail();
		}
		assertTrue(true);
	}
	
	@Test
	public void testAsimiladorRecolectaGasVespeno() {
		asimilador = crearEdificio();
		for(int i = 0; i < 6; i++) asimilador.pasarTurno(); // Construyendo
		this.asimilador = (Edificio) mapa.getOcupante(asimilador.getPosicion());
		
		int gasRelativo = jugador.getGasVespeno();

		for(int i = 0; i < 10; i++){
			gasRelativo += 10;
			asimilador.pasarTurno();
			if (jugador.getGasVespeno() != gasRelativo)
				fail("La refineria no esta recolectando gas (de a 10)");
		}
	}
	
	@Test
	public void testAsimiladorAlDestruirseDejaGasVespenoEnMapa() {
		this.asimilador = crearEdificio();
		for(int i = 0; i < 6; i++) asimilador.pasarTurno(); // Construccion
		this.asimilador = (Edificio) mapa.getOcupante(asimilador.getPosicion());
		
		this.asimilador.destruir();
		assertEquals(Tipo.VESPENO, mapa.getOcupante(asimilador.getPosicion()).getTipo());
	}
	
}
