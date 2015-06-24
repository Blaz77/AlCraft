package fiuba.algo3.juego;

import java.util.ArrayList;
import java.util.Iterator;

import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.MapaProxy;
import fiuba.algo3.mapa.MapaReal;
import fiuba.algo3.raza.TipoRaza;

public class Juego implements Iterable<Jugador>, Iterator<Jugador>{
	// Algunos atributos posibles:
	// Turnos
	// Mapa
	// Jugadores (en lista? asi jugar con >2 es posible)
	// Referencia a la interfaz? no creo...
	// cosas extra como jugador actual, constantes como turnos maximos si los hay
	// 
	
	
	private int turnos = 0;
	private int indiceJugadorActual = 0;
	//int turnosMAX; // si los hay
	private MapaReal mapa;
	ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	ArrayList<Jugador> jugadoresVivos = new ArrayList<Jugador>();
	// Metodos:
	
	/* Inicializa el juego pero sin jugadores (mas facil 
	 * agregarlos despues).*/
	
	public Juego(Opciones opciones){ // (Opciones opciones) {
		mapa = new MapaReal(opciones.getCantidadBases());
		String nombreJugador;
		TipoRaza razaJugador;
		Color colorJugador;
		Jugador jugadorActual;
		for (int n = 1; n <= opciones.getCantidadJugadores(); n++){
			nombreJugador = opciones.getNombreJugador(n);
			colorJugador = opciones.getColorJugador(n);
			razaJugador = opciones.getRazaJugador(n);
			Mapa mapaJugador = new MapaProxy(mapa, mapa.obtenerBaseDeJugador(n).getPosicion()); // (if opciones.fogOfWar? else: proxy = mapa? :D
			jugadorActual = new Jugador(nombreJugador, colorJugador, razaJugador, mapaJugador);
			jugadores.add(jugadorActual);
			jugadoresVivos.add(jugadorActual);
			//jugadores.get(n-1).setPosicionInicial(mapa.obtenerBaseDeJugador(n).getPosicion());
		}
	}
		
	// Se fija si queda un jugador vivo nomas.
	public boolean hayGanador() {
		return (jugadores.size() == 1);
	}
	
	/**********************************************/
	/**             SETTERS, GETTERS             **/
	/**********************************************/
	
	public int getTurnos(){
		return turnos;
	}
	
	//TODO: :D
	public Jugador getJugadorActual(){
		return jugadoresVivos.get(indiceJugadorActual);
	}

	// Elimina a un jugador del juego!
	public void eliminarJugador(Jugador jugador){
		//TODO: Excepciones x errores x aqui
		jugadoresVivos.remove(jugador);
		indiceJugadorActual = indiceJugadorActual % jugadores.size();
		
	}		
	
	/* Ordena a todas las unidades/edificios del jugador
	 * que realicen su accion de cambio de turno.*/
	public void nextJugadorActual() {
		// cambiamo el orden de estas 2 lineas y cambia cuando
		// ocurre el cambio de turno. Tiene mas sentido asi creo
		getJugadorActual().pasarTurno();
		indiceJugadorActual = (indiceJugadorActual + 1) % jugadores.size();
		if (indiceJugadorActual == 0) turnos ++;
	}
	
	public Jugador getJugador(int i) {
		return jugadores.get(i - 1);
		
	}
		
	/**********************************************/
	/**            ITERATOR (SIN CICLO)          **/
	/**********************************************/
	
	private int iter_nextPlayer = 1;
	public Iterator<Jugador> iterator() {
		return this;
	}

	public boolean hasNext() {
		return iter_nextPlayer <= jugadores.size();
	}

	public Jugador next() {
		Jugador jugador =  getJugador(iter_nextPlayer);
		iter_nextPlayer++;
		return jugador;
	}
	
	//	- de Delegacion (podrian no estar y q de arriba accedan a los objetos directamente):
	
	//		· ataque(pos1, pos2)
	
	//		· moverse(pos1, pos2)
	
	//		· construir(jugador, edif, pos)
}
