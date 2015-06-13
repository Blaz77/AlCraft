package fiuba.algo3.atributos;

import fiuba.algo3.magia.MagiaAUnidad;
import fiuba.algo3.magia.MagiaDeAreaDeEfecto;

public class AtributosMagia {
	
	private int energiaMax;
	private int energiaARegenerarPorTurno;
	private int rangoMagia;
	private MagiaDeAreaDeEfecto magiaAoE;
	private MagiaAUnidad magiaUnidad;
	
	public AtributosMagia(int energiaMax, int energiaARegenPorTurno, int rangoMagia
						,MagiaDeAreaDeEfecto magiaAoE, MagiaAUnidad magiaUnidad) {
		this.energiaMax = energiaMax;
		this.energiaARegenerarPorTurno = energiaARegenPorTurno;
		this.rangoMagia = rangoMagia;
		this.magiaAoE = magiaAoE;
		this.magiaUnidad = magiaUnidad;
	}
	
	// necesario?
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
