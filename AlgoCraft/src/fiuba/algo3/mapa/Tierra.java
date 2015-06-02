package fiuba.algo3.mapa;

import fiuba.algo3.edificios.Edificable;

public class Tierra extends Terreno {
	
	public boolean puedeEdificar(Edificable edif){
		return edif.enTierra();
	}

}
