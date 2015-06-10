package fiuba.algo3.atributos.edificios;

import fiuba.algo3.atributos.AtributosObjetoVivo;

public abstract class AtributosEdificio extends AtributosObjetoVivo {
	
	//Por ahora todos metodos con el comportamiento mas usual.
	//Quizas hacer todo abstracto despues (no sabria bien porque)
	
	public boolean puedeEntrenarUnidades(){
		return false;
	}

}
