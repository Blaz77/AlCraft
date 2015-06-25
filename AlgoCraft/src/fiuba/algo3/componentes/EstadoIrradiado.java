package fiuba.algo3.componentes;

import fiuba.algo3.excepciones.EstadoFinalizado;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.unidades.Unidad;

public class EstadoIrradiado implements Estado {

	private final int distancia = 1;
	private ObjetoVivo portador;
	private final float factorPortador = 0.10f;
	private final float factorCercanos = 0.05f;
	
	private int getDanio(int danio, float factor){
		return Math.max((int) (danio * factor), 1);
	}
	
	public void activar(ObjetoVivo portador) {
		this.portador = portador;
		this.portador.recibirDanio(getDanio(portador.getVidaMaxima(), factorPortador));
	}

	public void pasarTurno() throws EstadoFinalizado {
		this.portador.recibirDanio(getDanio(portador.getVidaMaxima(), factorPortador));
		
		for (Unidad unidad : portador.getPropietario().getMapa().
				getUnidadesEnRango(portador.getPosicion(), distancia)) {
			if (unidad == portador) continue;
			unidad.recibirDanio(getDanio(unidad.getVidaMaxima(), factorCercanos));
		}
	}

	public void desactivar() {}

	@Override
	public String getDescripcion() {
		return String.format("Irradiado!: recibiendo %d daño por turno.",(int) (portador.getVidaMaxima() * 0.10));
	}

}
