package fiuba.algo3.A_BORRAR;

import java.util.ArrayList;

import fiuba.algo3.atributos.AtributosEntrenadorUnidades;
import fiuba.algo3.componentes.EntrenadorUnidades;
import fiuba.algo3.ocupantes.unidades.constructores.Constructor;

public abstract class AtributosEdificioEntrenadorUnidades extends AtributosEdificio {

	//Quizas el entrenamiento actual pasa a ser una lista limitada por una:
	//int cantDeEntrenamientosSimultaneos
	
protected AtributosEntrenadorUnidades entrenador;
	
	@Override
	public boolean puedeEntrenarUnidades() {
		return true;
	}
	
	public ArrayList<Constructor> getUnidadesEntrenables(){
		return entrenador.getUnidadesEntrenables();
	}

	public EntrenadorUnidades getEntrenadorUnidades(){
		return new EntrenadorUnidades(this.entrenador);
	}
}
