package fiuba.algo3.tp_final;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.PosicionOcupada;
import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Celda;
import fiuba.algo3.mapa.MapaReal;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.Ocupante;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.TipoOcupante;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.terreno.Terreno;

public class TestMapa {
	final int cantidadBases = 6;
	final int SEMILADO_BASE = 8;
	final int MINERALES_POR_BASE = 6;

	private MapaReal mapaNuevo;

	/* Metodos auxiliares */
	
	/* Suma las distancias entre la base de puntoBase y cada una de las bases ajenas dadas
	 * como puntos
	 */
	private double sumarDistancias(Celda puntoBase, ArrayList<Celda> basesAjenas) {
		double suma = 0;
		
		for (int i=0; i < basesAjenas.size(); i++) {
			suma += puntoBase.distancia(basesAjenas.get(i));
		}
		
		return suma;
	}
	
	private boolean perteneceAlMapa(int x, int y, MapaReal mapa) {
		if (x < 0 || y < 0)
			return false;
		if (x > mapa.ancho() - 1 || y > mapa.alto() - 1)
			return false;
		
		return true;
	}
	
	/* Pruebas */
	
	@Before
	public void setUp() {
		mapaNuevo = new MapaReal(cantidadBases);
	}
		
	@Test
	public void testMapaGeneradoRespetaCantidadDeBases() {
		Assert.assertTrue(mapaNuevo.getBases().size() == cantidadBases);
	}
	
	@Test
	public void testMapaGeneradoDispersaBasesEnFormaJusta() {
		final int DIFERENCIA_TOLERABLE = 40;
		
		Celda puntoGeneracionJugador1 = mapaNuevo.obtenerBaseDeJugador(1);
		Celda puntoGeneracionJugador2 = mapaNuevo.obtenerBaseDeJugador(2);
		
		// Dispersa bien cuando la suma de distancias a las bases es similar en ambos 
		// jugadores
		ArrayList<Celda> basesAjenas = mapaNuevo.getBases();
		basesAjenas.remove(puntoGeneracionJugador1);
		basesAjenas.remove(puntoGeneracionJugador2);
		
		double diferenciaDistancias = Math.abs(sumarDistancias(puntoGeneracionJugador1, basesAjenas) -
											sumarDistancias(puntoGeneracionJugador2, basesAjenas));
		
		Assert.assertTrue(diferenciaDistancias <= DIFERENCIA_TOLERABLE);
	}
	/*
	@Test
	public void testMapaGeneradoDejaEnBasesUnMineral(){
		
		Celda puntoGeneracionJugador1 = mapaNuevo.obtenerBaseDeJugador(1);
		Celda puntoGeneracionJugador2 = mapaNuevo.obtenerBaseDeJugador(2);
		
		Assert.assertEquals(puntoGeneracionJugador1.getRecurso().getClass(),Mineral.class);
		Assert.assertEquals(puntoGeneracionJugador2.getRecurso().getClass(),Mineral.class);
	}*/
	
	@Test
	public void testMapaGeneradoDejaRecursosCercaDeCadaBase(){
				
		// Se toma un sector cuadrado en torno a cada base y se comprueba que haya un volcan
		// y al menos 2 cristales
		ArrayList<Celda> bases = mapaNuevo.getBases();
		int cristales, volcanes;
		Ocupante recurso;
		
		for (Celda baseActual : bases) {
			cristales = 0;
			volcanes = 0;
			for (int x=baseActual.getX()-SEMILADO_BASE; x<baseActual.getX()+SEMILADO_BASE + 1; x++) {
				for (int y=baseActual.getY()-SEMILADO_BASE; y<baseActual.getY()+SEMILADO_BASE + 1; y++) {
					if (perteneceAlMapa(x, y, mapaNuevo)) {
						// Inspeccion de la celda
						recurso = mapaNuevo.getOcupante(new Posicion(x,y));
						if (recurso == null) continue; //Despues sacar si hacemos RecursoNULL
						if (recurso.getTipo() == Tipo.MINERAL)
							cristales++;
						if (recurso.getTipo() == Tipo.VESPENO)
							volcanes++;
					}
				}
			}
			
			Assert.assertTrue(volcanes == 1);
			Assert.assertTrue(cristales >= MINERALES_POR_BASE);
		}
	}
	
	
	
	@Test
	public void testMapaGeneradoCreaBasesEnTierra() {
		//final int RADIO_BASE = 8;
		
		for (int y = 0; y < mapaNuevo.alto(); y++)
			for (int x = 0; x < mapaNuevo.ancho(); x++){
				// O es terrestre, o no tiene base, y punto (cuac)
				Posicion pos = new Posicion(x,y);
				Assert.assertTrue(mapaNuevo.getTerreno(pos)== Terreno.TIERRA || !(mapaNuevo.esBase(pos)));
			}
	}
	
	@Test
	public void testMapaGeneradoTieneAreasEspaciales() {
		boolean hayAreasEspaciales = false;
		
		for (int y = 0; y < mapaNuevo.alto(); y++)
			for (int x = 0; x < mapaNuevo.ancho(); x++){
				if (mapaNuevo.getTerreno(new Posicion(x,y)) == Terreno.ESPACIO) {
					hayAreasEspaciales = true;
					break;
			}
		}
		
		Assert.assertTrue(hayAreasEspaciales);
	}
	
	/* Test de superposicion */
	
	// Zona libre cerca del (32, 10)
	
	@Test(expected = PosicionOcupada.class)
	public void testCrearVariasUnidadesEnUnaCeldaDebeFallar() {
		Jugador jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.TERRAN, mapaNuevo);
		
		Unidad unidad = new Unidad(jugador, new Posicion(32, 10), jugador.getAtributos().getInfanteriaLivianaTerrestre());
		Unidad otraUnidad = new Unidad(jugador, new Posicion(32, 10), jugador.getAtributos().getInfanteriaLivianaTerrestre());
		
		mapaNuevo.setOcupante(unidad, new Posicion(32, 10));
		mapaNuevo.setOcupante(otraUnidad, new Posicion(32, 10));
	}
	
	@Test(expected = PosicionOcupada.class)
	public void testCrearVariosEdificiosEnUnaCeldaDebeFallar() {
		Jugador jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.TERRAN, mapaNuevo);
		
		Edificio edificio = new Edificio(jugador, new Posicion(32, 10), jugador.getAtributos().getIncrementadorPoblacion());
		Edificio otroEdificio = new Edificio(jugador, new Posicion(32, 10), jugador.getAtributos().getIncrementadorPoblacion());
		
		mapaNuevo.setOcupante(edificio, new Posicion(32, 10));
		mapaNuevo.setOcupante(otroEdificio, new Posicion(32, 10));
	}
}