package fiuba.algo3.componentes;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.edificios.EdificioRecolector;
import fiuba.algo3.ocupantes.ObjetoVivo;

public abstract class EstadoRecolectando implements Estado {

	protected EdificioRecolector recolector;
	protected Jugador beneficiario;
	
	public void activar(ObjetoVivo portador) {
		this.recolector = (EdificioRecolector)portador;
		this.beneficiario = portador.getPropietario();
	}

	public void desactivar() {}

}
