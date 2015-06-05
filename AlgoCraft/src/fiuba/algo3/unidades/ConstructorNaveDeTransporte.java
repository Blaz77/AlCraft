package fiuba.algo3.unidades;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class ConstructorNaveDeTransporte extends Constructor {

	public ConstructorNaveDeTransporte() {
		this.costoGas = 100;
		this.costoMinerales = 150;
		this.costoPoblacion = 2;
		this.turnosNecesarios = 7;
	}
	
	@Override
	public void liberarUnidad(Jugador propietario, Posicion posicion) {
		new NaveDeTransporte(propietario, posicion);

	}

	@Override
	public Constructor copia() {
		return new ConstructorNaveDeTransporte();
	}

}
