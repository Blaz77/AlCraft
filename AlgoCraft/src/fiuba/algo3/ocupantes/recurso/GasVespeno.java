package fiuba.algo3.ocupantes.recurso;

import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.Ocupante;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.TipoOcupante;

public class GasVespeno extends Recurso {
	// Tipicamente, los volcanes de gas son todos iguales, y arrancan con 5000 de gas.
	// Podrian arrancar con una cantidad distinta, pero hacerlos q ocupen distintas celdas nah.
	
	public GasVespeno(Posicion posicion) {
		super(posicion);
	}
	
	public Tipo getTipo() {
		return Tipo.VESPENO;
	}
	
	@Override
	public TipoOcupante getTipoOcupante() {
		return Tipo.VESPENO.getTipoOcupante();
	}
	
	// Este metodo despues se borra :3 es para los print y probar cosas
	public String toString() {
		return "Geyser";
	}
	
	@Override
	public boolean puedeCambiarsePor(Ocupante otroOcupante) {
		return (super.puedeCambiarsePor(otroOcupante) &&
				otroOcupante.debeOcuparGasVespeno());
	}
	
}
