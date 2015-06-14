package fiuba.algo3.ocupantes.edificios;

import java.util.ArrayList;

import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.componentes.Estado;
import fiuba.algo3.componentes.EstadoConstruyendoEdificio;
import fiuba.algo3.componentes.IEntrenadorUnidades;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;
import fiuba.algo3.ocupantes.unidades.constructores.Constructor;

public class Edificio extends ObjetoVivo {
	
	private IEntrenadorUnidades entrenador;
	
	private boolean construccionTerminada;
	public Edificio(Jugador propietario, Posicion posicion){
		super(propietario, posicion);
		construccionTerminada = false;
	}
	
	public Edificio(Jugador propietario, Posicion posicion,
			AtributosEdificio atributos) {
		super(propietario, posicion);
		this.costo = atributos.getCosto();
		this.atributos = atributos;
		//this.vida = new Vida(0,atributos.getAtributosVida());
		this.vida = atributos.getVida();
		this.agregarEstado(new EstadoConstruyendoEdificio());
		propietario.agregarEdificio(this);
		//
		this.entrenador = atributos.getEntrenadorUnidades();
		this.entrenador.setPortador(this);
	}
	
	
	//private Trabajo porDefecto;
	//private Ataque ataque;
	//private Movimiento mov;
	//private Almacenador alm;
	
	//public abstract int getVidaMax();
	//public abstract int getTurnosNecesarios();

	public TipoOcupante getTipo(){
		return TipoOcupante.EDIFICIO;
	}

	public boolean puedeEntrenarUnidades(){
		return construccionTerminada && this.entrenador.puedeEntrenarUnidades();
	}
	
	//				u otro nombre
	public ArrayList<Constructor> getUnidadesEntrenables(){
		return this.entrenador.getUnidadesEntrenables();
	}
	
	public void construccionFinalizada() {
		// Se ejecuta al terminar la construccion (Puede dar un aviso,
		// desbloquear otros edificios o activar estados iniciales)
		this.estados = atributos.getEstadosIniciales();
		construccionTerminada = true;
		for (Estado estado : estados) {
			estado.activar(this);
		}
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
