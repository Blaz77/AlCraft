package fiuba.algo3.unidades;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class ConstructorEspectro extends Constructor {

	public ConstructorEspectro() {
		this.costoGas = 100;
		this.costoMinerales = 150;
		this.costoPoblacion = 2;
		this.turnosNecesarios = 8;
	}
	
	@Override
	public void liberarUnidad(Jugador j, Posicion posicion) {
		new Espectro(j, posicion);

	}

	@Override
	public Constructor copia() {
		return new ConstructorEspectro();
	}

}
