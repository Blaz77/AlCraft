package fiuba.algo3.mapa;

import fiuba.algo3.edificios.Edificable;

public class Mineral extends Recurso{
	
	public boolean puedeEdificar(Edificable edif){
		return edif.sobreMineral();
	}

}
