package fiuba.algo3.componentes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import fiuba.algo3.atributos.AtributosTransporte;
import fiuba.algo3.excepciones.CapacidadAlmacenamientoInsuficente;
import fiuba.algo3.excepciones.NoEsUnAliado;
import fiuba.algo3.excepciones.UnidadNoEsAlmacenable;
import fiuba.algo3.ocupantes.unidades.Pasajero;
import fiuba.algo3.ocupantes.unidades.Unidad;

public class Transporte implements ITransporte{
	
	private int almacenamientoEnUso = 0; //transporte
	private LinkedList<Unidad> almacenados = new LinkedList<Unidad>();
	private AtributosTransporte atributos;
	
	public Transporte(AtributosTransporte atributos) {
		this.atributos = atributos;
	}
	
	public boolean puedeAlmacenar() {
		return true;
	}
	
	private boolean hayLugarPara(Unidad unidad){
		return (unidad.getCostoAlmacenamiento() <= 
				(this.atributos.getCapacidadAlmacenamiento() - almacenamientoEnUso));
	}
	
	// los edificios tendrian que implementar puedeSerAlmacenada()
	//							ObjetoVivo obj.
	public boolean puedeAlmacenarA(Unidad transporte, Unidad unidad){
		return (unidad.puedeSerAlmacenada() &&
				!transporte.esEnemigoDe(unidad)   &&
				this.hayLugarPara(unidad));
	}
	
	// los edificios tendrian que implementar puedeSerAlmacenada()
	//							ObjetoVivo obj.
	public void almacenarA(Unidad transporte, Unidad unidad){
		if (!unidad.puedeSerAlmacenada()) throw new UnidadNoEsAlmacenable();
		if (transporte.esEnemigoDe(unidad)) throw new NoEsUnAliado();
		if (!this.hayLugarPara(unidad)) throw new CapacidadAlmacenamientoInsuficente();
		this.almacenados.add(unidad);
		unidad.setPosicion(transporte.getPosicion());
		//SACAR DEL MAPA!, QUIZAS TAMBIEN DEL JUGADOR TEMPORALMENTE
		this.almacenamientoEnUso += unidad.getCostoAlmacenamiento();
	}
	
	public ArrayList<Pasajero> getUnidadesAlmacenadas(){
		ArrayList<Pasajero> pasajeros = new ArrayList<Pasajero>();
		
		for (Unidad unidad : this.almacenados) {
			pasajeros.add(new Pasajero(unidad, this));
		}
		
		return pasajeros;
	}
	
	public void liberarUnidad(Unidad unidadAlmacenada){
		Iterator<Unidad> iter = almacenados.iterator();
		
		while (iter.hasNext()){
			Unidad unidad = iter.next();
			if (unidad == unidadAlmacenada)
				iter.remove();
				this.almacenamientoEnUso -= unidad.getCostoAlmacenamiento();
				//PONER EN EL MAPA, DEVOLVER AL JUGADOR
				break;
		}
	}
	
}
