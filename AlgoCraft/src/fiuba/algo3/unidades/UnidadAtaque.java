package fiuba.algo3.unidades;

import java.util.ArrayList;

import fiuba.algo3.atributos.AtributosUnidadAtaque;
import fiuba.algo3.edificios.ObjetoVivo;
import fiuba.algo3.excepciones.EnemigoFueraDeRango;
import fiuba.algo3.excepciones.NoEsUnEnemigo;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class UnidadAtaque extends Unidad {
	
	int ataquesRestantes;
	//Quizas pasar a accionesRestantes -> ver comment en Unidad.

	public UnidadAtaque(Jugador propietario, Posicion posicion, AtributosUnidadAtaque atributos) {
		super(propietario, posicion, atributos);
		this.ataquesRestantes = ((AtributosUnidadAtaque)this.atributos).getAtaquesPorTurno();
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
	
	private boolean estaEnRangoDeAtaque(ObjetoVivo enemigo){
		return this.posicion.estaEnRango(
				enemigo.getPosicion(),enemigo.getRangoEfectivo(
						((AtributosUnidadAtaque)this.atributos).getRangoAtkAire(),
						((AtributosUnidadAtaque)this.atributos).getRangoAtkTierra()));
	}
	
	public boolean puedeAtacarA(ObjetoVivo enemigo){
		return (this.esEnemigoDe(enemigo) && 
				this.estaEnRangoDeAtaque(enemigo));
	}
	
	public void atacarA(ObjetoVivo enemigo){
		if (!this.esEnemigoDe(enemigo)) throw new NoEsUnEnemigo();
		if (!this.estaEnRangoDeAtaque(enemigo)) throw new EnemigoFueraDeRango();
		enemigo.recibirDanio(enemigo.getDanioEfectivo(
				((AtributosUnidadAtaque)this.atributos).getDanioAtkAire(),
				((AtributosUnidadAtaque)this.atributos).getDanioAtkTierra()));
	}
	
	//esto es una idea nomas, no necesariamente hacerlo
	public ArrayList<ObjetoVivo> getPosiblesObjetivos(){
		return null;
	}


	
}
