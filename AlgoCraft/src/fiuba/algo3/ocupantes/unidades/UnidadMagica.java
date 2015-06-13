package fiuba.algo3.ocupantes.unidades;

import fiuba.algo3.atributos.unidades.AtributosUnidadMagica;
import fiuba.algo3.componentes.Energia;
import fiuba.algo3.excepciones.EnergiaInsuficiente;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.magia.MagiaAUnidad;
import fiuba.algo3.magia.MagiaDeAreaDeEfecto;
import fiuba.algo3.mapa.Posicion;

public class UnidadMagica extends Unidad {

	protected Energia energia;
	protected MagiaDeAreaDeEfecto magiaAoE;
	protected MagiaAUnidad magiaUnidad;
	
	public UnidadMagica(Jugador propietario, Posicion posicion,
			AtributosUnidadMagica atributos) {
		super(propietario, posicion, atributos);
		this.energia = atributos.getEnergia();
		this.magiaAoE = atributos.getMagiaDeAreaDeEfecto();
		this.magiaUnidad = atributos.getMagiaAUnidad();
	}

	public int getEnergia() {
		return this.energia.getEnergia();
	}
	
	public int getEnergiaMaxima(){
		return energia.getEnergiaMaxima();
	}
	
	public int getEnergiaARegenerarPorTurno(){
		return energia.getEnergiaARegenerarPorTurno();
	}
	
	public boolean puedeDisminuirEnergia(int cantidad){
		return this.energia.getEnergia() >= cantidad;
	}
	
	public void disminuirEnergia(int cantidad){
		//if (!this.puedeDisminuirEnergia(cantidad)) throw new EnergiaInsuficiente();
		this.energia.disminuirEnergia(cantidad);;
	}
	
	public void regenerarEnergia(int cantRegenerada){
		this.energia.regenerarEnergia(cantRegenerada);
	}
	
	//Equivalente al rango de ataque pero para magia
	public int getRangoMagia(){
		return this.energia.getRangoMagia();
	}
	
	public boolean estaEnRangoDeMagia(Posicion otraPosicion){
		return this.posicion.estaEnRango(posicion, this.getRangoMagia());
	}
	
	public MagiaDeAreaDeEfecto getMagiaDeAreaDeEfecto(){
		this.magiaAoE.setUnidadMagica(this);
		return magiaAoE;
	}
	
	public MagiaAUnidad getMagiaAUnidad(){
		this.magiaUnidad.setUnidadMagica(this);
		return magiaUnidad;
	}
}
