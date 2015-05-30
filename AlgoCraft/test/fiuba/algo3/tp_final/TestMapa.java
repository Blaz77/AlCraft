package fiuba.algo3.tp_final;

import junit.framework.Assert;

import org.junit.Test;

public class TestMapa {
	final int SEIS_BASES = 6;
	private Mapa mapaNuevo = new Mapa(SEIS_BASES);

	@Test
	public void testMapaGeneradoRespetaSeparacionJugadores() {
		Punto puntoGeneracionJugador1 = mapaNuevo.obtenerPuntoGeneradorJugador(1);
		Punto puntoGeneracionJugador2 = mapaNuevo.obtenerPuntoGeneradorJugador(2);
		
		// Para considerar que los jugadores están en extremos opuestos, su distancia
		// horizontal o vertical debe ser similar al ancho o alto del mapa respectivamente
		
		int distanciaHorizontal = Math.abs(puntoGeneracionJugador1.x - puntoGeneracionJugador2.x);
		int distanciaVertical = Math.abs(puntoGeneracionJugador1.y - puntoGeneracionJugador2.y);
		
		Assert.assertTrue(distanciaHorizontal > mapaNuevo.ancho() * 0.8 || 
						distanciaVertical > mapaNuevo.alto() * 0.8);
	}
	
}
