package fiuba.algo3.unidades;

import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.juego.Atacable;


/* 
 * 
 * Reflection para mandarle a la interfaz los metodos que ésta pueda ejecutar.
 * 
 * 
 * */
public class Unidad implements Atacable{

	private int vida;
	private int x;
	private int y;

	
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
	public void moverse(){
		
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
