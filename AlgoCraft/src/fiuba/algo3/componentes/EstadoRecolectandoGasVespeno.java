package fiuba.algo3.componentes;

public class EstadoRecolectandoGasVespeno extends EstadoRecolectando {

	public void pasarTurno() throws Exception {
		(this.beneficiario).agregarGasVespeno(
				this.recolector.getCantARecolectarPorTurno());
	}

}
