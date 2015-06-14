package fiuba.algo3.atributos.edificios;

import fiuba.algo3.atributos.AtributosIncrementadorPoblacion;
import fiuba.algo3.atributos.AtributosObjetoVivo;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.componentes.VidaConEscudo;

public abstract class AtributosEdificio extends AtributosObjetoVivo {
	
	//Por ahora todos metodos con el comportamiento mas usual.
	//Quizas hacer todo abstracto despues (no sabria bien porque)
	
	public boolean puedeEntrenarUnidades(){
		return false;
	}

	@Override
	public IVida getVida() {
		return new Vida(0, this.vida);
	}
}
