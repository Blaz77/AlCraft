package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.GasVespenoInsuficiente;
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

public class TestAcceso extends TestEdificioEntrenador {

	private Edificio acceso;
	private Edificio accesoEnConst;
	private final int turnosZealot = 4;
	private final int turnosDragon = 6;
	
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
		this.jugador.agregarMinerales(1000);
		this.jugador.agregarGasVespeno(1000);
		this.jugador.aumentarCapacidadPoblacion(10);
		this.acceso = crearEdificio();
		for(int i = 0; i < 8; i++) acceso.pasarTurno();//Construccion
		this.acceso = (Edificio) mapa.getOcupante(acceso.getPosicion());
		this.accesoEnConst = crearEdificio();
		entrenador = this.acceso;	
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
	public void testAccesoPuedeEntrenarDosUnidades() {
		assertEquals(2, acceso.getUnidadesEntrenables().size());
	}
	
	@Test
	public void testAccesoPuedeEntrenarZealot() {
		puedeEntrenarUnidad(Tipo.ZEALOT);
	}
	
	@Test
	public void testAccesoPuedeEntrenarDragon() {
		puedeEntrenarUnidad(Tipo.DRAGON);
	}
	
	@Test
	public void testAccesoMientrasEntrenaZealotNoEstaDisponible() {
		mientrasEntrenaUnidadNoDisponible(Tipo.ZEALOT, turnosZealot);
	}
	
	@Test
	public void testAccesoMientrasEntrenaDragonNoEstaDisponible() {
		mientrasEntrenaUnidadNoDisponible(Tipo.DRAGON, turnosDragon);
	}
	
	@Test
	public void testAccesoEntrenaZealot() {
		entrenarUnidadVerJugador(Tipo.ZEALOT, turnosZealot);
	}
	
	@Test
	public void testAccesoEntrenaDragon() {
		entrenarUnidadVerJugador(Tipo.DRAGON, turnosDragon);
	}
	
	@Test
	public void testAccesoEntrenaZealotDejaEnElMapa() {
		entrenarUnidadVerMapa(Tipo.ZEALOT, turnosZealot);
	}
	
	@Test
	public void testAccesoEntrenaDragonDejaEnElMapa() {
		entrenarUnidadVerMapa(Tipo.DRAGON, turnosDragon);
	}
	
	@Test
	public void testAccesoEntrenaZealotConsumeRecursos() {
		entrenarUnidadConsumeRecursos(Tipo.ZEALOT, turnosZealot, 100, 0, 2);
	}
	
	@Test
	public void testAccesoEntrenaDragonConsumeRecursos() {
		entrenarUnidadConsumeRecursos(Tipo.DRAGON, turnosDragon, 125, 50, 2);
	}
	
	@Test(expected = MineralInsuficiente.class)
	public void testAccesoEntrenaUnidadSinMineralesDebeFallar() {
		entrenarSinMinerales(Tipo.ZEALOT);
		entrenarSinMinerales(Tipo.DRAGON);
	}
	
	@Test(expected = GasVespenoInsuficiente.class)
	public void testAccesoEntrenaUnidadSinGasVespenoDebeFallar() {
		entrenarSinGasVespeno(Tipo.DRAGON);
	}
	
	@Test(expected = SuministroInsuficiente.class)
	public void testAccesoEntrenaUnidadSinPoblacionDebeFallar() {
		entrenarSinCapacidadPoblacion(Tipo.ZEALOT);
		entrenarSinCapacidadPoblacion(Tipo.DRAGON);
	}
	
	@Test
	public void testAccesoEntrenarVariasUnidadesSinColaDeEspera() {
		for (int i = 0; i < 2; i++){
			entrenarUnidadVerJugador(Tipo.ZEALOT, turnosZealot);
			entrenarUnidadVerJugador(Tipo.DRAGON, turnosDragon);
		}
	}
	
	@Test
	public void testAccesoEntrenarVariasUnidadesConColaDeEspera() {
		entrenarVariasUnidadesSostieneElOrden(acceso.getUnidadesEntrenables(),
				turnosZealot, turnosDragon);
	}

}
