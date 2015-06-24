package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.GasVespenoInsuficiente;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.OrdenConstruccionViolado;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.terreno.Terreno;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.edificios.Edificio;

public class TestPuertoEstelar extends TestEdificio {

	private MapaReal mapa;
	private Jugador jugador;
	private Edificio fabrica;
	private Edificio barraca;
	private Edificio puerto;
	private Edificio puertoEnConst;
	
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
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.TERRAN, mapa);
		
		// Aseguro recursos
		jugador.agregarGasVespeno(600);
		jugador.agregarMinerales(850);
		this.barraca = crearEdificioRequerido();
		for(int i = 0; i < 12; i++) barraca.pasarTurno();//Construccion
		this.barraca = (Edificio) mapa.getOcupante(barraca.getPosicion());
		this.fabrica = crearEdificioRequerido();
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Construccion
		this.fabrica = (Edificio) mapa.getOcupante(fabrica.getPosicion());
		this.puerto = crearEdificio();
		for(int i = 0; i < 10; i++) puerto.pasarTurno();//Construccion
		this.puerto = (Edificio) mapa.getOcupante(puerto.getPosicion());
		this.puertoEnConst = crearEdificio();
	}
	
	@Test
	public void testCrearPuertoEstelar() {
		assertEquals(puerto.getNombre(),"Puerto Estelar");
	}
	
	@Test(expected = OrdenConstruccionViolado.class)
	public void testCrearPuertoEstelarSinFabricaFalla() {
		jugador = new Jugador("Prueba2", Color.AZUL, TipoRaza.TERRAN, mapa);
		// Aseguro recursos
		jugador.agregarGasVespeno(500);
		jugador.agregarMinerales(600);
		Edificio barraca = crearEdificioRequerido();
		for(int i = 0; i < 8; i++) barraca.pasarTurno();//Construccion
		crearEdificio();
	}
	
	@Test(expected = OrdenConstruccionViolado.class)
	public void testCrearPuertoEstelarLuegoDeDestruirFabricaFalla() {
		fabrica.destruir();
		crearEdificio();
	}
	
	@Test
	public void testCrearPuertoEstelarDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 100;
		int costoMineral = 150;
		
		this.puertoEnConst = crearEdificio();
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test(expected = TerrenoInadecuado.class)
	public void testCrearPuertoEstelarFueraDeTierraFalla() {
		crearFueraDeTierra();
	}
	
	@Test(expected = MineralInsuficiente.class)
	public void testCrearPuertoEstelarSinMineralesDebeFallar() {
		while (jugador.getMinerales() >= 150) jugador.agregarMinerales(-10);
		crearEdificio();
	}
	
	@Test(expected = GasVespenoInsuficiente.class)
	public void testCrearPuertoEstelarSinGasVespenoDebeFallar() {
		while (jugador.getGasVespeno() >= 100) jugador.agregarGasVespeno(-10);
		crearEdificio();
	}
	
	@Test
	public void testPuertoEstelarSubeVidaDuranteConstruccion() {
		int vidaRelativa = puertoEnConst.getVida();
		for(int i = 0; i < 10; i++){
			puertoEnConst.pasarTurno();
			if (puertoEnConst.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = puertoEnConst.getVida();
		}
		assertEquals(vidaRelativa, puerto.getVidaMaxima());
	}
	
	@Test
	public void testPuertoEstelarMientrasConstruyeNoPuedeEntrenar() {
		assertFalse(puertoEnConst.puedeEntrenarUnidades());
	}
	
	@Test
	public void testPuertoEstelarTerminadoPuedeEntrenar() {
		assertTrue(puerto.puedeEntrenarUnidades());
	}
	
	@Test
	public void testPuertoEstelarEntrenaUnidad() {
		//Junto recursos y poblacion para entrenar
		jugador.agregarGasVespeno(1000);
		jugador.agregarMinerales(1000);
		jugador.aumentarCapacidadPoblacion(10);
		
		puerto.getUnidadesEntrenables().get(0).crear();
		
		for(int i = 0; i < 8; i++) puerto.pasarTurno(); //Entrenar Espectro
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Espectro");

		puerto.getUnidadesEntrenables().get(1).crear();
		
		for(int i = 0; i < 10; i++) puerto.pasarTurno(); //Entrenar NaveDeCiencia
		assertEquals(jugador.getUnidades().get(1).getNombre(), "Nave de ciencia");
		
		puerto.getUnidadesEntrenables().get(2).crear();
		
		for(int i = 0; i < 7; i++) puerto.pasarTurno(); //Entrenar NaveDeTransporte
		assertEquals(jugador.getUnidades().get(2).getNombre(), "Nave de transporte");
	}

}
