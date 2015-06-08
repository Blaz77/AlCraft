package fiuba.algo3.unidades;

import fiuba.algo3.atributos.AtributosJugador;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class ConstructorNaveDeCiencia extends Constructor {
	
	@Override
	public void liberarUnidad(Jugador j, Posicion posicion) {
		new NaveDeCiencia(j, posicion);
		//new UnidadAtaque(j, pos, this.atributos)
	}
	
	@Override
	public void setAtributos() {
		this.atributos = 
				((AtributosJugador)this.entrenador.getPropietario().getAtributos()).getNaveDeCiencia();		
	}
}
