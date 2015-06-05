package fiuba.algo3.raza;

public class RazaFactory {
	// Un hashmap es mas portable y modificable que una cadena de ifs.
	// TODO despues lo agrego. Emi.
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
