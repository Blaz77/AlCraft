package fiuba.algo3.tp_final;

import java.util.ArrayList;

public class Mapa {
	final int DISTANCIA_BORDE = 10;
	final int DISTANCIA_ENTRE_BASES = 64;
	
	private int _ancho;
	private int _alto;
	private ArrayList<Punto> bases = new ArrayList<Punto>();
	private ArrayList<Punto> basesJugadores = new ArrayList<Punto>();

	/* Genera un mapa para 2 jugadores, con la cantidad de bases
	 * especxificada
	 */
	public Mapa(int cantidadBases) {
		// Por ahora es todo fijo, después veremos como hacer un generador 
		// aleatorio
		// Esto genera una cuadricula estilo
		// ---------------------
		// ---B------B------B---
		// ---------------------
		// ---B------B------B---
		// ---------------------
		// ---B------B----------
		// ---------------------
		// Si queda rectangular, se extiende en ancho
		int columnas = (int) Math.sqrt(cantidadBases);
		if (Math.sqrt(cantidadBases) > columnas) columnas++; 
		int filas = (int) Math.sqrt(cantidadBases);
		
		_ancho = DISTANCIA_BORDE * 2 + DISTANCIA_ENTRE_BASES * (columnas - 1);
		_alto = DISTANCIA_BORDE * 2 + DISTANCIA_ENTRE_BASES * (filas - 1);
		
		for (int fila = 0; fila < filas; fila++) {
			for (int columna = 0; columna < columnas; columna++) {
				if (bases.size() < cantidadBases) {
					bases.add(new Punto(DISTANCIA_BORDE + DISTANCIA_ENTRE_BASES * columna,
										DISTANCIA_BORDE + DISTANCIA_ENTRE_BASES * fila));
				}
			}
		}
		
		// Puntos de generacion para 2 jugadores
		basesJugadores.add(bases.get(0));
		basesJugadores.add(bases.get(bases.size() - 1));
	}

	public Punto obtenerPuntoGeneradorJugador(int numeroJugador) {
		return basesJugadores.get(numeroJugador - 1);
	}

	/* Devuelve el ancho del mapa. */
	public int ancho() {
		return _ancho;
	}

	/* Devuelve el alto del mapa. */
	public int alto() {
		return _alto;
	}

	public ArrayList<Punto> getBases() {
		return new ArrayList<Punto>(bases);
	}

}
