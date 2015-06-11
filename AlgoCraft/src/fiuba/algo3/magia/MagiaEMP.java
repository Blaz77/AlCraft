package fiuba.algo3.magia;

import fiuba.algo3.excepciones.FueraDelRangoPermitido;
import fiuba.algo3.mapa.Posicion;

public class MagiaEMP extends MagiaDeAreaDeEfecto {

	public MagiaEMP() {
		this.costoDeEjecutar = 100;
		this.rangoAoE = 15; //INVENTADO!
	}
	
	@Override
	public boolean puedeEjecutar(Posicion posicionCentral) {
		return (this.unidad.estaEnRangoDeMagia(posicionCentral) &&
				this.unidad.puedeDisminuirEnergia(this.costoDeEjecutar));
	}

	@Override
	public void ejecutar(Posicion posicionCentral) {
		if (!this.unidad.estaEnRangoDeMagia(posicionCentral))
			throw new FueraDelRangoPermitido();
		this.unidad.disminuirEnergia(this.costoDeEjecutar);
		for (unidad : )
	}

}
