package fiuba.algo3.tp_final;

public class RazaFactory {
	
	public Raza getRaza(TipoRaza raza) {
		if (raza == TipoRaza.TERRAN) {
			return new RazaTerran();
		}
		else if (raza == TipoRaza.PROTOSS) {
			return new RazaProtoss();
		}
		else {
			throw new RuntimeException();
		}
	}
}
