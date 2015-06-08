package fiuba.algo3.raza;

public enum TipoRaza {

	TERRAN {
		public Raza getRaza() {
			return new RazaTerran();
		}
	},
	
	PROTOSS	{	
		public Raza getRaza() {
			return new RazaProtoss();
		}
	};
	
	/*
	ZERG {	
		public Raza getRaza() {
			return new RazaZerg();
		}
	};
	*/
	
	public abstract Raza getRaza();
}