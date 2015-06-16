package fiuba.algo3.componentes;

import fiuba.algo3.atributos.AtributosRecolector;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.recurso.Mineral;

public class EstadoRecolectandoMineral implements Estado {

	private Posicion posicion;
	private Jugador beneficiario;
	private AtributosRecolector atributos;
	
	public EstadoRecolectandoMineral(AtributosRecolector atributos) {
		this.atributos = atributos;
	}
	
	public void activar(ObjetoVivo portador) {
		this.beneficiario = portador.getPropietario();
		this.posicion = portador.getPosicion();
	}
	
	public void pasarTurno() throws Exception {
		this.beneficiario.agregarMinerales(
				this.atributos.getCantARecolectarPorTurno());
	}

	public void desactivar() {
		this.beneficiario.getMapa().reemplazar(this.posicion, new Mineral(this.posicion));
	}
	
}
