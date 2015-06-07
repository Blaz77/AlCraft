package fiuba.algo3.juego;

import java.util.ArrayList;

import fiuba.algo3.mapa.Mapa;

public class Juego {
	// Algunos atributos posibles:
	// Turnos
	// Mapa
	// Jugadores (en lista? asi jugar con >2 es posible)
	// Referencia a la interfaz? no creo...
	// cosas extra como jugador actual, constantes como turnos maximos si los hay
	// 
	
	private int turnos = 0;
	//int turnosMAX; // si los hay
	Mapa mapa;
	ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	
	// Metodos:
	
	//	- Juego(opciones) 	->		inicializa el juego pero sin jugadores (mas facil
	//								agregarlos despues).
	
	public Juego(int cantidadBases){ 
		// (Opciones opciones) {
		// TODO agregar clase opciones, para encapsular todas las opciones
		// seleccionadas en los menus previos a jugar (si los hay)
		mapa = new Mapa(cantidadBases);
		
		
		
	}
	
	//	- pasarTurno() 		->		ordena a todas las unidades/edificios que realicen
	//								su accion de cambio de turno.
	//	- eliminarJugador() ->		remueve a un jugador del ciclo de turnos si murio
	
	//	- hayGanador() 		->		Se fija si queda un jugador vivo nomas.
	
	
	//	- de Delegacion (podrian no estar y q de arriba accedan a los objetos directamente):
	
	//		· ataque(pos1, pos2)
	
	//		· moverse(pos1, pos2)
	
	//		· construir(jugador, edif, pos)
}
