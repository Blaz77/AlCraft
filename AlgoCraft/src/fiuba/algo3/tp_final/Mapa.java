package fiuba.algo3.tp_final;

public class Raza implements RazaComportamiento {
	RazaComportamiento comportamiento;
	
	public Raza(TipoRaza raza) {
		if (raza == TipoRaza.TERRAN) {
			comportamiento = new RazaTerran();
		}
		else if (raza == TipoRaza.PROTOSS) {
			comportamiento = new RazaProtoss();
		}
	}
	
	// Razas
	class RazaTerran implements RazaComportamiento {

		public Object construccionesDisponibles() {
			// TODO Auto-generated method stub
			return null;
		}

		public Object unidadesDisponibles() {
			// TODO Auto-generated method stub
			return null;
		}

		public TipoRaza getTipoRaza() {
			return TipoRaza.TERRAN;
		}
		
	}
	
	class RazaProtoss implements RazaComportamiento {

		public Object construccionesDisponibles() {
			// TODO Auto-generated method stub
			return null;
		}

		public Object unidadesDisponibles() {
			// TODO Auto-generated method stub
			return null;
		}

		public TipoRaza getTipoRaza() {
			return TipoRaza.PROTOSS;
		}
		
	}

	public Object construccionesDisponibles() {
		return comportamiento.construccionesDisponibles();
	}

	public Object unidadesDisponibles() {
		return comportamiento.unidadesDisponibles();
	}

	public TipoRaza getTipoRaza() {
		return comportamiento.getTipoRaza();
	}
}
