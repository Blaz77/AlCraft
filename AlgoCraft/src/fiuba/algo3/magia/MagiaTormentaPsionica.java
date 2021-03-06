package fiuba.algo3.magia;

import fiuba.algo3.componentes.EstadoAtormentando;
import fiuba.algo3.excepciones.FueraDelRangoPermitido;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.unidades.Unidad;

//TEMPORAL SOLO PARA PROBAR
public class MagiaTormentaPsionica implements MagiaDeAreaDeEfecto {

	private int rangoAoE;
	private int costoDeEjecutar;
	private Unidad unidad;
	
	public MagiaTormentaPsionica() {
		this.costoDeEjecutar = 75;
		this.rangoAoE = 15; //INVENTADO!
	}
	
	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}
	
	public boolean puedeEjecutar(Posicion posicionCentral) {
		return (this.unidad.estaEnRangoDeMagia(posicionCentral) &&
				this.unidad.puedeDisminuirEnergia(this.costoDeEjecutar));
	}

	public void ejecutar(Posicion posicionCentral) {
		if (!this.unidad.estaEnRangoDeMagia(posicionCentral))
			throw new FueraDelRangoPermitido();
		this.unidad.disminuirEnergia(this.costoDeEjecutar);
		unidad.agregarEstado(new EstadoAtormentando(
				posicionCentral, unidad.getPropietario().getMapa(), rangoAoE));
	}

}
