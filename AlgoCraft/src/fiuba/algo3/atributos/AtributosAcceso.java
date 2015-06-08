package fiuba.algo3.atributos;

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
		}

	@Override
	public boolean tieneEscudo() {
		return true;
	}
}