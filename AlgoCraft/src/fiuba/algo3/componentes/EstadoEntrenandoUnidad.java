package fiuba.algo3.componentes;

import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.ocupantes.ObjetoVivo;

public class EstadoEntrenandoUnidad implements Estado {

	private AtributosUnidad atributos;
	private IEntrenadorUnidades entrenador;
	private int turnosRestantes;
	
	public EstadoEntrenandoUnidad(IEntrenadorUnidades entrenador, AtributosUnidad atributos){
		this.atributos = atributos;
		this.entrenador = entrenador;
		this.turnosRestantes = atributos.getCosto().getTurnosConstruccion();
	}
	
	public void activar(ObjetoVivo portador) {}

	public void pasarTurno() throws Exception {
		this.turnosRestantes--;
		if (this.turnosRestantes == 0) {
			this.entrenador.liberarUnidad(atributos);
			throw new Exception();
		}
	}

	public void desactivar() {
		// hay que hacer al desactivar porque modifica la lista de estados!?!?!
		this.entrenador.proximoEntrenamiento();
	}

}
