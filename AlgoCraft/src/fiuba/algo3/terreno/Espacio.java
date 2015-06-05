package fiuba.algo3.terreno;

import fiuba.algo3.edificios.Edificable;

public class Espacio extends Terreno {
	
	public TipoTerreno getTipo() {
		return TipoTerreno.ESPACIO;
	}
	
	public boolean puedeEdificar(Edificable edif){
		return edif.enEspacio();
	}

}
