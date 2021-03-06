package fiuba.algo3.componentes;

import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.excepciones.EstadoFinalizado;
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

	public void pasarTurno() throws EstadoFinalizado {
		this.turnosRestantes--;
		if (this.turnosRestantes == 0) {
			this.entrenador.liberarUnidad(atributos);
			throw new EstadoFinalizado();
		}
	}

	public void desactivar() {
		// hay que hacer al desactivar porque modifica la lista de estados!?!?!
		this.entrenador.proximoEntrenamiento();
	}

	@Override
	public String getDescripcion() {
		return String.format("Entrenando %s... %d%%.", atributos.getNombre(), 100 - turnosRestantes * 100 / atributos.getCosto().getTurnosConstruccion());
	}

}
