package fiuba.algo3.factories;

import java.util.HashMap;

import fiuba.algo3.atributos.edificios.AtributosConstruccion;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.excepciones.OrdenConstruccionViolado;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.edificios.Edificio;

public class EdificiosFactory implements EdificiosAbstractFactory{
	private final int NO_HABILITADO = 0;
	private final int HABILITADO = 1;
	
	private HashMap<Tipo,Integer> libreParaConstruccion = new HashMap<Tipo,Integer>();
	
	public void denegarConstruccionEdificio(Tipo tipo){
		Integer liberadores = libreParaConstruccion.getOrDefault(tipo, HABILITADO);
		libreParaConstruccion.put(tipo, liberadores-1);
	}
	
	public void permitirConstruccionEdificio(Tipo tipo){
		Integer liberadores = libreParaConstruccion.getOrDefault(tipo, NO_HABILITADO);
		libreParaConstruccion.put(tipo, liberadores+1);
	}
	
	private void comprarEdificio(Jugador jugador, AtributosEdificio atributos) {
		jugador.comprar(atributos.getCosto().getCostoMineral(), atributos.getCosto().getCostoGasVespeno());
	}
	
	private void verificarOrdenConstruccion(AtributosEdificio atributos) {
		if (libreParaConstruccion.getOrDefault(atributos.getTipo(), HABILITADO) <= NO_HABILITADO)
			throw new OrdenConstruccionViolado(atributos);
	}
	
	//public para la interfaz!
	public Edificio crearEdificio(Jugador jugador, Posicion posicion, AtributosEdificio atributos){
		// Chequeo de orden construccion. 
		verificarOrdenConstruccion(atributos);
		//
		Edificio edificio = new Edificio(jugador, posicion, new AtributosConstruccion(atributos));
		// Chequeo de terreno y/o recurso:
		jugador.getMapa().verificarOcupacion(edificio, posicion);
		// Chequeo y compra 
		comprarEdificio(jugador, atributos);
		// Poner en mapa (chequeado anteriormente)
		jugador.getMapa().setOcupante(edificio, posicion);
		jugador.agregarEdificio(edificio);
		return edificio;
	}
	
	public Edificio crearRecolectorGasVespeno(Jugador jugador, Posicion posicion) {
		return crearEdificio(jugador, posicion, jugador.getAtributos().getRecolectorGasVespeno());	
	}

	public Edificio crearRecolectorMineral(Jugador jugador, Posicion posicion) {
		return crearEdificio(jugador, posicion, jugador.getAtributos().getRecolectorMineral());
	}

	public Edificio crearIncrementadorPoblacion(Jugador jugador, Posicion posicion) {
		return crearEdificio(jugador, posicion, jugador.getAtributos().getIncrementadorPoblacion());
	}

	public Edificio crearEntrenadorUnidadesBasicas(Jugador jugador, Posicion posicion) {
		return crearEdificio(jugador, posicion, jugador.getAtributos().getEntrenadorUnidadesBasicas());
	}

	public Edificio crearEntrenadorUnidadesIntermedias(Jugador jugador, Posicion posicion) {
		return crearEdificio(jugador, posicion, jugador.getAtributos().getEntrenadorUnidadesIntermedias());
	}

	public Edificio crearEntrenadorUnidadesAvanzadas(Jugador jugador, Posicion posicion) {
		return crearEdificio(jugador, posicion, jugador.getAtributos().getEntrenadorUnidadesAvanzadas());
	}

}
