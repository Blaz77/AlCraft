package fiuba.algo3.componentes;

import fiuba.algo3.edificios.EdificioRecolector;
import fiuba.algo3.edificios.ObjetoVivo;
import fiuba.algo3.juego.Jugador;

public abstract class EstadoRecolectando implements Estado {

	protected EdificioRecolector recolector;
	protected Jugador beneficiario;
	
	public void activar(ObjetoVivo portador) {
		this.recolector = (EdificioRecolector)portador;
		this.beneficiario = portador.getPropietario();
	}

	public void desactivar() {}

}
