package fiuba.algo3.atributos;

import fiuba.algo3.unidades.ConstructorInfanteriaMagica;

public class AtributosArchivosTemplarios extends AtributosEdificioEntrenadorUnidades {

	public AtributosArchivosTemplarios() {
		// fields ObjetoVivo:
		this.costoMineral = 150;
		this.costoGasVespeno = 200;
		this.turnosConstruccion = 9;
		this.vidaMaxima = 500; 
		//this.escudoMaximo = 500; ?
		this.nombre = "Archivos Templarios";
		
		// fields EdificioEntrenadorUnidades:
		this.unidadesEntrenables.add(new ConstructorInfanteriaMagica());
	}

	@Override
	public boolean tieneEscudo() {
		return true;
	}
}
