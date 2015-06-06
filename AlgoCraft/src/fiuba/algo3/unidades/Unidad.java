package fiuba.algo3.unidades;

import java.util.ArrayList;

import fiuba.algo3.componentes.Movimiento;
import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.mapa.recurso.TipoOcupante;
import fiuba.algo3.edificios.ObjetoVivo;


/* 
 * 
 * Reflection para mandarle a la interfaz los metodos que ésta pueda ejecutar.
 * 
 * 
 * */
public abstract class Unidad extends ObjetoVivo{
	
	protected Movimiento movimiento;
	
	public Unidad(Jugador propietario, Posicion posicion){
		super(propietario, posicion);
		//POSICIONAR EN ESTE CONSTRUCTOR:
		// -UNIDAD EN EL MAPA -> mapa.addUnidad(this, POSicion)
		propietario.agregarUnidad(this);
		
		//O HACER EN LOS CONSTRUCTORES!?!??!
	}
	
	public void pasarTurno(){
		super.pasarTurno();
		this.movimiento = (Movimiento)this.movimiento.pasarTurno();
	}
	
	public TipoOcupante getTipo(){
		return TipoOcupante.UNIDAD;
	}
	
	/*
	 * Metodos para reflection para la barra de acciones
	 * 
	 * Atacar / Magia
	 * Moverse
	 * Descargar Unidades alojadas
	 * Meterse dentro de transporte
	 * 
	 * 
	 * Si usamos reflection, los demas metodos van encapsulados
	 * 
	 * 
	 * 
	 * */
	
	public boolean puedeMoverse(){
		return this.movimiento.puedeMoverse();
	}
	
	// si no puede volar significa que es terrestre
	public boolean puedeVolar(){ //esAereo();
		return this.movimiento.puedeVolar();
	}
	
	public boolean puedeMoverseA(Posicion destino){
		return this.movimiento.puedeMoverseA(this.posicion, destino);
	}
	
	public void moverA(Posicion destino){
		this.posicion = this.movimiento.moverA(this.posicion, destino);
	}
	
	public ArrayList<Posicion> getPosiblesMovimientos(){
		return this.movimiento.getPosiblesMovimientos();
	}
	
	public boolean puedeAtacar(){
		return true;
	}
	
	// Este iria adentro de un strategy, xq hay unidades de magia q no atacan.
	public void atacarA(Unidad enemigo){
		
		
	}

	public void atacarA(Edificio enemigo){
		
	}

}
