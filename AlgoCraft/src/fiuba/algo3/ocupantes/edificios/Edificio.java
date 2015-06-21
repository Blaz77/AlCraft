package fiuba.algo3.ocupantes.edificios;

import java.util.ArrayList;

import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.componentes.IEntrenadorUnidades;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.TipoOcupante;
import fiuba.algo3.ocupantes.unidades.constructores.Constructor;

public class Edificio extends ObjetoVivo {
	
	private IEntrenadorUnidades entrenador;
	
	public Edificio(Jugador propietario, Posicion posicion, 
								AtributosEdificio atributos){
		super(propietario, posicion, atributos);
		this.entrenador = atributos.getEntrenadorUnidades();
		this.entrenador.setPortador(this);
	}

	public boolean puedeEntrenarUnidades(){
		return this.entrenador.puedeEntrenarUnidades();
	}
	
	@Override
	public void destruir() {
		super.destruir();
		this.propietario.removerEdificio(this);
	}
	
	//				u otro nombre
	public ArrayList<Constructor> getUnidadesEntrenables(){
		return this.entrenador.getUnidadesEntrenables();
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
