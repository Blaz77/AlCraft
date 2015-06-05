package fiuba.algo3.unidades;

import fiuba.algo3.juego.Jugador;

public class ConstructorEspectro extends Constructor {

	public ConstructorEspectro() {
		this.costoGas = 100;
		this.costoMinerales = 150;
		this.costoPoblacion = 2;
		this.turnosNecesarios = 8;
	}
	
	@Override
	public void liberarUnidad(Jugador j, int x, int y) {
		new Espectro(j, x, y);

	}

	@Override
	public Constructor copia() {
		return new ConstructorEspectro();
	}

}
