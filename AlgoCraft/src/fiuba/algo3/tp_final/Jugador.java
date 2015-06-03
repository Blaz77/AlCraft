package fiuba.algo3.tp_final;

//import java.util.ArrayList;

public class Jugador {
	private Color color;
	private Raza raza;
	private int minerales = 0; //luego pasar a constructor de Jugador
	private int gasVespeno = 0; //luego pasar a constructor de Jugador

	public Jugador(TipoRaza raza, Color color) {
		RazaFactory razaFactory = new RazaFactory();
		
		this.color = color;
		this.raza = razaFactory.getRaza(raza);
	}

	public Color getColor() {
		return color;
	}

	public TipoRaza getRaza() {
		return raza.getTipoRaza();
	}
	
	public int getMinerales(){
		return this.minerales;
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
