package fiuba.algo3.ocupantes.edificios;

import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.componentes.Estado;
import fiuba.algo3.componentes.EstadoConstruyendoEdificio;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;

public abstract class Edificio extends ObjetoVivo {
	
	private boolean construccionTerminada;
	protected AtributosEdificio atributos;
	public Edificio(Jugador propietario, Posicion posicion){
		super(propietario, posicion);
		construccionTerminada = false;
	}
	
	public Edificio(Jugador propietario, Posicion posicion,
			AtributosEdificio atributos) {
		super(propietario, posicion);
		this.atributos = atributos;
		inicializar();
	}
	
	@Override
	protected void inicializar() {
		super.inicializar();
		this.vida = new Vida(0,atributos);
		this.agregarEstado(new EstadoConstruyendoEdificio());
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
		// Devolver true aca implica la obligacion de implementar IEntrenador
		return ((AtributosEdificio) atributos).puedeEntrenarUnidades() &&
				construccionTerminada;
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
