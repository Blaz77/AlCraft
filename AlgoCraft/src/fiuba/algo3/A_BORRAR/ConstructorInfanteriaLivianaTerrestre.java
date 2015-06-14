package fiuba.algo3.ocupantes.unidades.constructores;

import fiuba.algo3.atributos.jugador.AtributosJugador;

public class ConstructorInfanteriaLivianaTerrestre extends ConstructorUnidadAtaque {

	@Override
	public void setAtributos() {
		this.atributos = 
				((AtributosJugador)this.entrenador.getPropietario().getAtributos())
				.getInfanteriaLivianaTerrestre();		
	}

}
