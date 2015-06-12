package fiuba.algo3.componentes;

import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.edificios.Edificio;

public class EstadoConstruyendoEdificio implements Estado {
	
	int cantVidaSumadaPorTurno;
	Edificio edificio;
	
	public void activar(ObjetoVivo portador) {
		this.edificio = (Edificio) portador;
		this.cantVidaSumadaPorTurno = 
				(int) Math.ceil(edificio.getVidaMaxima() / 
								(double) edificio.getTurnosConstruccion());
		
	}

	public void pasarTurno() throws Exception {
		edificio.regenerarVida(cantVidaSumadaPorTurno);
		if (edificio.getVida() == edificio.getVidaMaxima()) {
			throw new Exception();
		}
	}

	public void desactivar() {
		edificio.construccionFinalizada();
	}

}
