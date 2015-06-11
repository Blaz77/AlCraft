package fiuba.algo3.terreno;

import fiuba.algo3.ocupantes.Ocupante;

public class Tierra extends Terreno {
	
	public TipoTerreno getTipo() {
		return TipoTerreno.TIERRA;
	}
	
	@Override
	public boolean puedeSerOcupada(Ocupante ocupante) {
		return ocupante.puedeOcuparTierra();
	}

}
