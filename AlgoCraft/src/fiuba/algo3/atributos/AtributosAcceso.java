package fiuba.algo3.atributos;

import fiuba.algo3.unidades.ConstructorMarine;

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
		this.unidadesEntrenables.add(new ConstructorMarine());
	}

	@Override
	public boolean tieneEscudo() {
		return true;
	}
}
