package fiuba.algo3.juego;

import java.util.ArrayList;

import fiuba.algo3.atributos.Atributos;
import fiuba.algo3.edificios.Edificio;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.MapaProxy;
import fiuba.algo3.raza.Raza;
import fiuba.algo3.raza.RazaFactory;
import fiuba.algo3.raza.TipoRaza;
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
	

	private final static int POB_MAX_INICIAL = 5;
	private final static int POB_MAX_TOTAL = 200;
	private final static int MINERALES_INICIAL = 200;
	private final static int VESPENO_INICIAL = 50;

	private Color color;
	private Raza raza;
	private int minerales;
	private int gasVespeno;
	private int poblacion;
	private int poblacionCapacidad; // Depende de los edificios correspondientes.
	private MapaProxy mapaPropio;
	private ArrayList<Unidad> unidades = new ArrayList<Unidad>();
	private ArrayList<Edificio> edificios = new ArrayList<Edificio>();

	public Jugador(TipoRaza raza, Color color, Mapa mapa) { // MapaProxy? 
		RazaFactory razaFactory = new RazaFactory();
		
		this.color = color;
		this.raza = razaFactory.getRaza(raza);
		
		// Por ahora estos numeros magicos funcionan, despues se ve si
		// extraerlos en forma de constantes o pasados x parametro
		// (en caso de implementar un menu de 'opciones' antes de jugar)
		this.poblacionCapacidad = POB_MAX_INICIAL;
		this.minerales = MINERALES_INICIAL;
		this.gasVespeno = VESPENO_INICIAL;
		this.poblacion = 0;
		this.mapaPropio = new MapaProxy(mapa);
	}

	public Color getColor() {
		return color;
	}
	
	public Atributos getAtributos(){
		return this.raza.getAtributos();
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
	
	public MapaProxy getMapa() {
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
	
	public void pasarTurno(){
		for (Unidad unidad: unidades)
			unidad.pasarTurno();
		for (Edificio edificio: edificios)
			edificio.pasarTurno();
	}
}
