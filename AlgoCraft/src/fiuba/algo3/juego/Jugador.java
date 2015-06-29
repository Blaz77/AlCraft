package fiuba.algo3.juego;

import java.util.ArrayList;

import fiuba.algo3.atributos.jugador.AtributosJugador;
import fiuba.algo3.componentes.Costo;
import fiuba.algo3.excepciones.GasVespenoInsuficiente;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.SuministroInsuficiente;
import fiuba.algo3.factories.EdificiosFactory;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.raza.Raza;
import fiuba.algo3.raza.RazaFactory;
import fiuba.algo3.raza.TipoRaza;


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
	private Mapa mapaPropio;
	private ArrayList<Unidad> unidades = new ArrayList<Unidad>();
	private ArrayList<Edificio> edificios = new ArrayList<Edificio>();
	private String nombre;

	public Jugador(String nombre, Color color, TipoRaza raza, Mapa mapa) { // MapaProxy? 
		
		this.color = color;
		this.raza = RazaFactory.getRaza(raza);
		this.nombre = nombre;
		
		// Por ahora estos numeros magicos funcionan, despues se ve si
		// extraerlos en forma de constantes o pasados x parametro
		// (en caso de implementar un menu de 'opciones' antes de jugar)
		this.poblacionCapacidad = POB_MAX_INICIAL;
		this.minerales = MINERALES_INICIAL;
		this.gasVespeno = VESPENO_INICIAL;
		this.poblacion = 0;
		this.mapaPropio = mapa;
	}

	public Color getColor() {
		return color;
	}
	
	public AtributosJugador getAtributos(){
		return this.raza.getAtributos();
	}

	public TipoRaza getRaza() {
		return raza.getTipoRaza();
	}
	
	public EdificiosFactory getEdificador() {
		return this.raza.getEdificador();
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getMinerales(){
		return this.minerales;
	}
	
	/* Verifica si hay recursos y poblacion necesaria para crear la unidad.
	 * Si no se cumple, lanza una excepcion
	 */
	public void comprar(Costo costo){
		if (this.minerales < costo.getCostoMineral()) 
			throw new MineralInsuficiente(costo.getCostoMineral(), this.minerales);
		if (this.gasVespeno < costo.getCostoGasVespeno()) 
			throw new GasVespenoInsuficiente(costo.getCostoGasVespeno(), this.gasVespeno);
		if ((this.poblacion + costo.getCostoPoblacion()) > this.poblacionCapacidad)
			throw new SuministroInsuficiente(raza.getAtributos().getIncrementadorPoblacion().getNombre());
		this.minerales -= costo.getCostoMineral();
		this.gasVespeno -= costo.getCostoGasVespeno();
		this.poblacion += costo.getCostoPoblacion();
	}

	// Acepta numeros negativos
	public void agregarMinerales(int cantidad) {
		this.minerales += cantidad;
	}
	
	// Acepta numeros negativos
	public void agregarGasVespeno(int cantidad){
		this.gasVespeno += cantidad;
	}

	public void aumentarPoblacion(int i) {
		this.poblacion += i;		
	}
	
	public void aumentarCapacidadPoblacion(int i) {
		this.poblacionCapacidad += i;
		this.poblacionCapacidad = poblacionCapacidad > POB_MAX_TOTAL? POB_MAX_TOTAL: poblacionCapacidad;
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

	public ArrayList<Edificio> getEdificios() {
		return this.edificios;
	}
	
	public ArrayList<Unidad> getUnidades() {
		return this.unidades;
	}

	public void agregarEdificio(Edificio edificio) {
		this.edificios.add(edificio);
	}
	
	public void agregarUnidad(Unidad unidad){
		this.unidades.add(unidad);
	}

	public void removerEdificio(Edificio edificio) {
		this.edificios.remove(edificio);
	}
	
	public void removerUnidad(Unidad unidad) {
		this.unidades.remove(unidad);
	}
	
	public void pasarTurno(){
		for (Unidad unidad: new ArrayList<Unidad>(unidades))
			unidad.pasarTurno();
		for (Edificio edificio: new ArrayList<Edificio>(edificios))
			edificio.pasarTurno();
	}
}
