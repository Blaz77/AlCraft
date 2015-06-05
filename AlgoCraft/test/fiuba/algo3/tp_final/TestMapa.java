package fiuba.algo3.tp_final;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.mapa.Celda;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.mapa.recurso.Mineral;
import fiuba.algo3.mapa.recurso.Recurso;
import fiuba.algo3.mapa.recurso.TipoRecurso;
import fiuba.algo3.terreno.TipoTerreno;

public class TestMapa {
	final int SEIS_BASES = 6;
	private Mapa mapaNuevo;

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
	
	private boolean perteneceAlMapa(int x, int y, Mapa mapa) {
		if (x < 0 || y < 0)
			return false;
		if (x > mapa.ancho() - 1 || y > mapa.alto() - 1)
			return false;
		
		return true;
	}
	
	/* Pruebas */
	
	@Before
	public void setUp() {
		mapaNuevo = new Mapa(SEIS_BASES);
	}
	
	@Test
	public void testMapaGeneradoRespetaSeparacionJugadores() {
		Celda puntoGeneracionJugador1 = mapaNuevo.obtenerPuntoGeneradorJugador(1);
		Celda puntoGeneracionJugador2 = mapaNuevo.obtenerPuntoGeneradorJugador(2);
		
		// Para considerar que los jugadores estan en extremos opuestos, su distancia
		// horizontal o vertical debe ser similar al ancho o alto del mapa respectivamente
		
		int distanciaHorizontal = Math.abs(puntoGeneracionJugador1.getX() - puntoGeneracionJugador2.getX());
		int distanciaVertical = Math.abs(puntoGeneracionJugador1.getY() - puntoGeneracionJugador2.getY());
		
		Assert.assertTrue(distanciaHorizontal > mapaNuevo.ancho() * 0.7 || 
						distanciaVertical > mapaNuevo.alto() * 0.7);
	}
	
	@Test
	public void testMapaGeneradoRespetaCantidadDeBases() {
		Assert.assertTrue(mapaNuevo.getBases().size() == SEIS_BASES);
	}
	
	@Test
	public void testMapaGeneradoDispersaBasesEnFormaJusta() {
		final int DIFERENCIA_TOLERABLE = 40;
		
		Celda puntoGeneracionJugador1 = mapaNuevo.obtenerPuntoGeneradorJugador(1);
		Celda puntoGeneracionJugador2 = mapaNuevo.obtenerPuntoGeneradorJugador(2);
		
		// Dispersa bien cuando la suma de distancias a las bases es similar en ambos 
		// jugadores
		ArrayList<Celda> basesAjenas = mapaNuevo.getBases();
		basesAjenas.remove(puntoGeneracionJugador1);
		basesAjenas.remove(puntoGeneracionJugador2);
		
		double diferenciaDistancias = Math.abs(sumarDistancias(puntoGeneracionJugador1, basesAjenas) -
											sumarDistancias(puntoGeneracionJugador2, basesAjenas));
		
		Assert.assertTrue(diferenciaDistancias <= DIFERENCIA_TOLERABLE);
	}
	
	@Test
	public void testMapaGeneradoDejaEnBasesUnMineral(){
		
		Celda puntoGeneracionJugador1 = mapaNuevo.obtenerPuntoGeneradorJugador(1);
		Celda puntoGeneracionJugador2 = mapaNuevo.obtenerPuntoGeneradorJugador(2);
		
		Assert.assertEquals(puntoGeneracionJugador1.getRecurso().getClass(),Mineral.class);
		Assert.assertEquals(puntoGeneracionJugador2.getRecurso().getClass(),Mineral.class);
	}
	
	@Test
	public void testMapaGeneradoDejaRecursosCercaDeCadaBase(){
		final int RADIO_BASE = 8;
		
		// Se toma un sector cuadrado en torno a cada base y se comprueba que haya un volcan
		// y al menos 2 cristales
		ArrayList<Celda> bases = mapaNuevo.getBases();
		int cristales, volcanes;
		Recurso recurso;
		
		for (Celda baseActual : bases) {
			cristales = 0;
			volcanes = 0;
			for (int x=baseActual.getX()-RADIO_BASE; x<baseActual.getX()+RADIO_BASE; x++) {
				for (int y=baseActual.getY()-RADIO_BASE; y<baseActual.getY()+RADIO_BASE; y++) {
					if (perteneceAlMapa(x, y, mapaNuevo)) {
						// Inspeccion de la celda
						recurso = mapaNuevo.getRecurso(new Posicion(x,y));
						if (recurso == null) continue; //Despues sacar si hacemos RecursoNULL
						if (recurso.getTipo() == TipoRecurso.MINERAL)
							cristales++;
						if (recurso.getTipo() == TipoRecurso.VESPENO)
							volcanes++;
					}
				}
			}
			
			Assert.assertTrue(volcanes == 1);
			Assert.assertTrue(cristales >= 2);
		}
	}
	
	
	
	@Test
	public void testMapaGeneradoCreaBasesEnTierra() {
		//final int RADIO_BASE = 8;
		
		for (int y = 0; y < mapaNuevo.alto(); y++)
			for (int x = 0; x < mapaNuevo.ancho(); x++){
				// O es terrestre, o no tiene base, y punto (cuac)
				Posicion pos = new Posicion(x,y);
				Assert.assertTrue(mapaNuevo.getTerreno(pos).getTipo() == TipoTerreno.TIERRA || !(mapaNuevo.esBase(x, y)));
			}
	}
	
	@Test
	public void testMapaGeneradoTieneAreasEspaciales() {
		boolean hayAreasEspaciales = false;
		
		for (int y = 0; y < mapaNuevo.alto(); y++)
			for (int x = 0; x < mapaNuevo.ancho(); x++){
				if (mapaNuevo.getTerreno(new Posicion(x,y)).getTipo() == TipoTerreno.ESPACIO) {
					hayAreasEspaciales = true;
					break;
			}
		}
		
		Assert.assertTrue(hayAreasEspaciales);
	}
	
}