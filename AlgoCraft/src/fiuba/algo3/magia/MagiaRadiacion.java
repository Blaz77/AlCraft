package fiuba.algo3.magia;

import fiuba.algo3.componentes.EstadoIrradiado;
import fiuba.algo3.excepciones.FueraDelRangoPermitido;
import fiuba.algo3.excepciones.NoEsUnEnemigo;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.ocupantes.unidades.UnidadMagica;

public class MagiaRadiacion implements MagiaAUnidad {

	private UnidadMagica unidad;
	private int costoDeEjecutar;
	
	public MagiaRadiacion() {
		this.costoDeEjecutar = 75;
	}
	
	public void setUnidadMagica(UnidadMagica unidad) {
		this.unidad = unidad;		
	}
	
	public boolean puedeEjecutar(Unidad otraUnidad) {
		return (this.unidad.esEnemigoDe(otraUnidad) &&
			this.unidad.estaEnRangoDeMagia(otraUnidad.getPosicion()) &&
			this.unidad.puedeDisminuirEnergia(this.costoDeEjecutar));
	}

	public void ejecutar(Unidad enemiga) {
		//chequeo si es enemiga (no se si es necesario, pero bueno)
		if (!this.unidad.esEnemigoDe(enemiga)) throw new NoEsUnEnemigo();
		if (!this.unidad.estaEnRangoDeMagia(enemiga.getPosicion()))
			throw new FueraDelRangoPermitido();
		this.unidad.disminuirEnergia(this.costoDeEjecutar);
		enemiga.agregarEstado(new EstadoIrradiado());
	}


}
