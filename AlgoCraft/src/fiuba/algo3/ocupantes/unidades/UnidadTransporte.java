package fiuba.algo3.ocupantes.unidades;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import fiuba.algo3.atributos.unidades.AtributosUnidadTransporte;
import fiuba.algo3.excepciones.CapacidadAlmacenamientoInsuficente;
import fiuba.algo3.excepciones.NoEsUnAliado;
import fiuba.algo3.excepciones.UnidadNoEsAlmacenable;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;

public class UnidadTransporte extends Unidad {

	private int almacenamientoEnUso = 0; //transporte
	private LinkedList<Unidad> almacenados = new LinkedList<Unidad>();
	
	private AtributosUnidadTransporte atributos;
	public UnidadTransporte(Jugador propietario, Posicion posicion,
			AtributosUnidadTransporte atributos) {
		super(propietario, posicion);
		this.atributos = atributos;
		inicializar();
	}
	
	
	private boolean hayLugarPara(Unidad unidad){
		return (unidad.getCostoAlmacenamiento() <= 
				(((AtributosUnidadTransporte)this.atributos).getCapacidadAlmacenamiento()
						- almacenamientoEnUso));
	}
	
	public boolean puedeAlmacenarA(Unidad unidad){
		return (unidad.puedeSerAlmacenada() &&
				!this.esEnemigoDe(unidad)   &&
				this.hayLugarPara(unidad));
	}
	
	public void almacenarA(Unidad unidad){
		if (!unidad.puedeSerAlmacenada()) throw new UnidadNoEsAlmacenable();
		if (this.esEnemigoDe(unidad)) throw new NoEsUnAliado();
		if (!this.hayLugarPara(unidad)) throw new CapacidadAlmacenamientoInsuficente();
		this.almacenados.add(unidad);
		unidad.setPosicion(this.posicion);
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
