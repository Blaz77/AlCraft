package fiuba.algo3.edificios;

import fiuba.algo3.componentes.Trabajo;
import fiuba.algo3.componentes.TrabajoNull;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public abstract class Edificio extends ObjetoVivo {

	// 2 Opciones: 
	// - Herencia Edificio -> EdificioConstructor -> Barracas, etc.
	
	// - Edificio generico con estados internos - modificables, si fuese necesario-
	// El constructor de unidades va de la mano con estos estados.

	protected Trabajo estado = new TrabajoNull(); //En construccion/Trabajando (no se puede accionar) /En espera (permite acciones)
	
	public void pasarTurno(){
		this.estado = estado.pasarTurno();
	}
	
	//private Trabajo porDefecto;
	//private Ataque ataque;
	//private Movimiento mov;
	//private Almacenador alm;
	
	public abstract int getCostoMineral();
	public abstract int getCostoGas();
	//public abstract int getVidaMax();
	//public abstract int getTurnosNecesarios();

	
	public Edificio(Jugador propietario, Posicion posicion) {
		super(propietario, posicion);
		//this.trabajoActual = primerTrabajo; generalmente una Construccion
	}
	
	public void setTrabajo(Trabajo nuevoTrabajo){
		nuevoTrabajo.setAnterior(this.estado);
		this.estado = nuevoTrabajo;
	}
	
	public void construccionFinalizada() {
		// Se ejecuta al terminar la construccion (Puede dar un aviso,
		// desbloquear otros edificios o aumentar poblacion)
		return;
	}

	public boolean puedeEntrenarUnidades(){
		// Devolver true aca implica la obligacion de implementar IEntrenador
		return false;
	}
	
	
	
	//public boolean puedeAlmacenarUnidades(){}
		
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
