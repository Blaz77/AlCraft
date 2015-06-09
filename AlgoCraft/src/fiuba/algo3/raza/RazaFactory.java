package fiuba.algo3.raza;

public class RazaFactory {
	// Un hashmap es mas portable y modificable que una cadena de ifs.
	// TODO despues lo agrego. Emi.
	
	public static Raza getRaza(TipoRaza tipoRaza) {
			return tipoRaza.getRaza();
	}
}
