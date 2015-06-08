package fiuba.algo3.atributos;

import fiuba.algo3.componentes.EstadoRecolectandoMineral;

public abstract class AtributosEdificioRecolectorMineral extends AtributosEdificioRecolector {
	
	public AtributosEdificioRecolectorMineral() {
		estadosIniciales.add(new EstadoRecolectandoMineral());
	}
	
	@Override
	public boolean edificableSobreMineral(){
		return true;
	}
	
}