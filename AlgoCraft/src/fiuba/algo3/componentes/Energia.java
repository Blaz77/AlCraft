package fiuba.algo3.componentes;

import fiuba.algo3.atributos.AtributosEnergia;
import fiuba.algo3.atributos.AtributosVida;

public class Energia {
	private AtributosEnergia atributos;
	private int energia;

	public Energia(int energiaInicial, AtributosEnergia atributos){
		this.atributos = atributos;
		this.energia = energiaInicial;
	}
	
	public Energia(AtributosEnergia atributos) {
		this(atributos.getEnergiaMaxima(), atributos);
	}
	
	public int getEnergia() {
		return this.energia;
	}
	
	public int getEnergiaMaxima() {
		return atributos.getEnergiaMaxima();
	}

	public int getEnergiaARegenerarPorTurno() {
		return atributos.getEnergiaARegenerarPorTurno();
	}
	
	public void disminuirEnergia(int puntos) {
		this.energia -= puntos;
		if (this.energia < 0){
			this.energia = 0;
		}
	}
	
	public void regenerarEnergia(int puntos) {
		this.energia += puntos;
		if (this.energia > getEnergiaMaxima())
			this.energia = getEnergiaMaxima();
	}

	public int getRangoMagia() {
		return atributos.getRangoMagia();
	}
}
