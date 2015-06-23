package fiuba.algo3.componentes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import fiuba.algo3.atributos.AtributosTransporte;
import fiuba.algo3.excepciones.CapacidadAlmacenamientoInsuficente;
import fiuba.algo3.excepciones.FueraDelRangoPermitido;
import fiuba.algo3.excepciones.NoEsUnAliado;
import fiuba.algo3.excepciones.UnidadNoEsAlmacenable;
import fiuba.algo3.excepciones.UnidadYaAlmacenada;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.unidades.Pasajero;
import fiuba.algo3.ocupantes.unidades.Unidad;

public class Transporte implements ITransporte, Estado{
	
	private int almacenamientoEnUso = 0; //transporte
	private LinkedList<Unidad> almacenados = new LinkedList<Unidad>();
	private AtributosTransporte atributos;
	private ObjetoVivo portador;
	
	public Transporte(AtributosTransporte atributos, ObjetoVivo portador) {
		this.atributos = atributos;
		this.portador = portador;
		portador.agregarEstado(this);
	}
	
	@Override
	public String getDescripcion() {
		return String.format("Almacenamiento en uso: %d/%d.", 
				almacenamientoEnUso, atributos.getCapacidadAlmacenamiento());
		
	}

	@Override
	public void activar(ObjetoVivo portador) {}

	@Override
	public void pasarTurno() throws Exception {}

	@Override // solamente se llama cuando se muere el transporte.
	public void desactivar() {
		for (Unidad unidad : this.almacenados) {
			unidad.setPosicion(unidad.getPropietario().getMapa()
					.setOcupanteEnCercania(unidad, portador.getPosicion()));
		}
	}
	
	public boolean puedeAlmacenar() {
		return true;
	}
	
	private boolean tengoAlmacenadoA(Unidad unidad){
		for (Unidad unidadAlmacenada : this.almacenados) {
			if (unidad == unidadAlmacenada) return true;
		}
		return false;
	}
	
	private boolean hayLugarPara(Unidad unidad){
		return (unidad.getCostoAlmacenamiento() <= 
				(this.atributos.getCapacidadAlmacenamiento() - almacenamientoEnUso));
	}
	
	// los edificios tendrian que implementar puedeSerAlmacenada()
	//							ObjetoVivo obj.
	public boolean puedeAlmacenarA(Unidad unidad){
		return (unidad.puedeSerAlmacenada() &&
				!portador.esEnemigoDe(unidad) &&
				!this.tengoAlmacenadoA(unidad) &&
				portador.getPosicion().estaEnRango(unidad.getPosicion(), atributos.getRangoEntrada()) &&
				this.hayLugarPara(unidad));
	}
	
	// los edificios tendrian que implementar puedeSerAlmacenada()
	//							ObjetoVivo obj.
	public void almacenarA(Unidad unidad){
		//Chequeos:
		if (!unidad.puedeSerAlmacenada()) throw new UnidadNoEsAlmacenable();
		if (portador.esEnemigoDe(unidad)) throw new NoEsUnAliado();
		if (this.tengoAlmacenadoA(unidad)) throw new UnidadYaAlmacenada();
		if (!portador.getPosicion().estaEnRango(unidad.getPosicion(), atributos.getRangoEntrada()))
			throw new FueraDelRangoPermitido();
		if (!this.hayLugarPara(unidad)) throw new CapacidadAlmacenamientoInsuficente();
		// Sacar del mapa:
		portador.getPropietario().getMapa().removerOcupante(unidad.getPosicion());
		// Compartir posicion con el transporte (se actualiza solo):
		unidad.setPosicion(portador.getPosicion());
		// Agregar a mi lista:
		this.almacenados.add(unidad);
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
			if (unidad == unidadAlmacenada){
				// Posicionar en el mapa:
				unidad.setPosicion(unidad.getPropietario().getMapa()
						.setOcupanteEnCercania(unidad, portador.getPosicion()));
				// Sacar de mi lista:
				iter.remove();
				this.almacenamientoEnUso -= unidad.getCostoAlmacenamiento();
				break;
			}
		}
	}
	
}
