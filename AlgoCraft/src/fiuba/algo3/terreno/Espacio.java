package fiuba.algo3.terreno;

import fiuba.algo3.ocupantes.Ocupante;

public class Espacio extends Terreno {
	
	public TipoTerreno getTipo() {
		return TipoTerreno.ESPACIO;
	}

	@Override
	public boolean puedeSerOcupada(Ocupante ocupante) {
		return ocupante.puedeOcuparEspacio();
	}
	
}
