package fiuba.algo3.mapa.recurso;

import fiuba.algo3.edificios.Edificable;
import fiuba.algo3.juego.Ocupante;

public class Mineral extends Recurso {
	
	public TipoOcupante getTipo() {
		return TipoOcupante.MINERAL;
	}
	
	public boolean puedeEdificar(Edificable edif){
		return edif.sobreMineral();
	}

	@Override
	public void pasarTurno() {
		return;		
	}

}
