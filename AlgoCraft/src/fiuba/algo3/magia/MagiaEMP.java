package fiuba.algo3.magia;

import fiuba.algo3.excepciones.FueraDelRangoPermitido;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.unidades.Unidad;

public class MagiaEMP implements MagiaDeAreaDeEfecto {

	private int rangoAoE;
	private int costoDeEjecutar;
	private Unidad unidad;
	
	public MagiaEMP() {
		this.costoDeEjecutar = 100;
		this.rangoAoE = 15; //INVENTADO!
	}
	
	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}
	
	public boolean puedeEjecutar(Posicion posicionCentral) {
		return (this.unidad.estaEnRangoDeMagia(posicionCentral) &&
				this.unidad.puedeDisminuirEnergia(this.costoDeEjecutar));
	}

	public void ejecutar(Posicion posicionCentral, Mapa mapa) {
		if (!this.unidad.estaEnRangoDeMagia(posicionCentral))
			throw new FueraDelRangoPermitido();
		this.unidad.disminuirEnergia(this.costoDeEjecutar);
		
		for (Unidad unidad : mapa.getUnidadesEnRango(posicionCentral, rangoAoE)) {
			if (unidad.getPropietario() != this.unidad.getPropietario()) {
				unidad.disminuirEnergia(unidad.getEnergia());
			}
		}
	}

}
