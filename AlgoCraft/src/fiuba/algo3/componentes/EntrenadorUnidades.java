package fiuba.algo3.componentes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import fiuba.algo3.atributos.AtributosEntrenadorUnidades;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.ocupantes.unidades.constructores.Constructor;

public class EntrenadorUnidades implements IEntrenadorUnidades { //, Estado

	private Queue<AtributosUnidad> esperando = new LinkedList<AtributosUnidad>();
	private int entrenamientosActuales;
	private AtributosEntrenadorUnidades atributos;
	private ObjetoVivo portador;
	
	public EntrenadorUnidades(AtributosEntrenadorUnidades atributos) {
		this.atributos = atributos;
		this.entrenamientosActuales = 0;
	}
	
	public void setPortador(ObjetoVivo portador){
		this.portador = portador;
	}
	
	public boolean puedeEntrenarUnidades(){
		return true;
	}
	
	//				u otro nombre
	public ArrayList<Constructor> getUnidadesEntrenables(){
		ArrayList<Constructor> entrenables = new ArrayList<Constructor>();
		for (AtributosUnidad atrUnidad : this.atributos.getUnidadesEntrenables()) {
			entrenables.add(new Constructor(this, atrUnidad));
		}
		return entrenables;
	}
	
	public boolean puedeEntrenar(AtributosUnidad atrUnidad){
		return true; // TODO -> propietario.verificarCompra...
	}
	
	public void entrenar(AtributosUnidad atrUnidad){
		portador.getPropietario().comprar(atrUnidad.getCosto().getCostoMineral(),
				atrUnidad.getCosto().getCostoGasVespeno(), atrUnidad.getVoluntadDelSer().getCostoPoblacion());
		
		if (entrenamientosActuales < this.atributos.getMaxEntrenamientosSimultaneos()){
			portador.agregarEstado(new EstadoEntrenandoUnidad(this, atrUnidad));
			entrenamientosActuales++;
		} else {
			esperando.add(atrUnidad);
		}
	}
	
	public void liberarUnidad(AtributosUnidad atrUnidad) {
		// liberarUnidad
		Unidad u = new Unidad(portador.getPropietario(), portador.getPosicion(), atrUnidad);
		Posicion pos = portador.getPropietario().getMapa().setOcupanteEnCercania(u, portador.getPosicion());
		u.setPosicion(pos);
		portador.getPropietario().agregarUnidad(u);
	}

	public void proximoEntrenamiento() {
		try {
			AtributosUnidad atrUnidad = esperando.remove();
			portador.agregarEstado(new EstadoEntrenandoUnidad(this, atrUnidad));
		} catch (NoSuchElementException e) {
			entrenamientosActuales--;
		}
		
	}

}
