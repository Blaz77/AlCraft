package fiuba.algo3.juego;

import java.util.ArrayList;

import fiuba.algo3.atributos.jugador.AtributosJugador;
import fiuba.algo3.excepciones.GasVespenoInsuficiente;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.SuministroInsuficiente;
import fiuba.algo3.excepciones.UnidadNoPropia;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.MapaReal;
import fiuba.algo3.mapa.MapaProxy;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.raza.Raza;
import fiuba.algo3.raza.RazaFactory;
import fiuba.algo3.raza.TipoRaza;

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
	//private final static int POB_MAX_TOTAL = 200;
	private final static int MINERALES_INICIAL = 200;
	private final static int VESPENO_INICIAL = 50;

	private Color color;
	private Raza raza;
	private int minerales;
	private int gasVespeno;
	private int poblacion;
	private int poblacionCapacidad; // Depende de los edificios correspondientes.
	private MapaProxy mapaPropio;
	private Posicion posicionInicial;
	private ArrayList<Unidad> unidades = new ArrayList<Unidad>();
	private ArrayList<Edificio> edificios = new ArrayList<Edificio>();
	private String nombre;

	public Jugador(String nombre, Color color, TipoRaza raza, MapaReal mapa) { // MapaProxy? 
		
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
		this.mapaPropio = new MapaProxy(mapa, unidades);
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
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getMinerales(){
		return this.minerales;
	}
	
	// nombre pendiente de modificacion
	public void comprar(int costoMineral, int costoGasVespeno){
		if (this.minerales < costoMineral) throw new MineralInsuficiente();
		if (this.gasVespeno < costoGasVespeno) throw new GasVespenoInsuficiente();
		this.minerales -= costoMineral;
		this.gasVespeno -= costoGasVespeno;
	}
	
	/* Verifica si hay recursos y poblacion necesaria para crear la unidad.
	 * Si no se cumple, lanza una excepcion
	 */
	// nombre pendiente de modificacion
	public void comprar(int costoMineral, int costoGasVespeno, int costoPoblacion){
		if ((this.poblacion + costoPoblacion) > this.poblacionCapacidad)
			throw new SuministroInsuficiente();
		this.comprar(costoMineral, costoGasVespeno); //hace chequeos
		//si paso aumento la poblacion:
		this.poblacion += costoPoblacion;
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
	
	public Posicion getPosicionInicial() {
		return this.posicionInicial;
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
	
	public void mover(Unidad unidad, Posicion destino){
		if (! unidades.contains(unidad)) // medio lento este chequeo.
			throw new UnidadNoPropia();
		mapaPropio.mover(unidad, destino);
	}
	
	public void pasarTurno(){
		for (Unidad unidad: new ArrayList<Unidad>(unidades))
			unidad.pasarTurno();
		for (Edificio edificio: new ArrayList<Edificio>(edificios))
			edificio.pasarTurno();
	}

	public void setPosicionInicial(Posicion posicionInicial) {
		this.posicionInicial = posicionInicial;
		this.mapaPropio.setPuntoOrigen(posicionInicial);
	}

}
