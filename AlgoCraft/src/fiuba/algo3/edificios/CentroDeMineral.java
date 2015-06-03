package fiuba.algo3.edificios;

import fiuba.algo3.tp_final.Jugador;


public class CentroDeMineral extends Edificio{
	
	public CentroDeMineral(String nombre, Jugador propietario, int x, int y) {
		super(nombre, propietario, x, y);
		// TODO Auto-generated constructor stub
	}

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
