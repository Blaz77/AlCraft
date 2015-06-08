package fiuba.algo3.unidades;

import fiuba.algo3.atributos.AtributosJugador;

public class ConstructorInfanteriaPesadaTerrestre extends ConstructorUnidadAtaque {

	@Override
	public void setAtributos() {
		this.atributos = 
				((AtributosJugador)this.entrenador.getPropietario().getAtributos())
				.getInfanteriaPesadaTerrestre();		
	}

}
