package fiuba.algo3.terreno;

import fiuba.algo3.ocupantes.Ocupante;

// La idea de esto es eliminar las clases Terreno e hijas,
// pues tenian poco comportamiento y nada de estado que
// ameritara tener instancias de las mismas.

// TODO: creo q esto lo borramos xq no permite agregar moho
// zerg. Lo dejo comentado x ahora, ya veremos.
public enum TipoTerreno {
	
	
	TIERRA {
		public boolean puedeSerOcupada(Ocupante ocupante) {
			return ocupante.puedeOcuparTierra();
		}
	}, 
	ESPACIO {
		public boolean puedeSerOcupada(Ocupante ocupante) {
			return ocupante.puedeOcuparEspacio();
		}
	},
	
	/*
	MOHO {
		
		public boolean puedeSerOcupada(Ocupante ocupante) {
			return ocupante.puedeOcuparMoho();
		}

	},
	*/	
	
	SOMBRA{
		
		public boolean puedeSerOcupada(Ocupante ocupante){
			return false;
		}
	};
	
	public abstract boolean puedeSerOcupada(Ocupante ocupante);
	
}