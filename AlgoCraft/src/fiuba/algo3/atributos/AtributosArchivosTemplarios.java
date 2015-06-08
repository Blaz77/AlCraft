package fiuba.algo3.atributos;

import fiuba.algo3.unidades.ConstructorGolliat;

public class AtributosArchivosTemplarios extends AtributosEdificioEntrenadorUnidades {

	public AtributosArchivosTemplarios() {
		// fields ObjetoVivo:
		this.costoMineral = 150;
		this.costoGasVespeno = 200;
		this.vidaMaxima = 500; 
		//this.escudoMaximo = 500; ?
		this.nombre = "Archivos Templarios";
		
		// fields EdificioEntrenadorUnidades:
		this.unidadesEntrenables.add(new ConstructorGolliat());
	}

	@Override
	public boolean tieneEscudo() {
		return true;
	}
}
