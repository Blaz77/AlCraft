package fiuba.algo3.ocupantes.unidades;

import java.util.ArrayList;

import fiuba.algo3.atributos.unidades.AtributosUnidadAtaque;
import fiuba.algo3.excepciones.FueraDelRangoPermitido;
import fiuba.algo3.excepciones.NoEsUnEnemigo;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.ObjetoVivo;

public class UnidadAtaque extends Unidad {
	
	int ataquesRestantes;
	//Quizas pasar a accionesRestantes -> ver comment en Unidad.

	private AtributosUnidadAtaque atributos;
	public UnidadAtaque(Jugador propietario, Posicion posicion, AtributosUnidadAtaque atributos) {
		super(propietario, posicion);
		this.atributos = atributos;
		inicializar();
	}
	
	@Override
	protected void inicializar() {
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
		if (!this.estaEnRangoDeAtaque(enemigo)) throw new FueraDelRangoPermitido();
		enemigo.recibirDanio(enemigo.getDanioEfectivo(
				((AtributosUnidadAtaque)this.atributos).getDanioAtkAire(),
				((AtributosUnidadAtaque)this.atributos).getDanioAtkTierra()));
	}
	
	//esto es una idea nomas, no necesariamente hacerlo
	public ArrayList<ObjetoVivo> getPosiblesObjetivos(){
		return null;
	}


	
}
