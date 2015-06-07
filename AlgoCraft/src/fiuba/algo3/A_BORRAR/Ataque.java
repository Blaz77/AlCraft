//TODO Clase sin usar!

package fiuba.algo3.A_BORRAR;

import java.util.ArrayList;

import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.edificios.ObjetoVivo;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.unidades.Unidad;

public class Ataque implements Componente {
	
	// Dividir Clases en -> AtaqueAereo, AtaqueTerrestre
	
	private final int ataquesPorTurno;
	private int ataquesRestantes;
	private int danioATierra;
	private int danioAAire; 
	private int rangoAire;
	private int rangoTierra;
	
	//Ataque quizas despues comparte Posicion con el objeto que lo use
	// -> actualizaciones a Posicion se reflejarian tambien aca!
		
	
	public Ataque(int danioAA, int danioAT, int rangoA, int rangoT, int ataquesPorTurno){
		this.ataquesPorTurno = ataquesPorTurno;
		this.ataquesRestantes = ataquesPorTurno;
		this.danioAAire = danioAA;
		this.danioATierra = danioAT;
		this.rangoAire = rangoA;
		this.rangoTierra = rangoT;
	}
	
	public boolean puedeAtacar(){
		return ataquesRestantes != 0;
	}
	
	//Doy por sentado que un edificio es terrestre (quizas esta mal)
	public boolean puedeAtacarA(Posicion miPosicion, Edificio edificio){
		return miPosicion.estaEnRango(edificio.getPosicion(), rangoTierra);
	}
	
	public void atacarA(Posicion miPosicion, Edificio edificio){
		if (!puedeAtacarA(miPosicion, edificio))
			throw new RuntimeException();//AtaqueImposible/AtaqueFueraDeRango
		edificio.recibirDanio(danioATierra);
	}
	
	//Esto va estar horrible por ahora, pero bueno.
	public boolean puedeAtacarA(Posicion miPosicion, Unidad unidad){
		if (unidad.puedeVolar())
			return miPosicion.estaEnRango(unidad.getPosicion(), rangoAire);
		return miPosicion.estaEnRango(unidad.getPosicion(), rangoTierra);
		
		// return unidadEnemiga.esPosibleElAtaqueDesde(pos, rango1, rango2)
	
	public void atacarA(Posicion miPosicion, Unidad unidad){
		if (!puedeAtacarA(miPosicion, unidad))
			throw new RuntimeException();//AtaqueImposible/AtaqueFueraDeRango
		//LO PROXIMO ES HORRIBLE!:
		if (unidad.puedeVolar())
			unidad.recibirDanio(danioAAire);
		else
			unidad.recibirDanio(danioATierra);
		//SOLUCION:
				// Pasar por parametro los dos danios,
				// que decida la unidad receptora que es lo que la afecta!
				// unidad.recibirDanio(danioATierra,danioAAire);
	}
	
	public ArrayList<ObjetoVivo> getPosiblesObjetivos(){
		return null;
	}

	public void setAnterior(Componente componenteAnterior) {
	}

	public Componente pasarTurno() {
		this.ataquesRestantes = this.ataquesPorTurno;
		return this;
	}

}

	