package fiuba.algo3.A_BORRAR;

import fiuba.algo3.ocupantes.Ocupante;
import fiuba.algo3.terreno.Terreno;
import fiuba.algo3.terreno.TipoTerreno;

public class Tierra extends Terreno {
	
	public TipoTerreno getTipo() {
		return TipoTerreno.TIERRA;
	}
	
	@Override
	public boolean puedeSerOcupada(Ocupante ocupante) {
		return ocupante.puedeOcuparTierra();
	}

}
