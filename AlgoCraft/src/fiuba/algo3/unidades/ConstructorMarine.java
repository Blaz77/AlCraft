package fiuba.algo3.unidades;

import fiuba.algo3.juego.Jugador;

public class ConstructorMarine extends Constructor{
	
	public ConstructorMarine() {
		this.costoGas = 0;
		this.costoMinerales = 50;
		this.costoPoblacion = 1;
		this.turnosNecesarios = 3;
	}
	
	@Override
	public void liberarUnidad(Jugador j, int x, int y) {
		Unidad u = new Marine(j, x, y);
	}

	@Override
	public Constructor copia() {
		return new ConstructorMarine();
	}

	
	
	

}
