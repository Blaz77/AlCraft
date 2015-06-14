package fiuba.algo3.atributos.edificios;

import java.util.ArrayList;

import fiuba.algo3.atributos.AtributosEntrenadorUnidades;
import fiuba.algo3.atributos.AtributosIncrementadorPoblacion;
import fiuba.algo3.atributos.AtributosObjetoVivo;
import fiuba.algo3.componentes.EntrenadorUnidadesNull;
import fiuba.algo3.componentes.IEntrenadorUnidades;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.componentes.VidaConEscudo;
import fiuba.algo3.ocupantes.unidades.constructores.Constructor;

public abstract class AtributosEdificio extends AtributosObjetoVivo {
	
	//Por ahora todos metodos con el comportamiento mas usual.
	//Quizas hacer todo abstracto despues (no sabria bien porque)

	public IEntrenadorUnidades getEntrenadorUnidades(){
		return new EntrenadorUnidadesNull();
	}

	@Override
	public IVida getVida() {
		return new Vida(0, this.vida);
	}
}
