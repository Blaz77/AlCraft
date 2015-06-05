package fiuba.algo3.unidades;

import fiuba.algo3.juego.Jugador;

public class ConstructorNaveDeCiencia extends Constructor {

	public ConstructorNaveDeCiencia() {
		this.costoGas = 225;
		this.costoMinerales = 100;
		this.costoPoblacion = 2;
		this.turnosNecesarios = 10;
	}
	
	@Override
	public void liberarUnidad(Jugador j, int x, int y) {
		Unidad u = new NaveDeCiencia(j, x, y);

	}

	@Override
	public Constructor copia() {
		return new ConstructorNaveDeCiencia();
	}

}
