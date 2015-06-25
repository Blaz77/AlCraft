package fiuba.algo3.componentes;

import fiuba.algo3.atributos.AtributosRecolector;
import fiuba.algo3.excepciones.EstadoFinalizado;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.recurso.GasVespeno;

public class EstadoRecolectandoGasVespeno implements Estado {
	
	private Posicion posicion;
	private Jugador beneficiario;
	private AtributosRecolector atributos;
	
	public EstadoRecolectandoGasVespeno(AtributosRecolector atributos) {
		this.atributos = atributos;
	}
	
	public void activar(ObjetoVivo portador) {
		this.beneficiario = portador.getPropietario();
		this.posicion = portador.getPosicion();
	}

	public void pasarTurno() throws EstadoFinalizado {
		this.beneficiario.agregarGasVespeno(
				this.atributos.getCantARecolectarPorTurno());
	}
	
	public void desactivar() {
		this.beneficiario.getMapa().reemplazar(this.posicion, new GasVespeno(this.posicion));
	}
	
	@Override
	public String getDescripcion() {
		return String.format("Recolectando Gas Vespeno: %d por turno.", atributos.getCantARecolectarPorTurno());
	}

}
