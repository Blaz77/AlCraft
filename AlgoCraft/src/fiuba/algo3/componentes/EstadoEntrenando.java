package fiuba.algo3.componentes;

import fiuba.algo3.ocupantes.edificios.EdificioEntrenadorUnidades;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.unidades.constructores.Constructor;

public class EstadoEntrenando implements Estado {

	Constructor constructor;
	EdificioEntrenadorUnidades entrenador;
	int turnosRestantes;
	
	public EstadoEntrenando(Constructor constructor){
		this.constructor = constructor;
		this.turnosRestantes = constructor.getTurnosConstruccion();
	}
	
	public void activar(ObjetoVivo portador) {
		this.entrenador = (EdificioEntrenadorUnidades)portador;
	}

	public void pasarTurno() throws Exception {
		this.turnosRestantes--;
		if (this.turnosRestantes == 0) {
			throw new Exception();
		}
	}

	public void desactivar() {
		this.constructor.liberarUnidad(entrenador.getPropietario(), entrenador.getPosicion());
		this.entrenador.proximoEntrenamiento();
	}

}
