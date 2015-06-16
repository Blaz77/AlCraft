package fiuba.algo3.A_BORRAR;

import fiuba.algo3.atributos.AtributosRecolector;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.ocupantes.ObjetoVivo;

public abstract class EstadoRecolectando implements Estado {

	Jugador beneficiario;
	AtributosRecolector atributos;
	
	public EstadoRecolectando(AtributosRecolector atributos) {
		this.atributos = atributos;
	}
	
	public void activar(ObjetoVivo portador) {
		this.beneficiario = portador.getPropietario();
	}

	public void desactivar() {}

}
