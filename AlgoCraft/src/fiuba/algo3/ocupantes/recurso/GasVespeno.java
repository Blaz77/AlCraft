package fiuba.algo3.ocupantes.recurso;

import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.Ocupante;

public class GasVespeno extends Recurso {
	// Tipicamente, los volcanes de gas son todos iguales, y arrancan con 5000 de gas.
	// Podrian arrancar con una cantidad distinta, pero hacerlos q ocupen distintas celdas nah.
	
	public GasVespeno(Posicion posicion) {
		super(posicion);
	}
	
	public TipoOcupante getTipo() {
		return TipoOcupante.VESPENO;
	}
	
	// Este metodo despues se borra :3 es para los print y probar cosas
	public String toString() {
		return "Geyser";
	}
	
	@Override
	public void pasarTurno() {
		return;
		
	}
	
	@Override
	public boolean puedeCambiarsePor(Ocupante otroOcupante) {
		return (super.puedeCambiarsePor(otroOcupante) &&
				otroOcupante.debeOcuparGasVespeno());
	}
	
}
