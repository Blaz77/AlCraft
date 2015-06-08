package fiuba.algo3.unidades;

import fiuba.algo3.atributos.AtributosTerran;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class ConstructorNaveDeTransporte extends Constructor {
	
	@Override
	public void liberarUnidad(Jugador j, Posicion posicion) {
		new NaveDeTransporte(j, posicion);
		//new UnidadAtaque(j, pos, this.atributos)
	}
	
	@Override
	public void setAtributos() {
		this.atributos = 
				((AtributosTerran)this.entrenador.getPropietario().getAtributos()).getNaveDeTransporte();		
	}

}
