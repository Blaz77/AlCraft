//TODO Clase sin usar!

package fiuba.algo3.A_BORRAR;

import java.util.ArrayList;

import fiuba.algo3.componentes.EntrenadorUnidades;
import fiuba.algo3.ocupantes.unidades.constructores.Constructor;

public class EntrenadorUnidadesNull extends EntrenadorUnidades {

	public EntrenadorUnidadesNull(){
		super(null);
	}
	
	public void setAnterior(Componente componenteAnterior) {
		// TODO Auto-generated method stub
	}

	public boolean puedeEntrenar() {
		return false;
	}

	public ArrayList<Constructor> getUnidadesEntrenables() {
		return null;
		//throw Exception;
	}

}
