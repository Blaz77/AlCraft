package fiuba.algo3.componentes;

import fiuba.algo3.excepciones.EstadoFinalizado;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.TipoOcupante;

public class EstadoLiberandoConstruccionEdificio implements Estado {

	Tipo tipo;
	EdificiosFactory edificador;
	
	public EstadoLiberandoConstruccionEdificio(Tipo tipo){
		this.tipo = tipo;
		assert(tipo.getTipoOcupante() == TipoOcupante.EDIFICIO);
	}
	
	public void activar(ObjetoVivo portador) {
		this.edificador = portador.getPropietario().getEdificador();
		this.edificador.permitirConstruccionEdificio(tipo);
	}

	public void pasarTurno() throws EstadoFinalizado {}

	public void desactivar() {
		this.edificador.denegarConstruccionEdificio(tipo);
	}

	@Override
	public String getDescripcion() {
		return String.format("Permito la construccion de %s.", tipo.getNombre());
	}
	
	

}
