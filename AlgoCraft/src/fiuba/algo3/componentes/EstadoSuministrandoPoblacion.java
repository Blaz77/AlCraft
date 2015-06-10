package fiuba.algo3.componentes;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.ocupantes.edificios.EdificioIncrementadorPoblacion;
import fiuba.algo3.ocupantes.ObjetoVivo;

public class EstadoSuministrandoPoblacion implements Estado {

	private Jugador beneficiario;
	private EdificioIncrementadorPoblacion portador;
	
	public void activar(ObjetoVivo portador) {
		this.portador = (EdificioIncrementadorPoblacion)portador;
		this.beneficiario = portador.getPropietario();
		this.beneficiario.aumentarCapacidadPoblacion(this.portador.getIncrementoDePoblacion());
	}

	public void pasarTurno() throws Exception {
		
	}

	public void desactivar() {
		this.beneficiario.aumentarCapacidadPoblacion(-this.portador.getIncrementoDePoblacion());
	}

}
