package fiuba.algo3.tp_final;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Mapa implements Iterable<Celda>{
	final int DISTANCIA_BORDE = 10;
	final int DISTANCIA_ENTRE_BASES = 64;
	
	static int BASES_PARA_MAIN = 5;
	
	private int _ancho;
	private int _alto;
	private Celda[][] mapa;
	private ArrayList<Celda> bases = new ArrayList<Celda>();
	private ArrayList<Celda> basesJugadores = new ArrayList<Celda>();

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
		int filas = (int) Math.ceil(Math.sqrt(cantidadBases));
		int columnas = filas;//(int) Math.sqrt(cantidadBases);
		
		_ancho = DISTANCIA_BORDE * 2 + DISTANCIA_ENTRE_BASES * (columnas - 1);
		_alto = DISTANCIA_BORDE * 2 + DISTANCIA_ENTRE_BASES * (filas - 1);
		
		mapa = new Celda[_ancho][_alto];// X==ancho, y==alto
		
		for (int x = 0; x < _ancho; x++){
			for (int y = 0; y < _alto; y++){
				mapa[x][y] = new Celda(x, y); //faltaran parametros
			}
		}
		
		for (int fila = 0; fila < filas; fila++) {
			for (int columna = 0; columna < columnas; columna++) {
				if (bases.size() < cantidadBases) {
					int x = DISTANCIA_BORDE + (DISTANCIA_ENTRE_BASES * columna);
					int y = DISTANCIA_BORDE + (DISTANCIA_ENTRE_BASES * fila);
					Celda p = mapa[x][y];
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

	public Celda obtenerPuntoGeneradorJugador(int numeroJugador) {
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

	public ArrayList<Celda> getBases() {
		return new ArrayList<Celda>(bases);
	}

	public Celda getCelda(int x, int y) { // Esto de aca... public?
		return mapa[x][y];
	}
	
	public void setRecurso(Recurso recurso, int x, int y) {
		mapa[x][y].setRecurso(recurso);
	}	
	
	public Recurso getRecurso(int x, int y) {
		return mapa[x][y].getRecurso();
	}

	public TipoTerreno getTerreno(int x, int y){
		return mapa[x][y].getTerreno();
	}
	
	public void setTerreno(TipoTerreno tipoTerreno, int x, int y){
		mapa[x][y].setTerreno(tipoTerreno);
	}
	
	
	public Iterator<Celda> iterator() {		
		
		return new MapaIterator();
	}

	class MapaIterator implements Iterator<Celda> {
			
		private int actual_x;
		private int actual_y;
		private Mapa mapa;
		
		public MapaIterator() {
			actual_x = 0;
			actual_y = 0;
			mapa = Mapa.this;
		}
		
		private void advance(){
			actual_x ++;
			if (actual_x == mapa._ancho){
				actual_x = 0;
				actual_y ++;
			}
		}
		
		public Celda next(){
			if (!this.hasNext())
				throw new NoSuchElementException();
			Celda next = mapa.mapa[actual_x][actual_y];
			this.advance();
			return next;
		}
     
		public boolean hasNext(){
			return((actual_y < mapa._alto ) && ( actual_x < mapa._ancho));
		}
	};

	
	
	// Mostrar el mapa de una forma horriblemente hermosa (?".
	public static void main(String[] args){

		Mapa miMapa = new Mapa(BASES_PARA_MAIN);
		Celda elPunto;
		Recurso elRecurso;
		for (int y2 = 0; y2 < miMapa.alto(); y2++){
			for (int x2 = 0; x2 < miMapa.ancho(); x2++){
				elPunto = miMapa.getCelda(x2,y2);
				
				if (miMapa.bases.contains(elPunto) || miMapa.basesJugadores.contains(elPunto) )
					System.out.print("B");
				else {
					elRecurso = elPunto.getRecurso();
					if(elRecurso.getClass().equals(Recurso.class))
						System.out.print(".");
					else if(elRecurso.getClass().equals(GasVespeno.class))
						System.out.print("G");
					else if(elRecurso.getClass().equals(Mineral.class))
						System.out.print("M");
					else
						System.out.print(" ");
				}
			}
			System.out.println("");
		}
		
	}	
	
	
	
	/*
	public static void main(String[] args){
			
		Mapa miMapa = new Mapa(BASES_PARA_MAIN);
		
		// Mostrar los puntos en formato "x, y: recurso".
		for (Punto punto : miMapa){
			System.out.print(punto.getX());
			System.out.print(", ");
			System.out.print(punto.getY());
			System.out.print(": ");
			System.out.println(punto.getRecurso());
		}
	}
	*/
}
