package fiuba.algo3.componentes;

import fiuba.algo3.atributos.AtributosMagia;
import fiuba.algo3.excepciones.EnergiaInsuficiente;
import fiuba.algo3.magia.MagiaAUnidad;
import fiuba.algo3.magia.MagiaDeAreaDeEfecto;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.unidades.UnidadMagica;

public class Magia {
	
	private int energia;
	private AtributosMagia atributos;
	
	public Magia(AtributosMagia atributos) {
		this.atributos = atributos;
		this.energia = this.getEnergiaMaxima(); //o una energia inicial
	}

	public int getEnergia(){
		return this.energia;
	}
	
	public int getEnergiaMaxima(){
		return this.atributos.getEnergiaMaxima();
	}
	
	// private
	public int getEnergiaARegenerarPorTurno(){
		return this.atributos.getEnergiaARegenerarPorTurno();
	}
	
	public boolean puedeDisminuirEnergia(int cantidad){
		return this.energia >= cantidad;
	}
	
	public void disminuirEnergia(int cantidad){
		if (!this.puedeDisminuirEnergia(cantidad)) throw new EnergiaInsuficiente();
		this.energia -= cantidad;
	}
	
	public void regenerarEnergia(int cantRegenerada){
		this.energia += cantRegenerada;
		if (this.energia > this.getEnergiaMaxima())
			this.energia = this.getEnergiaMaxima();
	}
	
	//Equivalente al rango de ataque pero para magia
	public int getRangoMagia(){
		return this.atributos.getRangoMagia();
	}
	
	public boolean estaEnRangoDeMagia(UnidadMagica efectuador, Posicion otraPosicion){
		return efectuador.getPosicion().estaEnRango(otraPosicion, this.getRangoMagia());
	}
	
	public MagiaDeAreaDeEfecto getMagiaDeAreaDeEfecto(UnidadMagica efectuador){
		MagiaDeAreaDeEfecto magia = this.atributos.getMagiaDeAreaDeEfecto();
		magia.setUnidadMagica(efectuador);
		return magia;
	}
	
	public MagiaAUnidad getMagiaAUnidad(UnidadMagica efectuador){
		MagiaAUnidad magia = this.atributos.getMagiaAUnidad();
		magia.setUnidadMagica(efectuador);
		return magia;
	}

}
