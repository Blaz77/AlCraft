package fiuba.algo3.unidades;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class ConstructorMarine extends Constructor{
	
	public ConstructorMarine() {
		this.costoGas = 0;
		this.costoMinerales = 50;
		this.costoPoblacion = 1;
		this.turnosNecesarios = 3;
	}
	
	@Override
	public void liberarUnidad(Jugador j, Posicion posicion) {
		new Marine(j, posicion);
	}

	@Override
	public Constructor copia() {
		return new ConstructorMarine();
	}

	
	
	

}
