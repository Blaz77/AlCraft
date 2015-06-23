package fiuba.algo3.componentes;

import fiuba.algo3.magia.MagiaAUnidad;
import fiuba.algo3.magia.MagiaDeAreaDeEfecto;
import fiuba.algo3.mapa.Posicion;

public interface IMagia {
	
	//necesario?
	public boolean puedeHacerMagia();
	
	public int getEnergia();
	
	public int getEnergiaMaxima();
	
	// private
	public int getEnergiaARegenerarPorTurno();

	public boolean puedeDisminuirEnergia(int cantidad);
	
	public void disminuirEnergia(int cantidad);
	
	public void regenerarEnergia(int cantRegenerada);
	
	//Equivalente al rango de ataque pero para magia
	public int getRangoMagia();
	
	public boolean estaEnRangoDeMagia(Posicion otraPosicion);
	
	public MagiaDeAreaDeEfecto getMagiaDeAreaDeEfecto();
	
	public MagiaAUnidad getMagiaAUnidad();

}
