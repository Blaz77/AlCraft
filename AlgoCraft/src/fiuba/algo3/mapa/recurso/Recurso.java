package fiuba.algo3.mapa.recurso;

import fiuba.algo3.edificios.Edificable;
import fiuba.algo3.juego.Ocupante;

public abstract class Recurso extends Ocupante {
	
	public abstract boolean puedeEdificar(Edificable edif);
	
	public abstract TipoOcupante getTipo();
	
}
