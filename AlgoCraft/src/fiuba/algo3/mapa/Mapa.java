package fiuba.algo3.mapa;

import java.util.ArrayList;

import fiuba.algo3.ocupantes.Ocupante;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.terreno.Terreno;

public interface Mapa {
	
	public int distancia(Posicion pos1, Posicion pos2);
	
	public Posicion getPosicionInicial();
	
	/* Devuelve el ancho del mapa. */
	public int ancho();
	
	/* Devuelve el alto del mapa. */
	public int alto();

	public boolean celdaValida(Posicion posicion);
	
	//una opcion para que devuelva booleano
	public boolean puedeOcupar(Ocupante ocupante, Posicion posicion);
	
	//otra opcion para que lanze excepciones en caso de error, sino nada
	public void verificarOcupacion(Ocupante ocupante, Posicion posicion);
	
	public void setOcupante(Ocupante ocupante, Posicion posicion);
	
	public Posicion setOcupanteEnCercania(Ocupante ocupante, Posicion posicion);
	
	public Ocupante getOcupante(Posicion posicion);
	
	public Ocupante removerOcupante(Posicion posicion);
	
	public Ocupante reemplazar(Posicion posicion, Ocupante reemplazante);

	public Terreno getTerreno(Posicion posicion);
	
	public void mover(Unidad /*ObjetoVivo*/ unidad, Posicion destino);
	 
	public ArrayList<Unidad> getUnidadesEnRango(Posicion centro, int distancia);

}
