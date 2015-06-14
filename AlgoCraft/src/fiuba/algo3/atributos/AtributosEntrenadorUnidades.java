package fiuba.algo3.atributos;

import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.atributos.unidades.AtributosUnidad;

public class AtributosEntrenadorUnidades {
	
	private int maxEntrenamientosSimultaneos;
	private ArrayList<AtributosUnidad> unidadesEntrenables;
	
	public AtributosEntrenadorUnidades(int maxEntrSimult, List<AtributosUnidad> entrenables){
		this.unidadesEntrenables = new ArrayList<AtributosUnidad>(entrenables);
		this.maxEntrenamientosSimultaneos = maxEntrSimult;
	}
	
	public AtributosEntrenadorUnidades(int maxEntrSimult) {
		this.unidadesEntrenables = new ArrayList<AtributosUnidad>();
		this.maxEntrenamientosSimultaneos = maxEntrSimult;
	}
	
	public int getMaxEntrenamientosSimultaneos() {
		return this.maxEntrenamientosSimultaneos;
	}
	
	public void agregarEntrenable(AtributosUnidad unidad){
		this.unidadesEntrenables.add(unidad);
	}
	
	public ArrayList<AtributosUnidad> getUnidadesEntrenables(){
		return new ArrayList<AtributosUnidad>(unidadesEntrenables);
	}

}
