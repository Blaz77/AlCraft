package fiuba.algo3.terreno;

import fiuba.algo3.ocupantes.edificios.Edificable;

public class Tierra extends Terreno {
	
	public TipoTerreno getTipo() {
		return TipoTerreno.TIERRA;
	}
	
	public boolean puedeEdificar(Edificable edif){
		return edif.enTierra();
	}

}
