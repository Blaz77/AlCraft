package fiuba.algo3.componentes;

public class EstadoRecolectandoMineral extends EstadoRecolectando {

	public void pasarTurno() throws Exception {
		(this.beneficiario).agregarMinerales(
				this.recolector.getCantARecolectarPorTurno());
	}

}
