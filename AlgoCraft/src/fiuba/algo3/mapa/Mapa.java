package fiuba.algo3.mapa;

import java.util.ArrayList;
import java.util.Random;

import fiuba.algo3.mapa.recurso.GasVespeno;
import fiuba.algo3.mapa.recurso.Mineral;
import fiuba.algo3.mapa.recurso.Recurso;
import fiuba.algo3.terreno.Espacio;
import fiuba.algo3.terreno.Terreno;
import fiuba.algo3.terreno.Tierra;
import fiuba.algo3.terreno.TipoTerreno;


public class Mapa {
	final int DISTANCIA_BORDE = 10;
	final int DISTANCIA_ENTRE_BASES = 64;
	
	// La base es un territorio cuadrado, del q marcamos su centro
	final int SEMILADO_BASE = 8; // El lado de una base sera entonces 2 * semilado + 1 (el centro)
	
	final int MINERALES_POR_BASE = 4;
	
	static int BASES_PARA_MAIN = 5;
	
	private int _ancho;
	private int _alto;
	private Celda[][] mapa;
	private ArrayList<Celda> bases = new ArrayList<Celda>();
	private ArrayList<Celda> basesJugadores = new ArrayList<Celda>();

	/* Genera un mapa para 2 jugadores, con la cantidad de bases
	 * especxificada
	 */
	public Mapa(int cantidadBases){
		// Por ahora es todo fijo, despues veremos como hacer un generador 
		// aleatorio
		// Esto genera un chorizo estilo

		//...........         .......
		//..P.....B.. ..etc.. ....P..
		//...........         .......
		
		int lado = 2 * SEMILADO_BASE + 1;
		int bordes = 2 * DISTANCIA_BORDE;
		_ancho = bordes + cantidadBases * lado + DISTANCIA_ENTRE_BASES * (cantidadBases - 1);
		_alto = bordes + lado;

		mapa = new Celda[_ancho][_alto];// X==ancho, y==alto

		llenarTerrenoMapa();
		
		ubicarBases(cantidadBases);
		
		ubicarRecursos();
	}

	private void ubicarBases(int cantidadBases) {
		for (int i = 0; i < cantidadBases; i++){
			int base_x = DISTANCIA_BORDE + SEMILADO_BASE + i * (1 + 2*SEMILADO_BASE + DISTANCIA_ENTRE_BASES); 
			int base_y = DISTANCIA_BORDE + SEMILADO_BASE;
			bases.add(mapa[base_x][base_y]);
		}
			
		basesJugadores.add(bases.get(0));
		basesJugadores.add(bases.get(bases.size() - 1));
	}

	private void ubicarRecursos(){

		int x;
		int y;
		for (Celda base : bases){
			x = base.getX();
			y = base.getY();
			mapa[x][y].setRecurso(new Mineral());
			mapa[x+2][y].setRecurso(new GasVespeno());
			mapa[x-1][y+3].setRecurso(new Mineral());
		}
	}


// Ubicar los recursos de forma mas canchera :D (anda pero no es perfecto, pruebenlo :D)
/*	private void ubicarRecursos(){

		int x, rand_x, y, rand_y, last_x, last_y;
		int lado = 2 * SEMILADO_BASE + 1;
		int sinUbicar;
		Random miRNG = new Random();
		for (Celda base : bases){
			x  = base.getX();
			y  = base.getY();
			last_x = x;
			last_y = y;
			sinUbicar = MINERALES_POR_BASE;
			while(sinUbicar > 0){
				rand_x = x + miRNG.nextInt(lado) - SEMILADO_BASE;
				rand_y = y + miRNG.nextInt(lado) - SEMILADO_BASE;
				System.out.format("(%d,%d)%n",rand_x, rand_y);
				if (sinUbicar == MINERALES_POR_BASE || (distancia(rand_x, rand_y, last_x, last_y) == 1 &&
						getRecurso(rand_x, rand_y).getTipo() != TipoRecurso.MINERAL)){
					mapa[rand_x][rand_y].setRecurso(new Mineral());
					sinUbicar--;
					last_x = rand_x;
					last_y = rand_y;
				}
			}
		}
	}
	*/

	private void llenarTerrenoMapa(){
		//Por ahora todo Tierra, excepto una celda chancho ya se
		
		Random miRNG = new Random();
		for (int x = 0; x < _ancho; x++){
			for (int y = 0; y < _alto; y++){
				mapa[x][y] = new Celda(x, y, new Tierra()); //faltaran parametros
			}
		}
		
		// Esto le pone algunas celdas espaciales al borde de arriba
		for (int y = 0; y < DISTANCIA_BORDE; y++)
			for (int x = 0; x < _ancho; x++)
				if ((y == 0 || getTerreno(x, y-1).getTipo() == TipoTerreno.ESPACIO) && miRNG.nextBoolean())
					mapa[x][y] = new Celda(x, y, new Espacio());
		
		
		// y esto al de abajo
		for (int y = _alto - 1; y > _alto - DISTANCIA_BORDE; y--)
			for (int x = 0; x < _ancho; x++)
				if ((((y == _alto - 1) || getTerreno(x, y+1).getTipo() == TipoTerreno.ESPACIO) && miRNG.nextBoolean()))
					mapa[x][y] = new Celda(x, y, new Espacio());
		
		
	}
	
