package fiuba.algo3.tp_final;

import fiuba.algo3.mapa.Mapa;

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
	private Mapa mapaPropio;

	public Jugador(TipoRaza raza, Color color, Mapa mapa) {
		RazaFactory razaFactory = new RazaFactory();
		
		this.color = color;
		this.raza = razaFactory.getRaza(raza);
		
		this.minerales = 200;
		this.gasVespeno = 50;
		this.poblacion = 0;
		this.mapaPropio = mapa;
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
	
	public Mapa getMApa() {
		return this.mapaPropio;
	}

	public int getGasVespeno() {
		return this.gasVespeno;
	}

}
