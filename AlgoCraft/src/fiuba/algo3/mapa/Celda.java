package fiuba.algo3.mapa;

import fiuba.algo3.edificios.Edificable;
import fiuba.algo3.edificios.Edificio;

public class Celda { //quizas heredar de celda y armar Tierra/Espacio extends Celda

	private Posicion posicion;
	private Recurso recurso = null; //RecursoNULL
	private Terreno terreno;
	private Edificio edificio = null; //EdificioNULL

	public Celda(int x, int y, Terreno terreno) {
		this.posicion = new Posicion(x, y);
		this.terreno = terreno;
	}
	
	public int distancia(Celda other) {
		return posicion.distancia(other.getPosicion());
	}

	public Posicion getPosicion() {
		return this.posicion;
	}
	public int getX() {
		return this.posicion.getX();
	}

	public int getY() {
		return this.posicion.getY();
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
