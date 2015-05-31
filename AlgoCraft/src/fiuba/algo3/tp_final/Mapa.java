package fiuba.algo3.tp_final;

import java.util.ArrayList;

public class Mapa {
	final int DISTANCIA_BORDE = 10;
	final int DISTANCIA_ENTRE_BASES = 64;
	
	private int _ancho;
	private int _alto;
	private Punto[][] mapa;
	private ArrayList<Punto> bases = new ArrayList<Punto>();
	private ArrayList<Punto> basesJugadores = new ArrayList<Punto>();

	/* Genera un mapa para 2 jugadores, con la cantidad de bases
	 * especxificada
	 */
	public Mapa(int cantidadBases) {
		// Por ahora es todo fijo, despu�s veremos como hacer un generador 
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
		
		mapa = new Punto[_ancho][_alto];// X==ancho, y==alto
		
		for (int x = 0; x < _ancho; x++){
			for (int y = 0; y < _alto; y++){
				mapa[x][y] = new Punto(x, y); //faltaran parametros
			}
		}
		
		for (int fila = 0; fila < filas; fila++) {
			for (int columna = 0; columna < columnas; columna++) {
				if (bases.size() < cantidadBases) {
					int x = DISTANCIA_BORDE + (DISTANCIA_ENTRE_BASES * columna);
					int y = DISTANCIA_BORDE + (DISTANCIA_ENTRE_BASES * fila);
					Punto p = mapa[x][y];
					bases.add(p);
					
					// Agrego recursos
					mapa[x][y].setRecurso(new Mineral());
					mapa[x+2][y].setRecurso(new GasVespeno());
					mapa[x-1][y+3].setRecurso(new Mineral());
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

	public Punto getCelda(int x, int y) {
		return mapa[x][y];
	}

}
