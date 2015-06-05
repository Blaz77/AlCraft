package fiuba.algo3.unidades;

import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.juego.Atacable;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.edificios.ObjetoVivo;


/* 
 * 
 * Reflection para mandarle a la interfaz los metodos que ésta pueda ejecutar.
 * 
 * 
 * */
public abstract class Unidad extends ObjetoVivo implements Atacable{
	
	public Unidad(Jugador propietario, Posicion posicion){
		super(propietario, posicion);
		//POSICIONAR EN ESTE CONSTRUCTOR:
		// -UNIDAD EN EL MAPA -> mapa.addUnidad(this, POSicion)
		propietario.agregarUnidad(this);
		
		//O HACER EN LOS CONSTRUCTORES!?!??!
	}
	
	public void pasarTurno(){
		
	}

	
	/*
	 * Metodos para reflection para la barra de acciones
	 * 
	 * Atacar / Magia
	 * Moverse
	 * Descargar Unidades alojadas
	 * Meterse dentro de transporte
	 * 
	 * 
	 * Si usamos reflection, los demas metodos van encapsulados
	 * 
	 * 
	 * 
	 * */
	
	// Este podria ir a una interface con edificios.
	public int getVida() {
		return 0;
	}

	// Este podria ir a una interface con edificios.
	public int getVidaMaxima() {
		return 0;
	}
	
	// Este podria ir a una interface con edificios.
	public void bajarVida(int puntos){
		
	}
	
	// Manejar veneno. Alguna interfaz para esto
	public void pasoDeTurno() {
		return;
	}
	
	public int movimiento() {
		return 0;
	}
	
	// Se mueve el? o acata ordenes?
	public void moverA(Posicion posicion){
		//this.movimiento.moverA(posicion);
		//this.vision
	}
	
	public boolean puedeAtacar(){
		return true;
	}
	
	// Este iria adentro de un strategy, xq hay unidades de magia q no atacan.
	public void atacarA(Unidad enemigo){
		
		
	}

	public void atacarA(Edificio enemigo){
		
	}

}
