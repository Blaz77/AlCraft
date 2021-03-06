package fiuba.algo3.unidades;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.MapaReal;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.raza.TipoRaza;

public abstract class TestUnidad {

	protected MapaReal mapa;
	protected Jugador jugador;
	protected Jugador jugadorEnemigo;
	protected Unidad unidad;
	protected Edificio edificioPropio;
	protected Edificio edificioEnemigo;
	
	// Ubicaciones relativas
	/* ....B....
	 * ...HA.C..
	 * ....D....
	 * ..E...F..
	 * .....G...
	 */
	
	protected final Posicion POSICION_A = new Posicion(32, 10);
	protected final Posicion POSICION_B = new Posicion(32, 9);
	protected final Posicion POSICION_C = new Posicion(34, 10);
	protected final Posicion POSICION_D = new Posicion(32, 11);
	protected final Posicion POSICION_E = new Posicion(30, 12);
	protected final Posicion POSICION_F = new Posicion(34, 12);
	protected final Posicion POSICION_G = new Posicion(33, 13);
	protected final Posicion POSICION_H = new Posicion(31, 10);

	@Before
	public void setUp() throws Exception {
		mapa = new MapaReal(6);
	}

	@Test
	public void testUnidadPuedeMoverse() {
		assertTrue(this.unidad.puedeMoverse());
	}
	
	@Test
	public void testUnidadMoverAPosicionValida() {
		assertTrue(unidad.puedeMoverseA(new Posicion(33, 10)));
		unidad.moverA(new Posicion(33, 10));
		
		assertTrue(mapa.getOcupante(new Posicion(33, 10)).equals(unidad));
	}

}
