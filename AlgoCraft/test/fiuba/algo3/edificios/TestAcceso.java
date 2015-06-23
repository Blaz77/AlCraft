package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.SuministroInsuficiente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.terreno.Terreno;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.edificios.Edificio;

public class TestAcceso extends TestEdificio {

	private Edificio acceso;
	private Edificio accesoEnConst;
	
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
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.PROTOSS, mapa);
		this.jugador.agregarMinerales(300);
		this.jugador.agregarGasVespeno(300);
		this.acceso = crearEdificio();
		for(int i = 0; i < 8; i++) acceso.pasarTurno();//Construccion
		this.acceso = (Edificio) mapa.getOcupante(acceso.getPosicion());
		this.accesoEnConst = crearEdificio();
	}

	@Test
	public void testCrearAcceso() {
		assertEquals(acceso.getNombre(),"Acceso");
	}
	
	@Test
	public void testCrearAccesoDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 0;
		int costoMineral = 150;
		
		this.acceso = crearEdificio();
		
		assertEquals(mineralRelativo - costoMineral, jugador.getMinerales());
		assertEquals(gasRelativo - costoGas, jugador.getGasVespeno());
	}
	
	@Test(expected = TerrenoInadecuado.class)
	public void testCrearAccesoFueraDeTierraFalla() {
		crearFueraDeTierra();
	}
	
	@Test(expected = MineralInsuficiente.class)
	public void testCrearAccesoSinRecursosDebeFallar() {
		while (jugador.getMinerales() >= 150) jugador.agregarMinerales(-10);
		crearEdificio();
	}
	
	@Test
	public void testAccesoSubeVidaDuranteConstruccion() {
		if (accesoEnConst.getVida() == accesoEnConst.getVidaMaxima())
			fail("La construccion inicio con la vida maxima");
		
		int vidaRelativa = accesoEnConst.getVida();		
		for(int i = 0; i < 8; i++){
			accesoEnConst.pasarTurno();
			if (accesoEnConst.getVida() <= vidaRelativa) 
				fail("No aumenta la vida en la Construccion");
			vidaRelativa = accesoEnConst.getVida();
		}
		assertEquals(vidaRelativa, accesoEnConst.getVidaMaxima());
	}
	
	@Test
	public void testAccesoSubeEscudoDuranteConstruccion() {
		int escudoRelativo = accesoEnConst.getEscudo();
		for(int i = 0; i < 8; i++){
			accesoEnConst.pasarTurno();
			if (accesoEnConst.getEscudo() <= escudoRelativo) 
				fail("No aumenta el escudo en la construccion");
			escudoRelativo = accesoEnConst.getEscudo();
		}
		assertEquals(escudoRelativo, accesoEnConst.getEscudoMaximo());
	}
	
	@Test
	public void testAccesoMientrasConstruyeNoPuedeEntrenar() {
		assertFalse(accesoEnConst.puedeEntrenarUnidades());
	}
	
	@Test
	public void testAccesoTerminadoPuedeEntrenar() {
		assertTrue(acceso.puedeEntrenarUnidades());
	}
	
	@Test
	public void testAccesoEntrenaZealot() {
		acceso.getUnidadesEntrenables().get(0).crear();
		
		for(int i = 0; i < 4; i++) acceso.pasarTurno();//Entrenar Zealot
		
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Zealot");

	}
	
	@Test
	public void testAccesoEntrenaDragon() {
		acceso.getUnidadesEntrenables().get(1).crear();
		
		for(int i = 0; i < 6; i++) acceso.pasarTurno();//Entrenar Dragon
		
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Dragon");

	}
	
	@Test
	public void testAccesoEntrenaUnidadConsumeRecursos() {
		int mineralRelativo = jugador.getMinerales();
		
		acceso.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 4; i++) acceso.pasarTurno();//Entrenar Zealot
		
		assertEquals(jugador.getMinerales(), mineralRelativo - 100);

	}
	
	@Test(expected = MineralInsuficiente.class)
	public void testAccesoEntrenaUnidadSinRecursosDebeFallar() {
		while (jugador.getMinerales() >= 100) jugador.agregarMinerales(-10);
		acceso.getUnidadesEntrenables().get(0).crear();
	}
	
	@Test
	public void testAccesoEntrenarVariasUnidadesSinColaDeEspera() {		
		acceso.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 4; i++) acceso.pasarTurno();//Entrenar Zealot
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Zealot");
		
		acceso.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 4; i++) acceso.pasarTurno();//Entrenar Zealot
		assertEquals(jugador.getUnidades().get(1).getNombre(), "Zealot");
	}
	
	@Test
	public void testAccesoEntrenarVariasUnidadesConColaDeEspera() {
		acceso.getUnidadesEntrenables().get(0).crear();
		acceso.getUnidadesEntrenables().get(0).crear();
		for(int i = 0; i < 8; i++) acceso.pasarTurno();//Entrenar 2 Zealots
		assertEquals(jugador.getUnidades().get(0).getNombre(), "Zealot");
		assertEquals(jugador.getUnidades().get(1).getNombre(), "Zealot");
	}

	@Test(expected = SuministroInsuficiente.class)
	public void testAccesoEntrenaUnidadSinPoblacionDebeFallar() {
		while (jugador.getCapacidadPoblacion() > 1) jugador.aumentarCapacidadPoblacion(-1);
		acceso.getUnidadesEntrenables().get(0).crear();
	}

}
