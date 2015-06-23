package fiuba.algo3.componentes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import fiuba.algo3.atributos.AtributosMovimiento;
import fiuba.algo3.excepciones.MovimientoInvalido;
import fiuba.algo3.excepciones.PosicionOcupada;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.unidades.Unidad;

public class Movimiento implements IMovimiento, Estado {

	private int movRestantes;
	private AtributosMovimiento atributos;
	private Unidad portador;
	
	public Movimiento(AtributosMovimiento atributos, Unidad portador){
		this.atributos = atributos;
		this.movRestantes = atributos.getMovPorTurno();
		this.portador = portador;
		portador.agregarEstado(this);
	}

	public void activar(ObjetoVivo portador) {}

	public void pasarTurno() throws Exception {
		this.movRestantes = atributos.getMovPorTurno();
	}

	public void desactivar() {}
	
	public int getRangoVision() {
		return atributos.getRangoVision();
	}
	
	public int getCostoAlmacenamiento() {
		return atributos.getCostoAlmacenamiento();
	}

	public boolean puedeSerAlmacenada() {
		return (atributos.getCostoAlmacenamiento() != 0);
	}
	
	//Pensar:
	// - Habria que diferenciar los que no se pueden mover nunca
	// 	 de los que terminaron sus turnos?
	public boolean puedeMoverse(){
		return (this.movRestantes != 0);
	}
	
	public boolean puedeMoverseA(Posicion destino){
		return (portador.getPosicion().estaEnRango(destino, movRestantes) &&
				portador.getPropietario().getMapa().puedeOcupar(portador, destino));
	}
	
	public void moverA(Posicion destino){
		if (! puedeMoverseA(destino)) {
			throw new MovimientoInvalido();
		}
			
		this.movRestantes -= portador.getPosicion().distancia(destino);
		portador.getPropietario().getMapa().mover(this.portador, destino);
		portador.setPosicion(destino);
	}

	//TODO: chequear q funcione!
	public HashSet<Posicion> getPosiblesMovimientos(Mapa mapa){
		HashSet<Posicion> posiblesMov = new HashSet<Posicion>();
		_getPosiblesMovimientos(mapa, posiblesMov, portador.getPosicion(), movRestantes);
		return posiblesMov;
		
	}
	
	/* BFS que agrega todas las posiciones de movimiento posibles, sin incluir la actual */
	public void _getPosiblesMovimientos(Mapa mapa, HashSet<Posicion> posiblesMov, Posicion posicionActual, int distancia){
		ArrayList<Posicion> visitados = new ArrayList<Posicion>();
		Queue<Posicion> cola = new LinkedList<Posicion>();
		Posicion v;
		
		cola.add(posicionActual);
		visitados.add(posicionActual);
		while (! cola.isEmpty()) {
			v = cola.poll();
			for (Posicion w : v.getAdyacentes()) {
				if (! visitados.contains(w) && w.distancia(posicionActual) <= distancia &&
						mapa.celdaValida(w) && portador.getPropietario().getMapa().puedeOcupar(portador, w)) {
					visitados.add(w);
					cola.add(w);
				}
			}
		}

		posiblesMov.clear();
		for (Posicion pos : visitados) {
			if ((pos) != posicionActual) posiblesMov.add(pos);
		}
	}
	
	/*
	// TODO: chequear q funcione! Basicamente hago un BFS y agrego todas las unidades a las q llego
	public void _getPosiblesMovimientos(Mapa mapa, HashSet<Posicion> posiblesMov, Posicion posicionActual, int restantes){
		if (restantes == 0 || posiblesMov.contains(posicionActual)) return;
		restantes --;
		posiblesMov.add(posicionActual);
		ArrayList<Posicion> adyacentes = posicionActual.getAdyacentes();
		for (Posicion adyacente: adyacentes)
			if (mapa.celdaValida(adyacente) && mapa.puedeOcupar(this.portador, adyacente))
				_getPosiblesMovimientos(mapa, posiblesMov, adyacente, restantes);
	}*/

	@Override
	public String getDescripcion() {
		return String.format("Movimiento: %d movimeintos restantes.", movRestantes);
	}
	
}
