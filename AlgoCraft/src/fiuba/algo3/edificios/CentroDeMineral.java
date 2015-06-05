package fiuba.algo3.edificios;

import fiuba.algo3.componentes.VidaSinEscudo;
import fiuba.algo3.juego.Jugador;


public class CentroDeMineral extends EdificioRecolectorMineral {
	
	public CentroDeMineral(Jugador propietario, int x, int y) {
		super(propietario, x, y, 10);
		this.vida = new VidaSinEscudo(500);
		this.nombre = "Centro de Mineral";
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
