package fiuba.algo3.edificios;

import fiuba.algo3.atributos.AtributosEdificio;
import fiuba.algo3.componentes.EstadoConstruyendoEdificio;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public abstract class Construccion extends Edificio { //EdificioEnConstruccion

	// Luego habria que buscar la forma de que la construccion
	// haga el new Edificio() y solo recibir los atributos por
	// parametro.
	protected Edificio edificio = null;
	
	public Construccion(Jugador propietario, Posicion posicion, AtributosEdificio atributos) {
		super(propietario, posicion);
		this.atributos = atributos;
		this.vida = new Vida(0,atributos); //temp
		this.agregarEstado(new EstadoConstruyendoEdificio());
	}
	
	public int getTurnosConstruccion(){
		return this.atributos.getTurnosConstruccion();
	}
	
	//aca va el new del edificio especifico
	abstract protected Edificio liberarEdificio();
	
	public void construccionFinalizada(){
		this.destruir();
		this.edificio = this.liberarEdificio();
		//posicionar en el mapa
		//dar al jugador (opcional)(ver)
	}
	
	//esto es feo, pero seria mas que nada para los TESTs
	public Edificio getEdificioTerminado(){
		if (this.edificio != null) return this.edificio;
		else throw new RuntimeException();
	}
	

}
