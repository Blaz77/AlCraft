package fiuba.algo3.unidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Fabrica;
import fiuba.algo3.ocupantes.edificios.PuertoEstelar;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.raza.TipoRaza;

public class TestEspectro extends TestUnidadAtaque {
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.jugador = new Jugador("Prueba", Color.AZUL, TipoRaza.TERRAN, mapa);
		this.unidad = new Unidad(this.jugador, POSICION_A, jugador.getAtributos().getInfanteriaPesadaArea());
		this.otraUnidad = new Unidad(this.jugador, POSICION_B, jugador.getAtributos().getInfanteriaPesadaArea());
		this.edificioPropio = new PuertoEstelar(this.jugador, POSICION_C);
		this.jugadorEnemigo = new Jugador("Enemigo", Color.ROJO, TipoRaza.TERRAN, mapa);
		this.unidadEnemigaTerrestre = new Unidad(jugadorEnemigo, POSICION_D, jugadorEnemigo.getAtributos().getInfanteriaPesadaTerrestre());
		this.unidadEnemigaAerea = new Unidad(jugadorEnemigo, POSICION_E, jugadorEnemigo.getAtributos().getInfanteriaPesadaArea());
		this.edificioEnemigo = new Fabrica(jugadorEnemigo, POSICION_F);	

		this.danioTierra = 8;
		this.danioAire = 20;
	}
	
	@Test
	public void testEspectroPuedeVolar() {
		assertTrue(this.unidad.puedeOcuparEspacio());
	}
	
	
}
