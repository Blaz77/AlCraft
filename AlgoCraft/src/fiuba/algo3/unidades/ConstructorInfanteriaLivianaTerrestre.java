package fiuba.algo3.unidades;

import fiuba.algo3.atributos.AtributosJugador;

public class ConstructorInfanteriaLivianaTerrestre extends ConstructorUnidadAtaque {

	@Override
	public void setAtributos() {
		this.atributos = 
				((AtributosJugador)this.entrenador.getPropietario().getAtributos())
				.getInfanteriaLivianaTerrestre();		
	}

}
