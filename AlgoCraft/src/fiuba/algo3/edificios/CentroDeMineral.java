package fiuba.algo3.edificios;

import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;


public class CentroDeMineral extends EdificioRecolectorMineral {
	
	public CentroDeMineral(Jugador propietario, Posicion posicion) {
		super(propietario, posicion, 10);
		this.vida = new Vida(0, 500);
		this.nombre = "Centro de Mineral".intern();
	}
	
	
	// no ver esto de abajo
	public boolean enTierra(){
		return true;
	}
	
	public boolean enEspacio(){
		return false;
	}
	
	public boolean sobreRecurso(){
		return true;
	}
	
	public boolean sobreMineral(){
		return true;
	}
	
	public boolean sobreGasVespeno(){
		return false;
	}

}
