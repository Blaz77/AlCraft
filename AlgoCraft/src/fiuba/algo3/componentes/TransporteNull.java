package fiuba.algo3.componentes;

import java.util.ArrayList;

import fiuba.algo3.ocupantes.unidades.Pasajero;
import fiuba.algo3.ocupantes.unidades.Unidad;

public class TransporteNull implements ITransporte {

	public boolean puedeAlmacenar() {
		return false;
	}

	public boolean puedeAlmacenarA(Unidad unidad) {
		return false;
	}

	public void almacenarA(Unidad unidad) {
		// throw ALGO?
	}

	public ArrayList<Pasajero> getUnidadesAlmacenadas() {
		return null;
	}

	public void liberarUnidad(Unidad unidadAlmacenada) {
		// throw ALGO?
	}

}
