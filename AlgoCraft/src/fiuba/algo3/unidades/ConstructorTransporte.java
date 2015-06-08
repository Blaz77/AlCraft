package fiuba.algo3.unidades;

import fiuba.algo3.atributos.AtributosJugador;

public class ConstructorTransporte extends ConstructorUnidadTransporte {
	
	@Override
	public void setAtributos() {
		this.atributos = 
				((AtributosJugador)this.entrenador.getPropietario().getAtributos())
				.getTransporte();		
	}

}
