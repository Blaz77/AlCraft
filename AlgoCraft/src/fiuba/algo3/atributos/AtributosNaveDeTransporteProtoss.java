package fiuba.algo3.atributos;

import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;

public class AtributosNaveDeTransporteProtoss extends AtributosUnidadTransporte {
	
	public AtributosNaveDeTransporteProtoss() {
		this.costoMineral = 200;
		this.costoGasVespeno = 0;
		this.turnosConstruccion = 8;
		

		this.costoPoblacion = 2;
		
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
