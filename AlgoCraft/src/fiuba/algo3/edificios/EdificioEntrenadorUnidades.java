package fiuba.algo3.edificios;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import fiuba.algo3.atributos.AtributosEdificio;
import fiuba.algo3.atributos.AtributosEdificioEntrenadorUnidades;
import fiuba.algo3.componentes.Trabajo;
import fiuba.algo3.excepciones.UnidadFinalizadaException;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.unidades.Constructor;

public abstract class EdificioEntrenadorUnidades extends Edificio implements IEntrenador{
	
	public EdificioEntrenadorUnidades(Jugador propietario, Posicion posicion,
			AtributosEdificioEntrenadorUnidades atributos) {
		super(propietario, posicion, atributos);		
	}
	
	private Constructor actual = null; //Constructor NULL
	private Queue<Constructor> esperando = new LinkedList<Constructor>();
	
	//int maxEntrenamientosSimultaneos;

	
	//TEMPORAL -> DESPUES PASAR A UN ESTADO! -> ESTADOENTRENANDO
	public void pasarTurno() {
		super.pasarTurno();
		
		if (actual == null) actual = esperando.poll(); //Despues pasar a remove con un try!
		if (actual != null){
			try { 
				actual.pasarTurno();
			} catch (UnidadFinalizadaException e) {
				actual.liberarUnidad(this.getPropietario(),
									 this.getPosicion());
				actual = null;
			}
		}
		//iterar la cola de los que se entrenan y llamarles .pasarTurno();
		
	}
	
	//				u otro nombre
	public ArrayList<Constructor> getUnidadesEntrenables(){
		ArrayList<Constructor> entrenables = ((AtributosEdificioEntrenadorUnidades)atributos).getUnidadesEntrenables();
		for (Constructor constructor : entrenables) {
			constructor.setEdificioEntrenador(this);
		}
		return entrenables;
	}
	
	public void entrenar(Constructor constructor){
		esperando.add(constructor);
	}
}
