package fiuba.algo3.tp_final;

public class Jugador {
	private Color color;
	private Raza raza;

	public Jugador(TipoRaza raza, Color color) {
		this.color = color;
		this.raza = new Raza(raza);
	}

	public Color getColor() {
		return color;
	}

	public TipoRaza getRaza() {
		return raza.getTipoRaza();
	}

}
