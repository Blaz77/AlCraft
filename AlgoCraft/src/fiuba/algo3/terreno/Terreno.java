package fiuba.algo3.terreno;

import fiuba.algo3.ocupantes.Ocupante;

public enum Terreno {//probablemente interfaz
	
	TIERRA {
		public boolean puedeSerOcupada(Ocupante ocupante) {
			return ocupante.puedeOcuparTierra();
		}
		
		public String getNombre(){
			return "Tierra";
		}
	}, 
	ESPACIO {
		public boolean puedeSerOcupada(Ocupante ocupante) {
			return ocupante.puedeOcuparEspacio();
		}
		
		public String getNombre(){
			return "Espacio";
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
		
		public String getNombre(){
			return "Sombra";
		}
	};
	
	public abstract boolean puedeSerOcupada(Ocupante ocupante);
	
	public abstract String getNombre();
}
