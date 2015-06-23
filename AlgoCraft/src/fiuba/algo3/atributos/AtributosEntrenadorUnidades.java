package fiuba.algo3.atributos;

import java.util.Arrays;
import java.util.List;

import fiuba.algo3.atributos.unidades.AtributosUnidad;

public class AtributosEntrenadorUnidades {
	
	private int maxEntrenamientosSimultaneos;
	private AtributosUnidad[] unidadesEntrenables;
	
	public AtributosEntrenadorUnidades(int maxEntrSimult, AtributosUnidad[] entrenables){
		this.unidadesEntrenables = entrenables;
		this.maxEntrenamientosSimultaneos = maxEntrSimult;
	}
	
	public int getMaxEntrenamientosSimultaneos() {
		return this.maxEntrenamientosSimultaneos;
	}
	
	public List<AtributosUnidad> getUnidadesEntrenables(){
		return Arrays.asList(unidadesEntrenables);
	}

}
