package fiuba.algo3.edificios;

import fiuba.algo3.tp_final.Atacable;
import fiuba.algo3.tp_final.Jugador;

public class Edificio { //implements Atacable

	// 2 Opciones: 
	// - Herencia Edificio -> EdificioConstructor -> Barracas, etc.
	
	// - Edificio generico con estados internos - modificables, si fuese necesario-
	// El constructor de unidades va de la mano con estos estados.

	private String nombre;
	private Jugador propietario;
	private int x;
	private int y;
	private Vida vida;
	//private Trabajo porDefecto;
	private Trabajo trabajo; //En construccion/Trabajando (no se puede accionar) /En espera (permite acciones)
	
	//private Ataque ataque;
	//private Movimiento mov;
	//private Almacenador alm;
	
	
	
	public Edificio(String nombre, Jugador propietario, int x, int y) {
		this.nombre = nombre;
		this.propietario = propietario;
		this.x = x;
		this.y = y;
		//this.trabajoActual = primerTrabajo; generalmente una Construccion
	}
	
	public int getVida() {
		return this.vida.getVida();
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
	
	public void setTrabajo(Trabajo trabajo){
		this.trabajo = trabajo;
	}
	
	// Manejar veneno. Alguna interfaz para esto
	public void pasarTurno() {
		this.trabajo = this.trabajo.pasarTurno();
	}
	
	public boolean puedeEntrenarUnidades(){
		return false;
	}
	
	//				UnidadConstructor
	//public ArrayList<Unidad> getConstructorDeUnidades(){
		
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
