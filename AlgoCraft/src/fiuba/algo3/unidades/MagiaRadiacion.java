package fiuba.algo3.unidades;

import fiuba.algo3.componentes.EstadoIrradiado;
import fiuba.algo3.excepciones.FueraDelRangoPermitido;
import fiuba.algo3.excepciones.NoEsUnEnemigo;

public class MagiaRadiacion extends MagiaAUnidad {

	public MagiaRadiacion() {
		this.costoDeEjecutar = 75;
	}
	
	@Override
	public boolean puedeEjecutar(Unidad otraUnidad) {
		return (this.unidad.esEnemigoDe(otraUnidad) &&
			this.unidad.estaEnRangoDeMagia(otraUnidad.getPosicion()) &&
			this.unidad.puedeDisminuirEnergia(this.costoDeEjecutar));
	}
	
	@Override
	public void ejecutar(Unidad enemiga) {
		//chequeo si es enemiga (no se si es necesario, pero bueno)
		if (!this.unidad.esEnemigoDe(enemiga)) throw new NoEsUnEnemigo();
		if (!this.unidad.estaEnRangoDeMagia(enemiga.getPosicion()))
			throw new FueraDelRangoPermitido();
		this.unidad.disminuirEnergia(this.costoDeEjecutar);
		enemiga.agregarEstado(new EstadoIrradiado());
	}



}
