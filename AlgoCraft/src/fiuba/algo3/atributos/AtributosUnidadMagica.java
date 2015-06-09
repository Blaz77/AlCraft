package fiuba.algo3.atributos;

import fiuba.algo3.unidades.MagiaAUnidad;
import fiuba.algo3.unidades.MagiaDeAreaDeEfecto;

public class AtributosUnidadMagica extends AtributosUnidad {

	int energiaARegenerarPorTurno;
	int energiaMax;
	int rangoMagia;
	MagiaDeAreaDeEfecto magiaAoE;
	MagiaAUnidad magiaUnidad;
	
	public int getEnergiaMaxima(){
		return energiaMax;
	}
	
	public int getEnergiaARegenerarPorTurno(){
		return energiaARegenerarPorTurno;
	}
	
	public MagiaDeAreaDeEfecto getMagiaDeAreaDeEfecto(){
		return magiaAoE;
	}
	
	public MagiaAUnidad getMagiaAUnidad(){
		return magiaUnidad;
	}

	public int getRangoMagia() {
		return rangoMagia;
	}
	
}
