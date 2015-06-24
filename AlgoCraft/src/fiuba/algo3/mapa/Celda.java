package fiuba.algo3.mapa;

import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.ocupantes.CeldaVacia;
import fiuba.algo3.ocupantes.Ocupante;
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

	private Ocupante ocupante;
	private Terreno terreno;
	
	public Celda(Posicion posicion, Terreno terreno) {
		this.terreno = terreno;
		this.ocupante = new CeldaVacia(posicion);
	}
	
	public int distancia(Celda other) {
		return getPosicion().distancia(other.getPosicion());
	}

	public int getX() {
		return getPosicion().getX();
	}

	public int getY() {
		return getPosicion().getY();
	}
	
	public Posicion getPosicion(){
		return ocupante.getPosicion();
	}
	
	public boolean puedeSerOcupada(Ocupante ocupante) {
		return (this.terreno.puedeSerOcupada(ocupante) &&
				this.ocupante.puedeCambiarsePor(ocupante));
	}
	
	public void verificarOcupacion(Ocupante ocupante){
		if (!this.terreno.puedeSerOcupada(ocupante))
			throw new TerrenoInadecuado(terreno);
		if (!this.ocupante.puedeCambiarsePor(ocupante))
			this.ocupante.lanzarExcepcionDeCambio();
	}
		
	public void setOcupante(Ocupante ocupante) {
		this.verificarOcupacion(ocupante);
		this.ocupante = ocupante;
	}
	
	public Ocupante getOcupante() {
		return this.ocupante;
	}
	
	public Ocupante removerOcupante(){
		Ocupante oc = this.ocupante;
		this.ocupante = new CeldaVacia(this.ocupante.getPosicion());
		return oc;
	}
	
	public Ocupante reemplazar(Ocupante reemplazante){
		Ocupante oc = this.ocupante;
		this.ocupante = reemplazante;
		return oc;
	}

	public Terreno getTerreno(){
		return this.terreno;
	}

	public void setTerreno(Terreno terreno) {
		this.terreno = terreno;
		
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
	/*
	public void posicionar(Edificable edif){
		// TODO: crear una excepcion valida para esta situacion (dejenlo asi
		// por ahora, sino despues tenemos 200 excepciones todas iguales.)
		if (!terreno.puedeEdificar(edif))
			throw new RuntimeException();
		this.edificio = (Edificio)edif; //FEO, despues arreglar! (Edificable edificio? :D)
		
	}*/


	
}
