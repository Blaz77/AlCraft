package fiuba.algo3.edificios;

import fiuba.algo3.atributos.AtributosTerran;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;


public class CentroDeMineral extends EdificioRecolectorMineral {
	
	final static int costoMineral = 50;
	
	public CentroDeMineral(Jugador propietario, Posicion posicion) {
		super(propietario, posicion, 
				((AtributosTerran)propietario.getAtributos()).getCentroDeMineral());
		this.vida = new Vida(0, ((AtributosTerran)propietario.getAtributos()).getCentroDeMineral());
		this.nombre = "Centro de Mineral".intern();
	}
	
	@Override
	public int getCostoMineral() {
		return 50;
	}


	@Override
	public int getCostoGas() {
		return 0;
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
