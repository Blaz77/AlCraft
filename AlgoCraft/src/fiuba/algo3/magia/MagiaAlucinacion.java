package fiuba.algo3.magia;

import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.atributos.unidades.AtributosUnidadAlucinada;
import fiuba.algo3.excepciones.FueraDelRangoPermitido;
import fiuba.algo3.excepciones.NoEsUnAliado;
import fiuba.algo3.ocupantes.unidades.Unidad;

public class MagiaAlucinacion implements MagiaAUnidad {

	private Unidad unidad;
	private int costoDeEjecutar;
	
	public MagiaAlucinacion() {
		this.costoDeEjecutar = 100;
	}
	
	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;		
	}
	
	public boolean puedeEjecutar(Unidad otraUnidad) {
		return (!this.unidad.esEnemigoDe(otraUnidad) &&
			this.unidad.estaEnRangoDeMagia(otraUnidad.getPosicion()) &&
			this.unidad.puedeDisminuirEnergia(this.costoDeEjecutar));
	}

	public void ejecutar(Unidad aliado) {
		//chequeo si es enemiga (no se si es necesario, pero bueno)
		if (this.unidad.esEnemigoDe(aliado)) throw new NoEsUnAliado();
		if (!this.unidad.estaEnRangoDeMagia(aliado.getPosicion()))
			throw new FueraDelRangoPermitido();
		this.unidad.disminuirEnergia(this.costoDeEjecutar);
		for (int i = 0; i < 2; i++){
			Unidad aluc = new Unidad(aliado.getPropietario(), aliado.getPosicion(),
							new AtributosUnidadAlucinada((AtributosUnidad)aliado.getAtributos()));
			aluc.setPosicion(this.unidad.getPropietario().getMapa().
					setOcupanteEnCercania(aluc, aliado.getPosicion()));
			this.unidad.getPropietario().agregarUnidad(aluc);
		}
	}


}
