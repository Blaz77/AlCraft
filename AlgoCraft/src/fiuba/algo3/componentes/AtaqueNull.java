package fiuba.algo3.componentes;

import java.util.ArrayList;

import fiuba.algo3.ocupantes.ObjetoVivo;

public class AtaqueNull implements IAtaque {

	public void setPortador(ObjetoVivo portador) {}
	
	public boolean puedeAtacar() {
		return false;
	}

	public boolean puedeAtacarA(ObjetoVivo enemigo) {
		return false;
	}

	public void atacarA(ObjetoVivo enemigo) {
		//throw ALGO?
	}

	public ArrayList<ObjetoVivo> getPosiblesObjetivos() {
		return null;
	}

}
