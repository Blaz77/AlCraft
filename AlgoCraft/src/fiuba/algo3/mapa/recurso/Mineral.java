package fiuba.algo3.mapa.recurso;

import fiuba.algo3.edificios.Edificable;

public class Mineral extends Recurso {
	
	public TipoRecurso getTipo() {
		return TipoRecurso.MINERAL;
	}
	
	public boolean puedeEdificar(Edificable edif){
		return edif.sobreMineral();
	}

}
