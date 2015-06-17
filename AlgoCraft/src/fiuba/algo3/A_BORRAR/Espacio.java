package fiuba.algo3.A_BORRAR;

import fiuba.algo3.ocupantes.Ocupante;
import fiuba.algo3.terreno.Terreno;
import fiuba.algo3.terreno.TipoTerreno;

public class Espacio extends Terreno {

	public TipoTerreno getTipo() {
		return TipoTerreno.ESPACIO;
	}
	
	@Override
	public boolean puedeSerOcupada(Ocupante ocupante) {
		//return getTipo().puedeSerOcupada(ocupante);//ocupante.puedeOcuparEspacio();
		return ocupante.puedeOcuparEspacio();
	}
	
}
