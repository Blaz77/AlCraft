package fiuba.algo3.atributos;

import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;

public class AtributosNexoMineral extends
		AtributosEdificioRecolectorMineral {
	
	public AtributosNexoMineral() {
		// fields ObjetoVivo:
		this.costoMineral = 50;
		this.costoGasVespeno = 0;
		this.turnosConstruccion = 4;
		this.vidaMaxima = 250;
		// this.escudoMaximo = 250; ?
		this.nombre = "Nexo Mineral";
		
		// fields EdificioRecolectorMineral
		this.cantARecolectarPorTurno = 10;
		
		estadosIniciales.add(new EstadoRegenerandoEscudo());
	}
	
	@Override
	public IVida getVida() {
		return new VidaConEscudo(this);
	}
	@Override
	public boolean tieneEscudo() {
		return true;
	}
}
