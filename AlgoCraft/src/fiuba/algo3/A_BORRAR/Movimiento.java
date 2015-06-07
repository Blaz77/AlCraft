package fiuba.algo3.componentes;

import java.util.ArrayList;

import fiuba.algo3.mapa.Posicion;

public class Movimiento implements Componente {
	
	private final int movPorTuno;
	private int movRestantes;
	
	
	//Movimiento quizas despues comparte Posicion con el objeto que lo use
	// -> actualizaciones a Posicion se reflejarian tambien en el objeto!
	
	public Movimiento(int movPorTurno){
		this.movPorTuno = movPorTurno;
		this.movRestantes = movPorTurno;
	}

	public boolean puedeMoverse(){
		return (this.movRestantes != 0);
	}
	
	// si no puede volar significa que es terrestre
	public boolean puedeVolar(){ //esAereo();
		return false;
	}
	
	public boolean puedeMoverseA(Posicion inicial, Posicion destino){
		return inicial.estaEnRango(destino, movRestantes);
		//chequear Terreno!!!!!
	}
	
	public Posicion moverA(Posicion inicial, Posicion destino){
		if (!this.puedeMoverseA(inicial, destino))
			throw new RuntimeException(); //MovimientoImposibleException?
		this.movRestantes -= inicial.distancia(destino);
		return destino;
	}
	
	public ArrayList<Posicion> getPosiblesMovimientos(){
		return null;
		
	}
	
	public void setAnterior(Componente componenteAnterior) {
	}

	public Componente pasarTurno() {
		this.movRestantes = this.movPorTuno;
		return this;
	}

}
