package fiuba.algo3.edificios;

import fiuba.algo3.atributos.AtributosEdificio;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.mapa.recurso.TipoOcupante;

public abstract class Edificio extends ObjetoVivo {
	
	public Edificio(Jugador propietario, Posicion posicion){
		super(propietario, posicion);
	}
	
	public Edificio(Jugador propietario, Posicion posicion,
			AtributosEdificio atributos) {
		super(propietario, posicion, atributos);
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
		return ((AtributosEdificio) atributos).puedeEntrenarUnidades();
	}
	
	public boolean edificableEnTierra(){
		return ((AtributosEdificio) atributos).edificableEnTierra();
	}
	
	public boolean edificableEnEspacio(){
		return ((AtributosEdificio) atributos).edificableEnEspacio();
	}
	
	public boolean edificableSobreRecurso(){
		return ((AtributosEdificio) atributos).edificableSobreRecurso();
	}
	
	public boolean edificableSobreMineral(){
		return ((AtributosEdificio) atributos).edificableSobreMineral();
	}
	
	public boolean edificableSobreGasVespeno(){
		return ((AtributosEdificio) atributos).edificableSobreGasVespeno();
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
