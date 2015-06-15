package fiuba.algo3.mapa;

import java.util.ArrayList;

import fiuba.algo3.ocupantes.Ocupante;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.terreno.Terreno;

public interface Mapa {
	
	public int distancia(Posicion pos1, Posicion pos2);
	
	//public Celda obtenerBaseDeJugador(int numeroJugador);
	
	/* Devuelve el ancho del mapa. */
	public int ancho();
	
	/* Devuelve el alto del mapa. */
	public int alto();
	
	//public ArrayList<Celda> getBases();
	
	// Aca prohibo celdas negativas, no en posicion, para no perder la chance
	// de "sumar" para moverme en direcciones negativas 
	public boolean celdaValida(Posicion posicion);
	
	//una opcion para que devuelva booleano
	public boolean puedeOcupar(Ocupante ocupante, Posicion posicion);
	
	//otra opcion para que lanze excepciones en caso de error, sino nada
	public void verificarOcupacion(Ocupante ocupante, Posicion posicion);
	
	public void setOcupante(Ocupante ocupante, Posicion posicion);
	
	public Ocupante getOcupante(Posicion posicion);

	public Terreno getTerreno(Posicion posicion);
	
	public void mover(Unidad /*ObjetoVivo*/ unidad, Posicion destino);
	 
	public ArrayList<Unidad> getUnidadesEnRango(Posicion centro, int distancia);

}
