package fiuba.algo3.componentes;

import fiuba.algo3.excepciones.EstadoFinalizado;
import fiuba.algo3.ocupantes.ObjetoVivo;

public class EstadoRegenerandoEscudo implements Estado {

	private ObjetoVivo portador;
	
	public void activar(ObjetoVivo portador) {
		this.portador = portador;
	}

	public void pasarTurno() throws EstadoFinalizado {
		//REGENERA 10% POR TURNO
		portador.regenerarEscudo((portador.getEscudoMaximo() / 10));
	}
	
	public void desactivar() {	}

	@Override
	public String getDescripcion() {
		return String.format("Regenerando escudo: %d por turno.", (int)portador.getEscudoMaximo()/10);
	}

}
