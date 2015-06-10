package fiuba.algo3.unidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.edificios.Barraca;
import fiuba.algo3.edificios.Fabrica;
import fiuba.algo3.edificios.PuertoEstelar;
import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.raza.TipoRaza;

public class TestNaveDeCiencia extends TestUnidadMagica {
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.unidad = new UnidadMagica(this.jugador, new Posicion(2,4), jugador.getAtributos().getInfanteriaMagica());
		this.otraUnidad = new UnidadMagica(this.jugador, new Posicion(1,3), jugador.getAtributos().getInfanteriaMagica());
		this.edificioPropio = new PuertoEstelar(this.jugador, new Posicion(2,6));
		this.jugadorEnemigo = new Jugador("Enemigo", Color.ROJO, TipoRaza.TERRAN, mapa);
		this.unidadEnemigaTerrestre = new UnidadAtaque(jugadorEnemigo, new Posicion(5,4), jugadorEnemigo.getAtributos().getInfanteriaPesadaTerrestre());
		this.unidadEnemigaAerea = new UnidadAtaque(jugadorEnemigo, new Posicion(1,2), jugadorEnemigo.getAtributos().getInfanteriaPesadaArea());
		this.unidadEnemigaMagica = new UnidadMagica(jugadorEnemigo, new Posicion(1,2), jugadorEnemigo.getAtributos().getInfanteriaMagica());
		this.edificioEnemigo = new Fabrica(jugadorEnemigo, new Posicion(3,3));	

		this.danioTierra = 8;
		this.danioAire = 20;
	}
	
	@Test
	public void testEspectroNoPuedeHacerMagia() {
		assertFalse(this.unidad.puedeHacerMagia());
	}
	
	@Test
	public void testEspectroPuedeVolar() {
		assertTrue(this.unidad.puedeVolar());
	}
	
	
}
