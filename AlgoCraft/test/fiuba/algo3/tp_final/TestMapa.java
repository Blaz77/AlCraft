package fiuba.algo3.tp_final;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestMapa {
	final int SEIS_BASES = 6;
	private Mapa mapaNuevo;

	/* M�todos auxiliares */
	
	/* Suma las distancias entre la base de puntoBase y cada una de las bases ajenas dadas
	 * como puntos
	 */
	private double sumarDistancias(Punto puntoBase, ArrayList<Punto> basesAjenas) {
		double suma = 0;
		
		for (int i=0; i < basesAjenas.size(); i++) {
			suma += puntoBase.distancia(basesAjenas.get(i));
		}
		
		return suma;
	}
	
	/* Pruebas */
	
	@Before
	public void setUp() {
		mapaNuevo = new Mapa(SEIS_BASES);
	}
	
	@Test
	public void testMapaGeneradoRespetaSeparacionJugadores() {
		Punto puntoGeneracionJugador1 = mapaNuevo.obtenerPuntoGeneradorJugador(1);
		Punto puntoGeneracionJugador2 = mapaNuevo.obtenerPuntoGeneradorJugador(2);
		
		// Para considerar que los jugadores est�n en extremos opuestos, su distancia
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
		
		Punto puntoGeneracionJugador1 = mapaNuevo.obtenerPuntoGeneradorJugador(1);
		Punto puntoGeneracionJugador2 = mapaNuevo.obtenerPuntoGeneradorJugador(2);
		
		// Dispersa bien cuando la suma de distancias a las bases es similar en ambos 
		// jugadores
		ArrayList<Punto> basesAjenas = mapaNuevo.getBases();
		basesAjenas.remove(puntoGeneracionJugador1);
		basesAjenas.remove(puntoGeneracionJugador2);
		
		double diferenciaDistancias = Math.abs(sumarDistancias(puntoGeneracionJugador1, basesAjenas) -
											sumarDistancias(puntoGeneracionJugador2, basesAjenas));
		
		Assert.assertTrue(diferenciaDistancias <= DIFERENCIA_TOLERABLE);
	}
	
	@Test
	public void testMapaGeneradoDejaEnBasesUnMineral(){
		
		Punto puntoGeneracionJugador1 = mapaNuevo.obtenerPuntoGeneradorJugador(1);
		Punto puntoGeneracionJugador2 = mapaNuevo.obtenerPuntoGeneradorJugador(2);
		
		Assert.assertEquals(puntoGeneracionJugador1.getRecurso().getClass(),Mineral.class);
		Assert.assertEquals(puntoGeneracionJugador2.getRecurso().getClass(),Mineral.class);
	}
	
}
