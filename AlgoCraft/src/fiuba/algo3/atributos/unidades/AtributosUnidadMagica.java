package fiuba.algo3.atributos.unidades;

import fiuba.algo3.magia.MagiaAUnidad;
import fiuba.algo3.magia.MagiaDeAreaDeEfecto;

public class AtributosUnidadMagica extends AtributosUnidad {

	protected int energiaARegenerarPorTurno;
	protected int energiaMax;
	protected int rangoMagia;
	protected MagiaDeAreaDeEfecto magiaAoE;
	protected MagiaAUnidad magiaUnidad;
	
	@Override
	public boolean puedeHacerMagia(){
		return true;
	}
	
	public int getEnergiaMaxima(){
		return energiaMax;
	}
	
	public int getEnergiaARegenerarPorTurno(){
		return energiaARegenerarPorTurno;
	}
	
	public int getRangoMagia() {
		return rangoMagia;
	}
	
	public MagiaDeAreaDeEfecto getMagiaDeAreaDeEfecto(){
		return magiaAoE;
	}
	
	public MagiaAUnidad getMagiaAUnidad(){
		return magiaUnidad;
	}
	
}
