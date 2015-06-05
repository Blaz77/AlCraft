package fiuba.algo3.unidades;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class ConstructorNaveDeCiencia extends Constructor {

	public ConstructorNaveDeCiencia() {
		this.costoGas = 225;
		this.costoMinerales = 100;
		this.costoPoblacion = 2;
		this.turnosNecesarios = 10;
	}
	
	@Override
	public void liberarUnidad(Jugador j, Posicion posicion) {
		new NaveDeCiencia(j, posicion);

	}

	@Override
	public Constructor copia() {
		return new ConstructorNaveDeCiencia();
	}

}
