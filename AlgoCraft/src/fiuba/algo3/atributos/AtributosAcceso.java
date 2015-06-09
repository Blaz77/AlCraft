package fiuba.algo3.atributos;

import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;
import fiuba.algo3.unidades.ConstructorInfanteriaLivianaTerrestre;
import fiuba.algo3.unidades.ConstructorInfanteriaPesadaTerrestre;

public class AtributosAcceso extends AtributosEdificioEntrenadorUnidades {

	public AtributosAcceso() {
		// fields ObjetoVivo:
		this.costoMineral = 150;
		this.costoGasVespeno = 0;
		this.turnosConstruccion = 8;
		this.vidaMaxima = 500;
		//this.escudoMaximo = 500; ?
		this.nombre = "Acceso";
		
		// fields EdificioEntrenadorUnidades:
		this.unidadesEntrenables.add(new ConstructorInfanteriaLivianaTerrestre());
		this.unidadesEntrenables.add(new ConstructorInfanteriaPesadaTerrestre());
		
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
