package fiuba.algo3.componentes;

import java.util.ArrayList;

import fiuba.algo3.atributos.AtributosAtaque;
import fiuba.algo3.excepciones.FueraDelRangoPermitido;
import fiuba.algo3.excepciones.NoEsUnEnemigo;
import fiuba.algo3.ocupantes.ObjetoVivo;

public class Ataque implements Estado {
	
	private int ataquesRestantes;
	private AtributosAtaque atributos;
	private ObjetoVivo portador;
	
	//Ataque quizas despues comparte Posicion con el objeto que lo use
	// -> actualizaciones a Posicion se reflejarian tambien aca!
		
	
	public Ataque(AtributosAtaque atributos){
		this.atributos = atributos;
		this.ataquesRestantes = this.atributos.getAtaquesPorTurno();
	}
	
	public void activar(ObjetoVivo portador) {
		this.portador = portador;
	}

	public void desactivar() {}
	
	public void pasarTurno(){
		this.ataquesRestantes = this.atributos.getAtaquesPorTurno();
	}
	
	//Pensar:
	// - Habria que diferenciar los que no pueden atacar nunca
	// 	 de los que terminaron sus turnos?
	public boolean puedeAtacar(){
		return ataquesRestantes != 0;
	}
	
	private boolean estaEnRangoDeAtaque(ObjetoVivo enemigo){
		return this.portador.getPosicion().estaEnRango(
				enemigo.getPosicion(),enemigo.getRangoEfectivo(
						this.atributos.getRangoAtkAire(),
						this.atributos.getRangoAtkTierra()));
	}
	
	public boolean puedeAtacarA(ObjetoVivo enemigo){
		return (this.portador.esEnemigoDe(enemigo) && 
				this.estaEnRangoDeAtaque(enemigo));
	}
	
	public void atacarA(ObjetoVivo enemigo){
		if (!this.portador.esEnemigoDe(enemigo)) throw new NoEsUnEnemigo();
		if (!this.estaEnRangoDeAtaque(enemigo)) throw new FueraDelRangoPermitido();
		enemigo.recibirDanio(enemigo.getDanioEfectivo(
				this.atributos.getDanioAtkAire(),
				this.atributos.getDanioAtkTierra()));
	}
	
	//esto es una idea nomas, no necesariamente hacerlo
	public ArrayList<ObjetoVivo> getPosiblesObjetivos(){
		return null;
	}


}

	