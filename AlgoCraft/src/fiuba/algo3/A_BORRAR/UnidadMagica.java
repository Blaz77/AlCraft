package fiuba.algo3.A_BORRAR;

import fiuba.algo3.atributos.unidades.AtributosUnidadMagica;
import fiuba.algo3.componentes.IMagia;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.magia.MagiaAUnidad;
import fiuba.algo3.magia.MagiaDeAreaDeEfecto;
import fiuba.algo3.mapa.Posicion;

public class UnidadMagica extends Unidad {

	protected IMagia magia;
	
	public UnidadMagica(Jugador propietario, Posicion posicion,
			AtributosUnidadMagica atributos) {
		super(propietario, posicion, atributos);
		this.magia = atributos.getMagia();
	}
	
	public boolean puedeHacerMagia() {
		return this.magia.puedeHacerMagia();
	}

	public int getEnergia() {
		return this.magia.getEnergia();
	}
	
	public int getEnergiaMaxima(){
		return this.magia.getEnergiaMaxima();
	}
	
	public int getEnergiaARegenerarPorTurno(){
		return this.magia.getEnergiaARegenerarPorTurno();
	}
	
	public boolean puedeDisminuirEnergia(int cantidad){
		return this.magia.puedeDisminuirEnergia(cantidad);
	}
	
	public void disminuirEnergia(int cantidad){
		this.magia.disminuirEnergia(cantidad);
	}
	
	public void regenerarEnergia(int cantRegenerada){
		this.magia.regenerarEnergia(cantRegenerada);
	}
	
	//Equivalente al rango de ataque pero para magia
	public int getRangoMagia(){
		return this.magia.getRangoMagia();
	}
	
	public boolean estaEnRangoDeMagia(Posicion otraPosicion){
		return this.magia.estaEnRangoDeMagia(this, otraPosicion);
	}
	
	public MagiaDeAreaDeEfecto getMagiaDeAreaDeEfecto(){
		return this.magia.getMagiaDeAreaDeEfecto(this);
	}
	
	public MagiaAUnidad getMagiaAUnidad(){
		return this.magia.getMagiaAUnidad(this);
	}
}
