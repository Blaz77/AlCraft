package fiuba.algo3.atributos.unidades.protoss;

import fiuba.algo3.atributos.unidades.AtributosUnidadMagica;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;

public class AtributosAltoTemplario extends AtributosUnidadMagica {
	
	public AtributosAltoTemplario(){
		this.costoMineral = 50;
		this.costoGasVespeno = 150;
		this.costoPoblacion = 2;
		this.turnosConstruccion = 7;
		
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
