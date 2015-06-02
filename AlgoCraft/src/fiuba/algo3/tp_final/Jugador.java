package fiuba.algo3.tp_final;

//import java.util.ArrayList;

public class Jugador {
	private Color color;
	private Raza raza;
	private int minerales;
	private int gasVespeno;

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

	public void agregarMinerales(int cantidad) {
		//si cantidad es negativo levantar excepcion?
		this.minerales += cantidad;
	}
	
	public void agregarGasVespeno(int cantidad){
		//si cantidad es negativo levantar excepcion?
		this.gasVespeno += cantidad;
	}

}
