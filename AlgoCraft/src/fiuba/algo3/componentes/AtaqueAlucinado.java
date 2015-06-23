package fiuba.algo3.componentes;

import java.util.ArrayList;

import fiuba.algo3.ocupantes.ObjetoVivo;

public class AtaqueAlucinado implements IAtaque {
	
	private IAtaque ataque;
	
	public AtaqueAlucinado(IAtaque ataqueOriginal){
		this.ataque = ataqueOriginal;
	}

	public boolean puedeAtacar() {
		return ataque.puedeAtacar();
	}

	public boolean puedeAtacarA(ObjetoVivo enemigo) {
		return ataque.puedeAtacarA(enemigo);
	}

	public void atacarA(ObjetoVivo enemigo) {}

	public ArrayList<ObjetoVivo> getPosiblesObjetivos() {
		return ataque.getPosiblesObjetivos();
	}

}
