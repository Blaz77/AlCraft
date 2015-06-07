package fiuba.algo3.unidades;

import fiuba.algo3.atributos.AtributosTerran;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class Golliat extends UnidadAtaque {
	
	public Golliat(Jugador propietario, Posicion posicion){
		super(propietario, posicion, 
				((AtributosTerran)propietario.getAtributos()).getGolliat());
		this.nombre = "Golliat".intern();
	}
}
