package fiuba.algo3.magia;

import fiuba.algo3.componentes.EstadoAtormentando;
import fiuba.algo3.excepciones.FueraDelRangoPermitido;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.ocupantes.unidades.UnidadMagica;
//TEMPORAL SOLO PARA PROBAR
public class MagiaTormentaPsionica implements MagiaDeAreaDeEfecto {

	private int rangoAoE;
	private int costoDeEjecutar;
	private UnidadMagica unidad;
	
	public MagiaTormentaPsionica() {
		this.costoDeEjecutar = 75;
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
		unidad.agregarEstado(new EstadoAtormentando(posicionCentral, mapa, rangoAoE));
	}

}
