package fiuba.algo3.unidades;

import fiuba.algo3.juego.Jugador;

public class ConstructorNaveDeTransporte extends Constructor {

	public ConstructorNaveDeTransporte() {
		this.costoGas = 100;
		this.costoMinerales = 150;
		this.costoPoblacion = 2;
		this.turnosNecesarios = 7;
	}
	
	@Override
	public void liberarUnidad(Jugador j, int x, int y) {
		Unidad u = new NaveDeTransporte(j, x, y);

	}

	@Override
	public Constructor copia() {
		return new ConstructorNaveDeTransporte();
	}

}
