package fiuba.algo3.atributos.unidades;

import fiuba.algo3.atributos.AtributosAtaque;
import fiuba.algo3.componentes.Ataque;

public abstract class AtributosUnidadAtaque extends AtributosUnidad {

	protected AtributosAtaque ataque;
	
	public Ataque getAtaque(){
		return new Ataque(this.ataque);
	}
	
	@Override
	public boolean puedeAtacar(){
		return true;
	}
	

}
