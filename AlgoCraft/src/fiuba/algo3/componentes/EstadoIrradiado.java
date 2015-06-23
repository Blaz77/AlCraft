package fiuba.algo3.componentes;

import fiuba.algo3.ocupantes.ObjetoVivo;

public class EstadoIrradiado implements Estado {

	ObjetoVivo portador;
	int danio;
	
	public void activar(ObjetoVivo portador) {
		//sacar vida aca tambien?
		this.portador = portador;
		this.danio = (int) (portador.getVidaMaxima() * 0.10);
	}

	public void pasarTurno() throws Exception {
		this.portador.recibirDanio(danio);
		//TODO: FALTA EL DANIO A LAS UNIDADES PROXIMAS!
	}

	public void desactivar() {}

	@Override
	public String getDescripcion() {
		return String.format("Irradiado!: recibiendo %d daño por turno.",(int) (portador.getVidaMaxima() * 0.10));
	}

}
