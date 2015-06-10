package fiuba.algo3.utilidades;

public class Utilidades {
	public static boolean isDigit(String myString) {
		for (char c : myString.toCharArray())
			if (!Character.isDigit(c))
				return false;
		return true;
	}
}
