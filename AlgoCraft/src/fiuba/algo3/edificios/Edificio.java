package fiuba.algo3.edificios;

import java.util.ArrayList;

import fiuba.algo3.juego.Atacable;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.unidades.Constructor;

public abstract class Edificio { //implements Atacable

	// 2 Opciones: 
	// - Herencia Edificio -> EdificioConstructor -> Barracas, etc.
	
	// - Edificio generico con estados internos - modificables, si fuese necesario-
	// El constructor de unidades va de la mano con estos estados.

	protected String nombre;
	protected Jugador propietario;
	private int x;
	private int y;
	protected Vida vida = new VidaNull();
	protected Trabajo trabajo = new TrabajoNull(); //En construccion/Trabajando (no se puede accionar) /En espera (permite acciones)
	protected EntrenadorUnidades entrenador = new EntrenadorUnidadesNull();
	
	//private Trabajo porDefecto;
	//private Ataque ataque;
	//private Movimiento mov;
	//private Almacenador alm;
	
	
	
	public Edificio(Jugador propietario, int x, int y) {
		this.propietario = propietario;
		this.x = x;
		this.y = y;
		//this.trabajoActual = primerTrabajo; generalmente una Construccion
	}
	
	public Jugador getPropietario(){
		return this.propietario;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getVida() {
		return this.vida.getVida();
	}
	
	public void regenerarVida(int puntos) {
		this.vida.regenerar(puntos);
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setVida(Vida vida) {
		this.vida = vida;		
	}

	public int getVidaMaxima() {
		return this.vida.getVidaMaxima();
	}
	
	public void recibirDanio(int puntos){
		this.vida.recibirDanio(puntos);
	}
	
	public void setTrabajo(Trabajo nuevoTrabajo){
		nuevoTrabajo.setAnterior(this.trabajo);
		this.trabajo = nuevoTrabajo;
	}
	
	// Manejar veneno. Alguna interfaz para esto
	public void pasarTurno() {
		this.trabajo = this.trabajo.pasarTurno();
	}
	
	public boolean puedeEntrenarUnidades(){
		return false;
	}
	
	public ArrayList<Constructor> getUnidadesEntrenables(){
		return this.entrenador.getUnidadesEntrenables();
	}

	public void construccionFinalizada() {
		// Se ejecuta al terminar la construccion (Puede dar un aviso,
		// desbloquear otros edificios o aumentar poblacion)
		return;
	}

		
	//}
	// con lo que devuelve el de arriba uno haria: unidadConstructor.crear();


	/*
	 * 
	 * PLANEADO QUIZAS PARA PARTE OPCIONAL QUE HAY EDIFICIOS DEFENSIVOS!!!!!
	 * ->utilizar el Ataque (componente generico) que se definira para Unidades
	 * 
	 * public boolean puedeAtacar();
	 * 
	 * public void atacarA(Edificio/Unidad)
	 * 
	 * public Lista<ataques> getListaDeAtaques();
	 */
	
	/*
	 * 
	 * PLANEADO QUIZAS PARA PARTE OPCIONAL QUE HAY EDIFICIOS QUE SE MUEVEN!!!!!
	 * -> utilizar el Movimiento (comp. generico) que se definira para Unidades
	 * 
	 * public boolean puedeMoverse();
	 * 
	 * public void moverseA(Edificio/Unidad)
	 * 
	 * public Lista<movimientos> getListaDeMovimientos();
	 */
	
	/*
	 * 
	 * PLANEADO QUIZAS PARA PARTE OPCIONAL QUE HAY EDIFICIOS QUE Guardan Unidades!!!!!
	 * -> utilizar el AlmacenadorUnidades (comp. generico) que se definira para Unidades
	 * 
	 * public boolean puedeAlmacenarUnidades();
	 * 
	 * public void almacenarA(Unidad)
	 * 
	 * */
	
	
}
