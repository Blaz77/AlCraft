package fiuba.algo3.A_BORRAR;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import fiuba.algo3.atributos.edificios.AtributosEdificioEntrenadorUnidades;
import fiuba.algo3.componentes.EstadoEntrenandoUnidad;
import fiuba.algo3.componentes.IEntrenadorUnidades;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.unidades.constructores.Constructor;

public class EdificioEntrenadorUnidades extends Edificio implements IEntrenadorUnidades{
	
	public EdificioEntrenadorUnidades(Jugador propietario, Posicion posicion,
			AtributosEdificioEntrenadorUnidades atributos) {
		super(propietario, posicion, atributos);
		maxEntrenamientosSimultaneos = 1;
		entrenamientosActuales = 0;
	}
	
	private Queue<Constructor> esperando = new LinkedList<Constructor>();
	private int maxEntrenamientosSimultaneos;//quizas despues esta en Atributos!
	private int entrenamientosActuales;
	
	//int maxEntrenamientosSimultaneos;
	
	//				u otro nombre
	public ArrayList<Constructor> getUnidadesEntrenables(){
		ArrayList<Constructor> entrenables = ((AtributosEdificioEntrenadorUnidades)atributos).getUnidadesEntrenables();
		for (Constructor constructor : entrenables) {
			constructor.setEdificioEntrenador(this);
		}
		return entrenables;
	}
	
	public void entrenar(Constructor constructor){
		if (entrenamientosActuales < maxEntrenamientosSimultaneos){
			this.agregarEstado(new EstadoEntrenandoUnidad(constructor));
			entrenamientosActuales++;
		}
		else{
			esperando.add(constructor);
		}
		
	}

	public void proximoEntrenamiento() {
		try {
			Constructor constructor = esperando.remove();
			this.agregarEstado(new EstadoEntrenandoUnidad(constructor));
		} catch (NoSuchElementException e) {
			entrenamientosActuales--;
		}
		
	}
}
