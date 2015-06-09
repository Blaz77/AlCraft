package fiuba.algo3.componentes;

import fiuba.algo3.edificios.ObjetoVivo;

public class EstadoRegenerandoEscudo implements Estado {

	private ObjetoVivo portador;
	
	public void activar(ObjetoVivo portador) {
		this.portador = portador;
	}

	public void pasarTurno() throws Exception {
		//REGENERA 10% POR TURNO, SE PUEDE CAMBIAR, VER BIEN EL REAL
		portador.regenerarEscudo((portador.getEscudoMaximo() / 10));
	}
	
	public void desactivar() {	}

}
