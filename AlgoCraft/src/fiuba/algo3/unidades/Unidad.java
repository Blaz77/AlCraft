package fiuba.algo3.unidades;

import java.util.ArrayList;

import fiuba.algo3.atributos.AtributosUnidad;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.mapa.recurso.TipoOcupante;
import fiuba.algo3.edificios.ObjetoVivo;

public abstract class Unidad extends ObjetoVivo{
	
	int movRestantes;
	//O hacer accionesRestantes y que p/ej 1 ataque cueste 2
	//									 y 1 movim. cueste 1
	
	
	public Unidad(Jugador propietario, Posicion posicion, AtributosUnidad atributos){
		super(propietario, posicion, atributos);
		//Hacer esto de abajo en los CONSTRUCTORES! NO ACA!:
		propietario.agregarUnidad(this);
		this.movRestantes = ((AtributosUnidad)this.atributos).getMovPorTurno();
	}
	
	public void pasarTurno(){
		super.pasarTurno();
		//Temp esto aca abajo, quizas puede estar mejor hecho
		// Un estado quizas ?
		this.movRestantes = ((AtributosUnidad)this.atributos).getMovPorTurno();
	}

	
	public TipoOcupante getTipo(){
		return TipoOcupante.UNIDAD;
	}

	
	/*
	 * Lista temporal:
	 * 
	 * Atacar / Magia
	 * Moverse
	 * Descargar Unidades alojadas
	 * Meterse dentro de transporte
	 * */	

	//Pensar:
	// - Habria que diferenciar los que no se pueden mover nunca
	// 	 de los que terminaron sus turnos?
	public boolean puedeMoverse(){
		return (this.movRestantes != 0);
	}
	
	public boolean puedeMoverseA(Posicion inicial, Posicion destino){
		return inicial.estaEnRango(destino, movRestantes);
		//chequear Terreno!!!!!
	}
	
	public void moverA(Posicion inicial, Posicion destino){
		if (!this.puedeMoverseA(inicial, destino))
			throw new RuntimeException(); //MovimientoImposibleException?
		this.movRestantes -= inicial.distancia(destino);
		this.posicion = destino;
	}
	
	//esto es una idea nomas, no necesariamente hacerlo
	public ArrayList<Posicion> getPosiblesMovimientos(){
		return null;
		
	}
	
	public boolean puedeAtacar(){
		return ((AtributosUnidad)this.atributos).puedeAtacar();
	}
	
	public boolean puedeHacerMagia(){
		return ((AtributosUnidad)this.atributos).puedeHacerMagia();
	}
	
	public boolean puedeVolar() {//esAereo()
		return this.atributos.esAereo();		
	}

}
