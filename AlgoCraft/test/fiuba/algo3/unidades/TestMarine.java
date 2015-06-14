package fiuba.algo3.unidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Barraca;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.ocupantes.unidades.UnidadAtaque;
import fiuba.algo3.raza.TipoRaza;

public class TestMarine extends TestUnidadAtaque {
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.unidad = new Unidad(this.jugador, new Posicion(2,4), jugador.getAtributos().getInfanteriaLivianaTerrestre());
		this.otraUnidad = new Unidad(this.jugador, new Posicion(1,3), jugador.getAtributos().getInfanteriaLivianaTerrestre());
		this.edificioPropio = new Barraca(this.jugador, new Posicion(2,6));
		this.jugadorEnemigo = new Jugador("Enemigo", Color.ROJO, TipoRaza.TERRAN, mapa);
		this.unidadEnemigaTerrestre = new Unidad(jugadorEnemigo, new Posicion(5,4), jugadorEnemigo.getAtributos().getInfanteriaLivianaTerrestre());
		this.unidadEnemigaAerea = new Unidad(jugadorEnemigo, new Posicion(1,2), jugadorEnemigo.getAtributos().getInfanteriaPesadaArea());
		this.edificioEnemigo = new Barraca(jugadorEnemigo, new Posicion(3,3));	
		
		this.danioTierra = 6;
		this.danioAire = 6;
	}
	
	@Test
	public void testMarineNoPuedeVolar() {
		assertFalse(this.unidad.puedeOcuparEspacio());
	}
	
	
}
