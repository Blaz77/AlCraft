package fiuba.algo3.unidades;

import fiuba.algo3.atributos.AtributosTerran;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class NaveDeCiencia extends Unidad {

	public NaveDeCiencia(Jugador propietario, Posicion posicion){
		super(propietario, posicion, 
				((AtributosTerran)propietario.getAtributos()).getNaveDeCiencia());
		this.nombre = "Nave de Ciencia".intern();
	}
}
