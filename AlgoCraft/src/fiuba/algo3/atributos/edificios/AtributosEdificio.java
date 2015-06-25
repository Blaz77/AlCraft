package fiuba.algo3.atributos.edificios;

import fiuba.algo3.atributos.AtributosObjetoVivo;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.componentes.EntrenadorUnidadesNull;
import fiuba.algo3.componentes.IEntrenadorUnidades;
import fiuba.algo3.ocupantes.Tipo;

public abstract class AtributosEdificio extends AtributosObjetoVivo {
	
	//Por ahora todos metodos con el comportamiento mas usual.
	//Quizas hacer todo abstracto despues (no sabria bien porque)

	public IEntrenadorUnidades getEntrenadorUnidades(Edificio portador){
		return new EntrenadorUnidadesNull();
	}
	
	public Tipo getEdificioRequerido() {
		return null;
	}
}
