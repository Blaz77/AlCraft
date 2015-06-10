package fiuba.algo3.componentes;

import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.edificios.en_construccion.Construccion;

public class EstadoConstruyendoEdificio implements Estado {
	
	int cantVidaSumadaPorTurno;
	Construccion construccion;
	
	public void activar(ObjetoVivo portador) {
		this.construccion = (Construccion)portador;
		this.cantVidaSumadaPorTurno = 
				(int) Math.ceil(construccion.getVidaMaxima() / 
								(double) construccion.getTurnosConstruccion());
		
	}

	public void pasarTurno() throws Exception {
		construccion.regenerarVida(cantVidaSumadaPorTurno);
		if (construccion.getVida() == construccion.getVidaMaxima()) {
			throw new Exception();
		}
	}

	public void desactivar() {
		construccion.construccionFinalizada();
	}

}
