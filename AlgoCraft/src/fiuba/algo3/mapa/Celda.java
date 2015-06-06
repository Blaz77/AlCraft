package fiuba.algo3.mapa;

import fiuba.algo3.juego.Ocupante;
import fiuba.algo3.terreno.Terreno;


/*
 * Una Celda es basicamente un nodo del mapa, que contiene
 * toda la info correspondiente a un casillero de la grilla
 * que basicamente compone el juego.
 * Naturalmente, tiene poco o ningun comportamiento.
 
 * Recuerden que, por ahora al menos, sólo el mapa debe tener
 * acceso directo a esta clase.
 */
public class Celda { //quizas heredar de celda y armar Tierra/Espacio extends Celda

	private Posicion posicion; // Es de utilidad esto aca? XD. Si las unidades/edificios/recursos lo tienen.
	private Terreno terreno;
	private Ocupante ocupante = new CeldaVacia();
	
	public Celda(Posicion posicion, Terreno terreno) {
		this.posicion = posicion;
		this.terreno = terreno;
	}
	
	public int distancia(Celda other) {
		return posicion.distancia(other.posicion);
	}

	public int getX() {
		return posicion.getX();
	}

	public int getY() {
		return posicion.getY();
	}
	
	public Posicion getPosicion(){
		return posicion;
	}
		
	public void setOcupante(Ocupante ocupante) {
		this.ocupante = ocupante;
	}	
	
	public Ocupante getOcupante() {
		return this.ocupante;
	}
	
	public Terreno getTerreno(){
		return this.terreno;
	}

	//O posicionar directo!
	//O preguntar si puede posicionar y luego posicionar
	
	// Esto me huele mal... xq la celda tiene que preguntarle al recurso si puede edificar o no?
	// Consideren separar esto por lo siguiente:
	
	// - Cuando uno edifica en estos juegos, si no tiene recursos/edificios previos requeridos, la
	// opcion de edificar no aparece o esta bloqueada / en rojo.
	// Sin embargo, si SI puede, entonces se le pide al usuario donde quiere ubicarlo.. y AHI es donde
	// preguntarle al terreno importa, no antes.
	// Por ende, mi idea de una secuencia correcta de esto seria:
	
	// * Interfaz le pregunta al mapa si puede edificar edif en (x,y), 
	// * Mapa le pregunta a la celda.
	// * Celda al terreno.
	
	// Y para la construccion en si:
	// * De arriba se construye el edificio (ya con posicion incluida)
	// * Le ordenan al mapa ubicarla en (x,y)
	// * Mapa delega en celda.
	// * Celda lo coloca, sin preguntar. En todo caso, que explote todo si
	// el terreno era incompatible.
	
	/*public void posicionar(Edificable edif){
		// TODO: crear una excepcion valida para esta situacion (dejenlo asi
		// por ahora, sino despues tenemos 200 excepciones todas iguales.)
		if (!terreno.puedeEdificar(edif))
			throw new RuntimeException();
		this.edificio = (Edificio)edif; //FEO, despues arreglar! (Edificable edificio? :D)
		
	}*/
	
}
