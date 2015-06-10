package fiuba.algo3.unidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Barraca;
import fiuba.algo3.ocupantes.edificios.Fabrica;
import fiuba.algo3.ocupantes.unidades.UnidadAtaque;
import fiuba.algo3.raza.TipoRaza;

public class TestGolliat extends TestUnidadAtaque {
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.unidad = new UnidadAtaque(this.jugador, new Posicion(2,4), jugador.getAtributos().getInfanteriaPesadaTerrestre());
		this.otraUnidad = new UnidadAtaque(this.jugador, new Posicion(1,3), jugador.getAtributos().getInfanteriaPesadaTerrestre());
		this.edificioPropio = new Fabrica(this.jugador, new Posicion(2,6));
		this.jugadorEnemigo = new Jugador("Enemigo", Color.ROJO, TipoRaza.TERRAN, mapa);
		this.unidadEnemigaTerrestre = new UnidadAtaque(jugadorEnemigo, new Posicion(5,4), jugadorEnemigo.getAtributos().getInfanteriaPesadaTerrestre());
		this.unidadEnemigaAerea = new UnidadAtaque(jugadorEnemigo, new Posicion(1,2), jugadorEnemigo.getAtributos().getInfanteriaPesadaArea());
		this.edificioEnemigo = new Fabrica(jugadorEnemigo, new Posicion(3,3));	

		this.danioTierra = 12;
		this.danioAire = 10;
	}
	
	@Test
	public void testGolliatNoPuedeVolar() {
		assertFalse(this.unidad.puedeOcuparEspacio());
	}
	
	
}
