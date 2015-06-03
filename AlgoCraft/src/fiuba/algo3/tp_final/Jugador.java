package fiuba.algo3.tp_final;

//import java.util.ArrayList;

public class Jugador {
	private Color color;
	private Raza raza;
	private int minerales;
	private int gasVespeno;
	//quizas hacer Objeto Poblacion?
	//poblacionMax = 200;
	//poblacionlibre = x;
	private int poblacion;

	public Jugador(TipoRaza raza, Color color) {
		RazaFactory razaFactory = new RazaFactory();
		
		this.color = color;
		this.raza = razaFactory.getRaza(raza);
		
		this.minerales = 200;
		this.gasVespeno = 50;
		this.poblacion = 0;
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

	public void aumentarPoblacion(int i) {
		this.poblacion += i;		
	}
	
	public int getPoblacion(){
		return this.poblacion;
	}

}
