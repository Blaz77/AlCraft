package fiuba.algo3.terreno;

import fiuba.algo3.ocupantes.Ocupante;

public enum Terreno {//probablemente interfaz
	
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
