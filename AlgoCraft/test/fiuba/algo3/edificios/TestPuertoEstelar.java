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

public class TestPuertoEstelar extends TestEdificioEntrenador {

	private Edificio fabrica;
	private Edificio barraca;
	private Edificio puerto;
	private Edificio puertoEnConst;
	private final int turnosEspectro = 8;
	private final int turnosCiencia = 10;
	private final int turnosNave = 7;
	
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
		jugador.agregarGasVespeno(1200);
		jugador.agregarMinerales(1500);
		jugador.aumentarCapacidadPoblacion(20);
		this.barraca = crearEdificioRequerido();
		for(int i = 0; i < 12; i++) barraca.pasarTurno();//Construccion
		this.barraca = (Edificio) mapa.getOcupante(barraca.getPosicion());
		this.fabrica = crearEdificioRequeridoNivel2();
		for(int i = 0; i < 12; i++) fabrica.pasarTurno();//Construccion
		this.fabrica = (Edificio) mapa.getOcupante(fabrica.getPosicion());
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
	public void testPuertoEstelarPuedeEntrenarTresUnidades() {
		assertEquals(3, puerto.getUnidadesEntrenables().size());
	}
	
	@Test
	public void testPuertoEstelarPuedeEntrenarEspectro() {
		puedeEntrenarUnidad(Tipo.ESPECTRO);
	}
	
	@Test
	public void testPuertoEstelarPuedeEntrenarNaveDeCiencia() {
		puedeEntrenarUnidad(Tipo.NAVE_DE_CIENCIA);
	}
	
	@Test
	public void testPuertoEstelarPuedeEntrenarNaveDeTransporte() {
		puedeEntrenarUnidad(Tipo.NAVE_DE_TRANSPORTE_TERRAN);
	}
	
	@Test
	public void testPuertoEstelarMientrasEntrenaEspectroNoEstaDisponible() {
		mientrasEntrenaUnidadNoDisponible(Tipo.ESPECTRO, turnosEspectro);
	}
	
	@Test
	public void testPuertoEstelarMientrasEntrenaNaveCienciaNoEstaDisponible() {
		mientrasEntrenaUnidadNoDisponible(Tipo.NAVE_DE_CIENCIA, turnosCiencia);
	}
	
	@Test
	public void testPuertoEstelarMientrasEntrenaNaveTransporteNoEstaDisponible() {
		mientrasEntrenaUnidadNoDisponible(Tipo.NAVE_DE_TRANSPORTE_TERRAN, turnosNave);
	}
	
	@Test
	public void testPuertoEstelarEntrenaEspectro() {
		entrenarUnidadVerJugador(Tipo.ESPECTRO, turnosEspectro);
	}
	
	@Test
	public void testPuertoEstelarEntrenaNaveDeCiencia() {
		entrenarUnidadVerJugador(Tipo.NAVE_DE_CIENCIA, turnosCiencia);
	}
	
	@Test
	public void testPuertoEstelarEntrenaNaveTransporte() {
		entrenarUnidadVerJugador(Tipo.NAVE_DE_TRANSPORTE_TERRAN, turnosNave);
	}
	
	@Test
	public void testPuertoEstelarEntrenaEspectroDejaEnElMapa() {
		entrenarUnidadVerMapa(Tipo.ESPECTRO, turnosEspectro);
	}
	
	@Test
	public void testPuertoEstelarEntrenaNaveDeCienciaDejaEnElMapa() {
		entrenarUnidadVerMapa(Tipo.NAVE_DE_CIENCIA, turnosCiencia);
	}
	
	@Test
	public void testPuertoEstelarEntrenaNaveTransporteDejaEnElMapa() {
		entrenarUnidadVerMapa(Tipo.NAVE_DE_TRANSPORTE_TERRAN, turnosNave);
	}
	
	@Test
	public void testPuertoEstelarEntrenaEspectroConsumeRecursos() {
		entrenarUnidadConsumeRecursos(Tipo.ESPECTRO, turnosEspectro, 150, 100, 2);
	}
	
	@Test
	public void testPuertoEstelarEntrenaNaveDeCienciaConsumeRecursos() {
		entrenarUnidadConsumeRecursos(Tipo.NAVE_DE_CIENCIA, turnosCiencia, 100, 225, 2);
	}
	
	@Test
	public void testPuertoEstelarEntrenaNaveTransporteConsumeRecursos() {
		entrenarUnidadConsumeRecursos(Tipo.NAVE_DE_TRANSPORTE_TERRAN, turnosNave
										, 100, 100, 2);
	}
	
	@Test(expected = MineralInsuficiente.class)
	public void testPuertoEstelarEntrenaUnidadSinMineralesDebeFallar() {
		entrenarSinMinerales(Tipo.ESPECTRO);
		entrenarSinMinerales(Tipo.NAVE_DE_CIENCIA);
		entrenarSinMinerales(Tipo.NAVE_DE_TRANSPORTE_TERRAN);
	}
	
	@Test(expected = GasVespenoInsuficiente.class)
	public void testPuertoEstelarEntrenaUnidadSinGasVespenoDebeFallar() {
		entrenarSinGasVespeno(Tipo.ESPECTRO);
		entrenarSinGasVespeno(Tipo.NAVE_DE_CIENCIA);
		entrenarSinGasVespeno(Tipo.NAVE_DE_TRANSPORTE_TERRAN);
	}
	
	@Test(expected = SuministroInsuficiente.class)
	public void testPuertoEstelarEntrenaUnidadSinPoblacionDebeFallar() {
		entrenarSinCapacidadPoblacion(Tipo.ESPECTRO);
		entrenarSinCapacidadPoblacion(Tipo.NAVE_DE_CIENCIA);
		entrenarSinCapacidadPoblacion(Tipo.NAVE_DE_TRANSPORTE_TERRAN);
	}
	
	@Test
	public void testPuertoEstelarEntrenaVariasUnidadesSinColaDeEspera() {
		for (int i = 0; i < 2; i++){
			entrenarUnidadVerJugador(Tipo.ESPECTRO, turnosEspectro);
			entrenarUnidadVerJugador(Tipo.NAVE_DE_CIENCIA, turnosCiencia);
			entrenarUnidadVerJugador(Tipo.NAVE_DE_TRANSPORTE_TERRAN, turnosNave);
		}
	}
	
	@Test
	public void testPuertoEstelarEntrenaVariasUnidadesConColaDeEspera() {
		entrenarVariasUnidadesSostieneElOrden(puerto.getUnidadesEntrenables(),
				turnosEspectro, turnosCiencia, turnosNave);
	}
}
