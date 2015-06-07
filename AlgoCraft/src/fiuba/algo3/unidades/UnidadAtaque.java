package fiuba.algo3.unidades;

import java.util.ArrayList;

import fiuba.algo3.atributos.AtributosUnidadAtaque;
import fiuba.algo3.edificios.ObjetoVivo;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class UnidadAtaque extends Unidad {
	
	int ataquesRestantes;
	//Quizas pasar a accionesRestantes -> ver comment en Unidad.

	public UnidadAtaque(Jugador propietario, Posicion posicion, AtributosUnidadAtaque atributos) {
		super(propietario, posicion, atributos);
	}
	
	public void pasarTurno(){
		super.pasarTurno();
		this.ataquesRestantes = ((AtributosUnidadAtaque)this.atributos).getAtaquesPorTurno();
	}
	
	//Pensar:
	// - Habria que diferenciar los que no pueden atacar nunca
	// 	 de los que terminaron sus turnos?
	public boolean puedeAtacar(){
		return ataquesRestantes != 0;
	}
	
	//Doy por sentado que un edificio es terrestre (quizas esta mal)
	public boolean puedeAtacarA(ObjetoVivo enemigo){
		//CHEQUEAR SI ES ENEMIGO DE VERDAD!
		return this.posicion.estaEnRango(enemigo.getPosicion(),enemigo.getRangoEfectivo(
						((AtributosUnidadAtaque)this.atributos).getRangoAtkAire(),
						((AtributosUnidadAtaque)this.atributos).getRangoAtkTierra()));
	}
	
	public void atacarA(ObjetoVivo enemigo){
		if (!this.puedeAtacarA(enemigo))
			throw new RuntimeException();//AtaqueImposible/AtaqueFueraDeRango
		enemigo.recibirDanio(enemigo.getDanioEfectivo(
				((AtributosUnidadAtaque)this.atributos).getDanioAtkAire(),
				((AtributosUnidadAtaque)this.atributos).getDanioAtkTierra()));
	}
	
	//esto es una idea nomas, no necesariamente hacerlo
	public ArrayList<ObjetoVivo> getPosiblesObjetivos(){
		return null;
	}


	
}
