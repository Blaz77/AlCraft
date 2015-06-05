package fiuba.algo3.juego;

import java.util.ArrayList;

import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.raza.Raza;
import fiuba.algo3.raza.RazaFactory;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.unidades.Constructor;
import fiuba.algo3.unidades.Unidad;

//import java.util.ArrayList;


/*
 * La Clase Jugador maneja los recursos y datos especificos 
 * del jugador (raza, color). 
 * Ademas,se encarga principalmente de limitar las acciones del 
 * usuario mediante las restricciones de poblacion maxima, rango 
 * de vision, recursos para gastar, edificios ya construidos, etc.
 */
public class Jugador {
	private Color color;
	private Raza raza;
	private int minerales;
	private int gasVespeno;
	//quizas hacer Objeto Poblacion?
	//poblacionMax = 200;
	//poblacionlibre = x;
	private int poblacion;
	private int poblacionCapacidad; // Depende de los edificios correspondientes.
	private Mapa mapaPropio;
	private ArrayList<Unidad> unidades;

	public Jugador(TipoRaza raza, Color color, Mapa mapa) { // MapaProxy? 
		RazaFactory razaFactory = new RazaFactory();
		
		this.color = color;
		this.raza = razaFactory.getRaza(raza);
		
		// Por ahora estos numeros magicos funcionan, despues se ve si
		// extraerlos en forma de constantes o pasados x parametro
		// (en caso de implementar un menu de 'opciones' antes de jugar)
		this.minerales = 200;
		this.gasVespeno = 50;
		this.poblacion = 0;
		this.poblacionCapacidad = 5;
		this.mapaPropio = mapa;
		this.unidades = new ArrayList<Unidad>();
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
	
	public void aumentarCapacidadPoblacion(int i) {
		this.poblacionCapacidad += i;		
	}
	

	public int getPoblacion(){
		return this.poblacion;
	}
	
	public int getCapacidadPoblacion(){
		return this.poblacionCapacidad;
	}
	
	public Mapa getMapa() {
		return this.mapaPropio;
	}

	public int getGasVespeno() {
		return this.gasVespeno;
	}

	public ArrayList<Unidad> getUnidades() {
		return this.unidades;
	}

	public void agregarUnidad(Unidad unidad){
		this.unidades.add(unidad);
	}
}
