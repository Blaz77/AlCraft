package fiuba.algo3.unidades;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fiuba.algo3.ocupantes.unidades.Unidad;


public abstract class TestUnidadTransporte extends TestUnidad {
	protected int capacidad;

	protected Unidad transportador;
	protected Unidad pasajero;
	protected Unidad unidadVoladora;
	protected Unidad unidadEnemigaTerrestre;
	protected Unidad unidadEnemigaAerea;
	

	@Test
	public void testUnidadNoPuedeAtacar() {
		assertFalse(this.transportador.puedeAtacar());
	}

	@Test
	public void testGolliatNoPuedeHacerMagia() {
		assertFalse(this.transportador.puedeHacerMagia());
	}

	@Test
	public void testUnidadPuedeMoverse() {
		assertTrue(this.transportador.puedeMoverse());
	}
	
	@Test
	public void testUnidadPuedeTransportar() {
		assertTrue(this.transportador.puedeAlmacenar());
	}
	
	@Test
	public void testUnidadNoPuedeSerTransportado() {
		assertFalse(this.transportador.puedeSerAlmacenada());
	}
	
	@Test
	public void testUnidadRecienCreadaEstaVacia() {
		assertTrue(this.transportador.getUnidadesAlmacenadas().isEmpty());
	}


}
