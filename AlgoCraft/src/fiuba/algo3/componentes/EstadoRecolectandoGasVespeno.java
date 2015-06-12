package fiuba.algo3.componentes;

import fiuba.algo3.atributos.AtributosRecolector;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.ocupantes.ObjetoVivo;

public class EstadoRecolectandoGasVespeno implements Estado {
	
	private Jugador beneficiario;
	private AtributosRecolector atributos;
	
	public EstadoRecolectandoGasVespeno(AtributosRecolector atributos) {
		this.atributos = atributos;
	}
	
	public void activar(ObjetoVivo portador) {
		this.beneficiario = portador.getPropietario();
	}

	public void pasarTurno() throws Exception {
		this.beneficiario.agregarGasVespeno(
				this.atributos.getCantARecolectarPorTurno());
	}
	
	public void desactivar() {}

}
