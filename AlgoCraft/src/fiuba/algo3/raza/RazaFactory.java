package fiuba.algo3.raza;

public class RazaFactory {
	
	public static Raza getRaza(TipoRaza tipoRaza) {
			return tipoRaza.getRaza();
	}
}
