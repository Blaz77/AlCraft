package fiuba.algo3.componentes;

import fiuba.algo3.edificios.Edificio;

public class EstadoConstruyendoEdificio implements Estado {
	
	int cantVidaSumadaPorTurno;
	Edificio construccion;
	
	public EstadoConstruyendoEdificio(int turnosParaCompletar, Edificio construccion) {
		this.cantVidaSumadaPorTurno = (int) Math.ceil(construccion.getVidaMaxima() / (double) turnosParaCompletar);
		this.construccion = construccion;
	}

	public void activar() {
		// TODO Auto-generated method stub

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
