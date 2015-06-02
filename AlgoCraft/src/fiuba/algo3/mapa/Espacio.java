package fiuba.algo3.mapa;

import fiuba.algo3.edificios.Edificable;

public class Espacio extends Terreno {
	
	public boolean puedeEdificar(Edificable edif){
		return edif.enEspacio();
	}

}
