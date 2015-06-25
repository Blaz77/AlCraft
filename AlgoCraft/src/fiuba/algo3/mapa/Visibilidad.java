package fiuba.algo3.mapa;

import java.util.HashSet;

import fiuba.algo3.ocupantes.unidades.Unidad;

// los 3 estados de iluminacion de una celda son:
//				verTerreno:	verOcupante:	implementacion:
// VISIBLE		true		true			si el conjunto incluido de unidades q la ven es no vacio.
// NO_VISIBLE	true		false			si el conjunto es vacio ahora pero no antes (c/ un booleano)
// DESCONOCIDO	false		false			si el conjunto siempre fue vacio (c/ el mismo booleano)
public class Visibilidad {

	private boolean fueVista;
	private int observadores;

	public Visibilidad(){
		this.fueVista = false;
		this.observadores = 0;
	}
	
	public boolean verTerreno(){
		return fueVista;
	}
	
	public boolean verOcupante(){
		return observadores > 0;
	}
	
	public void add(){
		fueVista = true;
		observadores++;
	}
	
	public void remove(){
		observadores--;
	}
	
	public void forzarIluminacion() {
		add();
	}
	
}