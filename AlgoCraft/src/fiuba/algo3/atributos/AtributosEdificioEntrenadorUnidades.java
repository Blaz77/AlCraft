package fiuba.algo3.atributos;

import java.util.ArrayList;

import fiuba.algo3.unidades.Constructor;

public abstract class AtributosEdificioEntrenadorUnidades extends AtributosEdificio {

	//Quizas el entrenamiento actual pasa a ser una lista limitada por una:
	//int cantDeEntrenamientosSimultaneos
	
	ArrayList<Constructor> unidadesEntrenables;
	
	public AtributosEdificioEntrenadorUnidades() {
		this.unidadesEntrenables = new ArrayList<Constructor>();
	}
	
	@Override
	public boolean puedeEntrenarUnidades() {
		return true;
	}
	
	public ArrayList<Constructor> getUnidadesEntrenables(){
		return new ArrayList<Constructor>(unidadesEntrenables);
	}

}
