package fiuba.algo3.mapa;

import fiuba.algo3.edificios.Edificable;
import fiuba.algo3.edificios.Edificio;

public class Celda { //quizas heredar de celda y armar Tierra/Espacio extends Celda

	private Posicion posicion;
	private Recurso recurso = new NoRecurso();
	private Terreno terreno;
	private Edificio edificio = null; //EdificioNULL

	public Celda(int x, int y, Terreno terreno) {
		posicion = new Posicion(x, y);
		this.terreno = terreno;
	}
	
	public int distancia(Celda other) {
		return posicion.distancia(other.posicion);
	}

	/*
	public double distancia(Celda otroPunto) {
		return Math.sqrt( Math.pow(this.x - otroPunto.x, 2) + Math.pow(this.y - otroPunto.y, 2) );
	}*/

	public int getX() {
		return posicion.getX();
	}

	public int getY() {
		return posicion.getY();
	}
	
	public Posicion getPosicion(){
		return posicion;
	}
		
	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}	
	
	public Recurso getRecurso() {
		return this.recurso;
	}

	//O posicionar directo!
	//O preguntar si puede posicionar y luego posicionar
	public void posicionar(Edificable edif){
		if (terreno.puedeEdificar(edif) && recurso.puedeEdificar(edif) && edificio != null){
			this.edificio = (Edificio)edif; //FEO, despues arreglar!
			
		}
	}
	
	public Terreno getTerreno(){
		return this.terreno;
	}

	
}
