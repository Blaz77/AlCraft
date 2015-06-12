package fiuba.algo3.ocupantes.unidades;

import fiuba.algo3.atributos.unidades.AtributosUnidadAtaque;
import fiuba.algo3.atributos.unidades.AtributosUnidadMagica;
import fiuba.algo3.excepciones.EnergiaInsuficiente;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.magia.MagiaAUnidad;
import fiuba.algo3.magia.MagiaDeAreaDeEfecto;
import fiuba.algo3.mapa.Posicion;

public class UnidadMagica extends Unidad {

	protected int energia;
	
	private AtributosUnidadMagica atributos;
	public UnidadMagica(Jugador propietario, Posicion posicion,
			AtributosUnidadMagica atributos) {
		super(propietario, posicion);
		this.atributos = atributos;
		inicializar();
		
	}

	@Override
	protected void inicializar() {
		this.energia = this.getEnergiaMaxima();
	}
	
	public int getEnergia(){
		return this.energia;
	}
	
	public int getEnergiaMaxima(){
		return ((AtributosUnidadMagica)this.atributos).getEnergiaMaxima();
	}
	
	public int getEnergiaARegenerarPorTurno(){
		return ((AtributosUnidadMagica)this.atributos).getEnergiaARegenerarPorTurno();
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
		return ((AtributosUnidadMagica)this.atributos).getRangoMagia();
	}
	
	public boolean estaEnRangoDeMagia(Posicion otraPosicion){
		return this.posicion.estaEnRango(posicion, this.getRangoMagia());
	}
	
	public MagiaDeAreaDeEfecto getMagiaDeAreaDeEfecto(){
		MagiaDeAreaDeEfecto magia = ((AtributosUnidadMagica)this.atributos).getMagiaDeAreaDeEfecto();
		magia.setUnidadMagica(this);
		return magia;
	}
	
	public MagiaAUnidad getMagiaAUnidad(){
		MagiaAUnidad magia = ((AtributosUnidadMagica)this.atributos).getMagiaAUnidad();
		magia.setUnidadMagica(this);
		return magia;
	}
}
