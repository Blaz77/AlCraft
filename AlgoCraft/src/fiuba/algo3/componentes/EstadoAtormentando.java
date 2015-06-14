package fiuba.algo3.componentes;

import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.unidades.Unidad;

public class EstadoAtormentando implements Estado {

	ObjetoVivo portador; // Esto para no matarse a si mismo
	private Posicion posicionCentral;
	private int rangoTormenta;
	private Mapa mapa;
	private int turnosRestantes;
	
	public EstadoAtormentando(Posicion posicionCentral, Mapa mapa, int rangoTormenta) {
		this.posicionCentral = posicionCentral;
		this.rangoTormenta = rangoTormenta;
		this.mapa = mapa;
	}

	public void activar(ObjetoVivo portador) {
		this.portador = portador;
		this.turnosRestantes = 2;
	}

	public void pasarTurno() throws Exception {
		for (Unidad unidad : mapa.getUnidadesEnRango(posicionCentral, rangoTormenta)) {
			unidad.recibirDanio(100); // Es mucho, pero es lo que dice el enunciado
		}
		this.turnosRestantes -= 1;
		if (turnosRestantes == 0) {
			throw new Exception();
		}
	}

	public void desactivar() {}

}
