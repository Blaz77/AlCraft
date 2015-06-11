package fiuba.algo3.terreno;

import fiuba.algo3.ocupantes.Ocupante;

public abstract class Terreno {//probablemente interfaz
	
	public abstract boolean puedeSerOcupada(Ocupante ocupante);
	
	public abstract TipoTerreno getTipo();

}
