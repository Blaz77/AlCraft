package fiuba.algo3.unidades;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fiuba.algo3.ocupantes.unidades.Unidad;

public abstract class TestUnidadMagica extends TestUnidad {
	
	protected Unidad otraUnidad;
	protected Unidad unidadEnemigaTerrestre;
	protected Unidad unidadEnemigaAerea;
	protected Unidad unidadEnemigaMagica;


	@Test
	public void testUnidadNoPuedeAtacar() {
		assertFalse(this.unidad.puedeAtacar());
	}
	
	@Test
	public void testUnidadPuedeHacerMagia() {
		assertTrue(this.unidad.puedeHacerMagia());
	}

}
