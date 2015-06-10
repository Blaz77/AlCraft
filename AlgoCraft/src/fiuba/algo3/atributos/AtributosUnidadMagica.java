package fiuba.algo3.atributos;

import fiuba.algo3.unidades.MagiaAUnidad;
import fiuba.algo3.unidades.MagiaDeAreaDeEfecto;

public class AtributosUnidadMagica extends AtributosUnidad {

	int energiaARegenerarPorTurno;
	int energiaMax;
	int rangoMagia;
	MagiaDeAreaDeEfecto magiaAoE;
	MagiaAUnidad magiaUnidad;
	
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
