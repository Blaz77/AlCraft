package fiuba.algo3.ocupantes.unidades;

import java.util.ArrayList;

import fiuba.algo3.atributos.unidades.AtributosUnidadAtaque;
import fiuba.algo3.componentes.Ataque;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.ObjetoVivo;

public class UnidadAtaque extends Unidad {
	
	private Ataque ataque;
	//Quizas pasar a accionesRestantes -> ver comment en Unidad.

	public UnidadAtaque(Jugador propietario, Posicion posicion, AtributosUnidadAtaque atributos) {
		super(propietario, posicion, atributos);
		this.ataque = atributos.getAtaque();
		this.ataque.activar(this);
	}
	
	//Pensar:
	// - Habria que diferenciar los que no pueden atacar nunca
	// 	 de los que terminaron sus turnos?
	public boolean puedeAtacar(){
		return ataque.puedeAtacar();
	}
	
	public boolean puedeAtacarA(ObjetoVivo enemigo){
		return ataque.puedeAtacarA(enemigo);
	}
	
	public void atacarA(ObjetoVivo enemigo){
		ataque.atacarA(enemigo);
	}
	
	//esto es una idea nomas, no necesariamente hacerlo
	public ArrayList<ObjetoVivo> getPosiblesObjetivos(){
		return ataque.getPosiblesObjetivos();
	}


	
}
