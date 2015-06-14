package fiuba.algo3.unidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.excepciones.FueraDelRangoPermitido;
import fiuba.algo3.excepciones.NoEsUnEnemigo;
import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Barraca;
import fiuba.algo3.ocupantes.edificios.EdificioEntrenadorUnidades;
import fiuba.algo3.ocupantes.unidades.UnidadAtaque;
import fiuba.algo3.ocupantes.unidades.UnidadMagica;
import fiuba.algo3.raza.TipoRaza;

public abstract class TestUnidadMagica extends TestUnidad {
	
	protected UnidadMagica unidad;
	protected UnidadMagica otraUnidad;
	protected UnidadAtaque unidadEnemigaTerrestre;
	protected UnidadAtaque unidadEnemigaAerea;
	protected UnidadMagica unidadEnemigaMagica;


	@Test
	public void testUnidadNoPuedeAtacar() {
		assertFalse(this.unidad.puedeAtacar());
	}
	
	@Test
	public void testUnidadPuedeHacerMagia() {
		assertTrue(this.unidad.puedeHacerMagia());
	}

	@Test
	public void testUnidadPuedeMoverse() {
		assertTrue(this.unidad.puedeMoverse());
	}

	

}
