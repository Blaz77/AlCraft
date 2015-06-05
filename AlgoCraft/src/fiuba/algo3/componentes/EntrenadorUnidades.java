package fiuba.algo3.edificios;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import fiuba.algo3.excepciones.UnidadFinalizadaException;
import fiuba.algo3.unidades.Constructor;

public class EntrenadorUnidades implements Trabajo,IEntrenador {

	private ArrayList<Constructor> unidadesEntrenables = new ArrayList<Constructor>();
	private Edificio edificio;//o edificio
	
	private Constructor actual = null; //Constructor NULL
	private Queue<Constructor> esperando = new LinkedList<Constructor>();
	
	//public TrabajoEntrenarUnidades(int maxEntrenamientosSimultaneos);
	
	public EntrenadorUnidades(Edificio edificio){
		this.edificio = edificio;
	}

	public Trabajo pasarTurno() {
		if (actual == null) actual = esperando.remove();
		if (actual != null){
			try { 
				actual.pasarTurno();
			} catch (UnidadFinalizadaException e) {
				actual.liberarUnidad(this.edificio.getPropietario(),
									 this.edificio.getX(), this.edificio.getY());
				actual = null;
			}
		}
		return this;
		//iterar la cola de los que se entrenan y llamarles .pasarTurno();
		
	}
	
	public boolean puedeEntrenar(){
		return unidadesEntrenables.size() != 0;
	}
	
	//				u otro nombre
	public ArrayList<Constructor> getUnidadesEntrenables(){
		return unidadesEntrenables;
	}
	
	public void entrenar(Constructor constructor){
		esperando.add(constructor);
	}
	
	public void agregarEntrenable(Constructor constructor){
		unidadesEntrenables.add(constructor);
		constructor.setEntrenador(this);
	}

	public void setAnterior(Componente componenteAnterior) {}
}
