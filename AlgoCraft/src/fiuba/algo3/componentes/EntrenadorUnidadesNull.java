package fiuba.algo3.componentes;

import java.util.ArrayList;

import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.unidades.constructores.Constructor;

public class EntrenadorUnidadesNull implements IEntrenadorUnidades {

	public boolean puedeEntrenarUnidades() {
		return false;
	}

	public ArrayList<Constructor> getUnidadesEntrenables() {
		return null;
	}

	public boolean puedeEntrenar(AtributosUnidad atributosUnidad) {
		return false;
	}

	public void entrenar(AtributosUnidad atributosUnidad) {}

	public void liberarUnidad(AtributosUnidad atributosUnidad) {}
	
	public void proximoEntrenamiento() {}

}
