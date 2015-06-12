package fiuba.algo3.A_BORRAR;

import fiuba.algo3.componentes.EstadoRecolectandoMineral;

public abstract class AtributosEdificioRecolectorMineral extends AtributosEdificioRecolector {
	
	public AtributosEdificioRecolectorMineral() {
		estadosIniciales.add(new EstadoRecolectandoMineral());
	}
	
	@Override
	public boolean debeOcuparMineral(){
		return true;
	}
	
}