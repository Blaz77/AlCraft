package fiuba.algo3.componentes;

import fiuba.algo3.atributos.AtributosIncrementadorPoblacion;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.ocupantes.edificios.EdificioIncrementadorPoblacion;
import fiuba.algo3.ocupantes.ObjetoVivo;

public class EstadoSuministrandoPoblacion implements Estado {

	private Jugador beneficiario;
	private AtributosIncrementadorPoblacion atributos;
	
	public EstadoSuministrandoPoblacion(AtributosIncrementadorPoblacion atributos) {
		this.atributos = atributos;
	}
	
	public Estado clone() {
		return new EstadoSuministrandoPoblacion(atributos);
	}
	
	public void activar(ObjetoVivo portador) {
		this.beneficiario = portador.getPropietario();
		this.beneficiario.aumentarCapacidadPoblacion(atributos.getIncrementoDePoblacion());
	}

	public void pasarTurno() throws Exception {}

	public void desactivar() {
		this.beneficiario.aumentarCapacidadPoblacion(-(atributos.getIncrementoDePoblacion()));
	}

}
