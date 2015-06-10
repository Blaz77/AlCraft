package fiuba.algo3.unidades;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fiuba.algo3.edificios.EdificioEntrenadorUnidades;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Mapa;

public class TestUnidad {

	protected Mapa mapa;
	protected Jugador jugador;
	protected Jugador jugadorEnemigo;
	protected UnidadAtaque unidad;
	protected UnidadAtaque otraUnidad;
	protected EdificioEntrenadorUnidades edificioPropio;
	protected EdificioEntrenadorUnidades edificioEnemigo;

	@Test
	public void testUnidadPuedeMoverse() {
		assertTrue(this.unidad.puedeMoverse());
	}

}
