package fiuba.algo3.edificios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.GasVespenoInsuficiente;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.OrdenConstruccionViolado;
import fiuba.algo3.excepciones.SuministroInsuficiente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.terreno.Terreno;
import fiuba.algo3.juego.*;
import fiuba.algo3.mapa.*;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.edificios.Edificio;

public class TestPuertoEstelarProtoss extends TestEdificioEntrenador {

	private Edificio acceso;
	private Edificio puerto;
	private Edificio puertoEnConst;
	private final int turnosScout = 9;
	private final int turnosNave = 8;
	
	@Override
	protected Edificio crearEdificio() {
		return crearEnTierra();
	}

	@Override
	protected Edificio crearEdificioEn(Terreno terreno, Tipo tipo) {
		return crearEn((j,p) -> jugador.getEdificador().crearEntrenadorUnidadesIntermedias(j,p), terreno, tipo);
	}
	
	@Override
	protected Edificio crearEdificioRequerido() {
		return crearEn((j,p) -> jugador.getEdificador().crearEntrenadorUnidadesBasicas(j,p),
				Terreno.TIERRA, Tipo.CELDA_VACIA);
	}
	
	@Before
	public void setUp() throws Exception {
		mapa = new MapaReal(6);
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.PROTOSS, mapa);
		
		// Aseguro recursos
		jugador.agregarGasVespeno(1500);
		jugador.agregarMinerales(1500);
		jugador.aumentarCapacidadPoblacion(30);
		this.acceso = crearEdificioRequerido();
		for(int i = 0; i < 8; i++) acceso.pasarTurno();//Construccion
		this.acceso = (Edificio) mapa.getOcupante(acceso.getPosicion());
		this.puerto = crearEdificio();
		for(int i = 0; i < 10; i++) puerto.pasarTurno();//Construccion
		this.puerto = (Edificio) mapa.getOcupante(puerto.getPosicion());
		this.puertoEnConst = crearEdificio();
		entrenador = this.puerto;
	}
	
	@Test
	public void testCrearPuertoEstelar() {
		assertEquals(puerto.getNombre(),"Puerto Estelar");
	}
	
	@Test(expected = OrdenConstruccionViolado.class)
	public void testCrearPuertoEstelarSinAccesoFalla() {
		jugador = new Jugador("Prueba2", Color.AZUL, TipoRaza.PROTOSS, mapa);
		// Aseguro recursos
		jugador.agregarGasVespeno(500);
		jugador.agregarMinerales(600);
		crearEdificio();
	}
	
	@Test(expected = OrdenConstruccionViolado.class)
	public void testCrearPuertoEstelarLuegoDeDestruirAccesoFalla() {
		acceso.destruir();
		crearEdificio();
	}
	
	@Test
	public void testCrearPuertoEstelarDisminuyeRecursosJugador() {
		int mineralRelativo = jugador.getMinerales();
		int gasRelativo = jugador.getGasVespeno();
		int costoGas = 150;
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
		while (jugador.getGasVespeno() >= 150) jugador.agregarGasVespeno(-10);
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
	public void testPuertoEstelarSubeEscudoDuranteConstruccion() {
		int escudoRelativo = puertoEnConst.getEscudo();
		for(int i = 0; i < 10; i++){
			puertoEnConst.pasarTurno();
			if (puertoEnConst.getEscudo() <= escudoRelativo) 
				fail("No aumenta el escudo en la construccion");
			escudoRelativo = puertoEnConst.getEscudo();
		}
		assertEquals(escudoRelativo, puertoEnConst.getEscudoMaximo());
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
	public void testPuertoEstelarPuedeEntrenarDosUnidades() {
		assertEquals(2, puerto.getUnidadesEntrenables().size());
	}
	
	@Test
	public void testPuertoEstelarPuedeEntrenarScout() {
		puedeEntrenarUnidad(Tipo.SCOUT);
	}
	
	@Test
	public void testPuertoEstelarPuedeEntrenarNaveDeTransporte() {
		puedeEntrenarUnidad(Tipo.NAVE_DE_TRANSPORTE_PROTOSS);
	}
	
	@Test
	public void testPuertoEstelarMientrasEntrenaScoutNoEstaDisponible() {
		mientrasEntrenaUnidadNoDisponible(Tipo.SCOUT, turnosScout);
	}
	
	@Test
	public void testPuertoEstelarMientrasEntrenaNaveTransporteNoEstaDisponible() {
		mientrasEntrenaUnidadNoDisponible(Tipo.NAVE_DE_TRANSPORTE_PROTOSS, turnosNave);
	}
	
	@Test
	public void testPuertoEstelarEntrenaScout() {
		entrenarUnidadVerJugador(Tipo.SCOUT, turnosScout);
	}
	
	@Test
	public void testPuertoEstelarEntrenaNaveTransporte() {
		entrenarUnidadVerJugador(Tipo.NAVE_DE_TRANSPORTE_PROTOSS, turnosNave);
	}
	
	@Test
	public void testPuertoEstelarEntrenaScoutDejaEnElMapa() {
		entrenarUnidadVerMapa(Tipo.SCOUT, turnosScout);
	}
	
	@Test
	public void testPuertoEstelarEntrenaNaveTransporteDejaEnElMapa() {
		entrenarUnidadVerMapa(Tipo.NAVE_DE_TRANSPORTE_PROTOSS, turnosNave);
	}
	
	@Test
	public void testPuertoEstelarEntrenaScoutConsumeRecursos() {
		entrenarUnidadConsumeRecursos(Tipo.SCOUT, turnosScout, 300, 150, 3);
	}
	
	@Test
	public void testPuertoEstelarEntrenaNaveTransporteConsumeRecursos() {
		entrenarUnidadConsumeRecursos(Tipo.NAVE_DE_TRANSPORTE_PROTOSS, turnosNave
										, 200, 0, 2);
	}
	
	@Test(expected = MineralInsuficiente.class)
	public void testPuertoEstelarEntrenaUnidadSinMineralesDebeFallar() {
		entrenarSinMinerales(Tipo.SCOUT);
		entrenarSinMinerales(Tipo.NAVE_DE_TRANSPORTE_PROTOSS);
	}
	
	@Test(expected = GasVespenoInsuficiente.class)
	public void testPuertoEstelarEntrenaUnidadSinGasVespenoDebeFallar() {
		entrenarSinGasVespeno(Tipo.SCOUT);
	}
	
	@Test(expected = SuministroInsuficiente.class)
	public void testPuertoEstelarEntrenaUnidadSinPoblacionDebeFallar() {
		entrenarSinCapacidadPoblacion(Tipo.SCOUT);
		entrenarSinCapacidadPoblacion(Tipo.NAVE_DE_TRANSPORTE_PROTOSS);
	}
	
	@Test
	public void testPuertoEstelarEntrenaVariasUnidadesSinColaDeEspera() {
		for (int i = 0; i < 2; i++){
			entrenarUnidadVerJugador(Tipo.SCOUT, turnosScout);
			entrenarUnidadVerJugador(Tipo.NAVE_DE_TRANSPORTE_PROTOSS, turnosNave);
		}
	}
	
	@Test
	public void testPuertoEstelarEntrenaVariasUnidadesConColaDeEspera() {
		entrenarVariasUnidadesSostieneElOrden(puerto.getUnidadesEntrenables(),
				turnosScout, turnosNave);
	}
}
