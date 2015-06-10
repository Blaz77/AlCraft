package fiuba.algo3.atributos.edificios.protoss;

import fiuba.algo3.atributos.edificios.AtributosEdificioRecolectorGasVespeno;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;

public class AtributosAsimilador extends AtributosEdificioRecolectorGasVespeno {

	public AtributosAsimilador() {
		// fields ObjetoVivo:
		this.costoMineral = 100;
		this.costoGasVespeno = 0;
		this.turnosConstruccion = 6;
		this.vidaMaxima = 450;
		//this.escudoMaximo = 450; ?
		this.nombre = "Asimilador";
		
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
