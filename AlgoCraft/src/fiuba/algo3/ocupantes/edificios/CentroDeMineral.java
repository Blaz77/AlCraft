package fiuba.algo3.ocupantes.edificios;

import fiuba.algo3.atributos.jugador.AtributosJugador;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;


public class CentroDeMineral extends EdificioRecolectorMineral {
	
	final static int costoMineral = 50;
	
	public CentroDeMineral(Jugador propietario, Posicion posicion) {
		super(propietario, posicion, 
				((AtributosJugador)propietario.getAtributos())
				.getRecolectorMineral());
		this.vida = new Vida(0, ((AtributosJugador)propietario.getAtributos())
				.getRecolectorMineral());
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
