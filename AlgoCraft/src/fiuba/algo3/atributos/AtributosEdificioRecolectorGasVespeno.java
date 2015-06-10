package fiuba.algo3.atributos;

import fiuba.algo3.componentes.EstadoRecolectandoGasVespeno;

public abstract class AtributosEdificioRecolectorGasVespeno extends AtributosEdificioRecolector {
	
	public AtributosEdificioRecolectorGasVespeno() {
		estadosIniciales.add(new EstadoRecolectandoGasVespeno());
	}

	
	@Override
	public boolean debeOcuparGasVespeno(){
		return true;
	}
	
}