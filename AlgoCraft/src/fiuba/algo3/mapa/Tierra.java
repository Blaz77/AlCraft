package fiuba.algo3.mapa;

import fiuba.algo3.edificios.Edificable;

public class Tierra extends Terreno {
	
	public TipoTerreno getTipo() {
		return TipoTerreno.TIERRA;
	}
	
	public boolean puedeEdificar(Edificable edif){
		return edif.enTierra();
	}

}
