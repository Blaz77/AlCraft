package fiuba.algo3.atributos.unidades;

import fiuba.algo3.atributos.AtributosEnergia;
import fiuba.algo3.componentes.Energia;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.magia.MagiaAUnidad;
import fiuba.algo3.magia.MagiaDeAreaDeEfecto;

public class AtributosUnidadMagica extends AtributosUnidad {

	//protected int energiaARegenerarPorTurno;
	//protected int energiaMax;
	//protected int rangoMagia;
	protected AtributosEnergia energia;
	protected MagiaDeAreaDeEfecto magiaAoE;
	protected MagiaAUnidad magiaUnidad;
	
	@Override
	public boolean puedeHacerMagia(){
		return true;
	}
	
	public Energia getEnergia() {
		return new Energia(this.energia);
	}
	
	public int getEnergiaMaxima() {
		return energia.getEnergiaMaxima();
	}
	
	public int getEnergiaARegenerarPorTurno() {
		return energia.getEnergiaARegenerarPorTurno();
	}
	
	public int getRangoMagia() {
		return energia.getRangoMagia(); // No se
	}
	
	public MagiaDeAreaDeEfecto getMagiaDeAreaDeEfecto(){
		return magiaAoE;
	}
	
	public MagiaAUnidad getMagiaAUnidad(){
		return magiaUnidad;
	}
	
}
