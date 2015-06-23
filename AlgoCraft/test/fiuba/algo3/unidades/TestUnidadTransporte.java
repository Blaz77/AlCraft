package fiuba.algo3.unidades;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fiuba.algo3.ocupantes.unidades.Unidad;


public abstract class TestUnidadTransporte extends TestUnidad {
	protected int capacidad;

	protected Unidad pasajero;
	protected Unidad unidadVoladora;
	protected Unidad unidadEnemigaTerrestre;
	protected Unidad unidadEnemigaAerea;
	

	@Test
	public void testUnidadNoPuedeAtacar() {
		assertFalse(this.unidad.puedeAtacar());
	}

	@Test
	public void testUnidadNoPuedeHacerMagia() {
		assertFalse(this.unidad.puedeHacerMagia());
	}

	@Test
	public void testUnidadPuedeTransportar() {
		assertTrue(this.unidad.puedeAlmacenar());
	}
	
	@Test
	public void testUnidadNoPuedeSerTransportado() {
		assertFalse(this.unidad.puedeSerAlmacenada());
	}
	
	@Test
	public void testUnidadRecienCreadaEstaVacia() {
		assertTrue(this.unidad.getUnidadesAlmacenadas().isEmpty());
	}


}
