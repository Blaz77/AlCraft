package fiuba.algo3.terreno;

import fiuba.algo3.ocupantes.edificios.Edificable;

public abstract class Terreno {//probablemente interfaz
	
	public abstract boolean puedeEdificar(Edificable edif);
	
	public abstract TipoTerreno getTipo();

}
