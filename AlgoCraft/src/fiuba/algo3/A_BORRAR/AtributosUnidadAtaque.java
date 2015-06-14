package fiuba.algo3.A_BORRAR;

import fiuba.algo3.atributos.AtributosAtaque;
import fiuba.algo3.componentes.Ataque;
import fiuba.algo3.componentes.IAtaque;

public abstract class AtributosUnidadAtaque extends AtributosUnidad {

	protected AtributosAtaque ataque;
	
	public IAtaque getAtaque(){
		return new Ataque(this.ataque);
	}

}
