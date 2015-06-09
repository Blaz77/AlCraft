package fiuba.algo3.atributos;

import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;

public class AtributosDragon extends AtributosUnidadAtaque {

	public AtributosDragon(){
		
		this.costoGasVespeno = 50;
		this.costoMineral = 125;
		this.costoPoblacion = 2;
		this.turnosConstruccion = 6;
		
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
