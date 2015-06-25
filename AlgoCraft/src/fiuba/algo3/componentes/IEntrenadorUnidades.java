package fiuba.algo3.componentes;

import java.util.ArrayList;

import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.unidades.constructores.Constructor;

public interface IEntrenadorUnidades {
	
	public boolean puedeEntrenarUnidades();
	
	public ArrayList<Constructor> getUnidadesEntrenables();
	
	public void entrenar(AtributosUnidad atributosUnidad);

	public void liberarUnidad(AtributosUnidad atributosUnidad);
	
	public void proximoEntrenamiento();

}

