package fiuba.algo3.A_BORRAR;

import fiuba.algo3.atributos.jugador.AtributosJugador;

public class ConstructorInfanteriaPesadaAerea extends ConstructorUnidadAtaque {

	@Override
	public void setAtributos() {
		this.atributos = 
				((AtributosJugador)this.entrenador.getPropietario().getAtributos())
				.getInfanteriaPesadaArea();
	}


}