	public double distancia(int x1, int y1, int x2, int y2) {
		return mapa[x1][y1].distancia(mapa[x2][y2]);
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

	/*public Celda getCelda(int x, int y) { // Esto de aca... public?
		return mapa[x][y];
	}*/
	
	private Celda getCelda(Posicion posicion){
		return mapa[posicion.getX()][posicion.getY()];
	}
	
	//Uso interno, para hacer la vida mas facil con la matriz
	private Recurso getRecurso(int x, int y){ 
		return mapa[x][y].getRecurso();
	}
	
	//Uso interno, para hacer la vida mas facil con la matriz
	private Terreno getTerreno(int x, int y){
		return mapa[x][y].getTerreno();
	}
	
	public void setRecurso(Recurso recurso, Posicion posicion) {
		this.getCelda(posicion).setRecurso(recurso);
	}	
	
	public Recurso getRecurso(Posicion posicion) {
		return this.getCelda(posicion).getRecurso();
	}

	public Terreno getTerreno(Posicion posicion){
		return this.getCelda(posicion).getTerreno();
	}

	
	/* DEBUG AREA */

	private void imprimirRecursoPorCelda(){
		Celda celda;
		// Mostrar los puntos en formato "x, y: recurso".
		for (int y2 = 0; y2 < _alto; y2++)
			for (int x2 = 0; x2 < _ancho; x2++){
				celda = mapa[x2][y2];
			
				System.out.print(celda.getX());
				System.out.print(", ");
				System.out.print(celda.getY());
				System.out.print(": ");
				System.out.println(celda.getRecurso());
		}
	}
	
	private void imprimirMineralesCercaDeJugadores(){
		
		Celda puntoGeneracionJugador1 = obtenerPuntoGeneradorJugador(1);
		Celda puntoGeneracionJugador2 = obtenerPuntoGeneradorJugador(2);
		int mineralesJ1 = 0;
		int mineralesJ2 = 0;
		int vespenoJ1 = 0;
		int vespenoJ2 = 0;

		int x1 = puntoGeneracionJugador1.getX();
		int y1 = puntoGeneracionJugador1.getY();
		int x2 = puntoGeneracionJugador2.getX();
		int y2 = puntoGeneracionJugador2.getY();
		
		for(int y = 0; y < alto(); y++) {
			for (int x = 0; x < ancho(); x++) {
				if (distancia(x,y,x1,y1) < 20) {
					if (getRecurso(x, y) != null && getRecurso(x, y).getClass() == Mineral.class)
						mineralesJ1++;
					else if (getRecurso(x, y) != null && getRecurso(x, y).getClass() == GasVespeno.class)
						vespenoJ1++;
				}
				else if (distancia(x,y,x2,y2) < 20) {
					if (getRecurso(x, y) != null && getRecurso(x, y).getClass() == Mineral.class)
						mineralesJ2++;
					else if (getRecurso(x, y) != null && getRecurso(x, y).getClass() == GasVespeno.class)
						vespenoJ2++;
				}
			}
		}
		System.out.format("Minerales cerca del jugador 1: %d%n", mineralesJ1);		
		System.out.format("Geyseres cerca del jugador 1: %d%n", vespenoJ1);		
		System.out.format("Minerales cerca del jugador 2: %d%n", mineralesJ2);		
		System.out.format("Geyseres cerca del jugador 2: %d%n", vespenoJ2);	
	}	

		
	private void imprimirMapaConReferencias() {
		Celda elPunto;
		Recurso elRecurso;
		for (int y2 = 0; y2 < alto(); y2++){
			for (int x2 = 0; x2 < ancho(); x2++){
				elPunto = mapa[x2][y2];

				if (basesJugadores.contains(elPunto))
					System.out.print("P");
				else if (bases.contains(elPunto) )
					System.out.print("B");
				else {
					elRecurso = getRecurso(x2, y2);
					if(elRecurso != null && elRecurso.getClass().equals(Recurso.class))
						System.out.print("R");
					else if(elRecurso != null && elRecurso.getClass().equals(GasVespeno.class))
						System.out.print("G");
					else if(elRecurso != null && elRecurso.getClass().equals(Mineral.class))
						System.out.print("M");
					else
						System.out.print(".");
				}
			}
			System.out.println("");
		}
	}

	// Mostrar info del mapa de varias formas".
	public static void main(String[] args){

		Mapa miMapa = new Mapa(BASES_PARA_MAIN);
		
		miMapa.imprimirMapaConReferencias();
		if (args.length != 0) {
			miMapa.imprimirMineralesCercaDeJugadores();
			miMapa.imprimirRecursoPorCelda();
		}
	}

	public boolean esBase(int x, int y) {
		return bases.contains(mapa[x][y]);
	}
		

}
