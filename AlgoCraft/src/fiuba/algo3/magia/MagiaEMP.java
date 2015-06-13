package fiuba.algo3.magia;

import fiuba.algo3.excepciones.FueraDelRangoPermitido;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.ocupantes.unidades.UnidadMagica;

public class MagiaEMP implements MagiaDeAreaDeEfecto {

	private int rangoAoE;
	private int costoDeEjecutar;
	private UnidadMagica unidad;
	
	public MagiaEMP() {
		this.costoDeEjecutar = 100;
		this.rangoAoE = 15; //INVENTADO!
	}
	
	public void setUnidadMagica(UnidadMagica unidad) {
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
			if (unidad.puedeHacerMagia()) {
				UnidadMagica unidadAfectada = (UnidadMagica) unidad;
				unidadAfectada.disminuirEnergia(unidadAfectada.getEnergia());
			}
		}
	}

}
