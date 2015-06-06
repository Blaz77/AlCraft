package fiuba.algo3.mapa;

import java.util.ArrayList;
import java.util.Random;

import fiuba.algo3.mapa.recurso.GasVespeno;
import fiuba.algo3.mapa.recurso.Mineral;
import fiuba.algo3.mapa.recurso.Recurso;
import fiuba.algo3.mapa.recurso.TipoRecurso;
import fiuba.algo3.terreno.Espacio;
import fiuba.algo3.terreno.Terreno;
import fiuba.algo3.terreno.Tierra;
import fiuba.algo3.terreno.TipoTerreno;


public class Mapa {
	final int DISTANCIA_BORDE = 10;
	final int DISTANCIA_ENTRE_BASES = 64;
	
	// La base es un territorio cuadrado, del q marcamos su centro
	final int SEMILADO_BASE = 8; // El lado de una base sera entonces 2 * semilado + 1 (el centro)
	
	final int MINERALES_POR_BASE = 6;
	
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
		
		generarMapaVacio(cantidadBases); // Define el alto y ancho, todo de tierra x ahora.
		ubicarBases(cantidadBases);	// Define los centros de zonas de recursos y de jugadores.
		definirTerreno(); // Define cuales celdas seran de tierra y cuales espaciales.
		ubicarRecursos(); // Ubica los recursos en las bases.
	}

	private void generarMapaVacio(int cantidadBases) {
		// Esto genera un chorizo estilo
		//...........         .......
		//..P.....B.. ..etc.. ....P..
		//...........         .......
		int lado = 2 * SEMILADO_BASE + 1;
		int bordes = 2 * DISTANCIA_BORDE;
		_ancho = bordes + cantidadBases * lado + DISTANCIA_ENTRE_BASES * (cantidadBases - 1);
		_alto = bordes + lado;

		mapa = new Celda[_ancho][_alto];
		
		for (int x = 0; x < _ancho; x++){
			for (int y = 0; y < _alto; y++){
				mapa[x][y] = new Celda(x, y, new Tierra());
			}
		}		
	}

	private void definirTerreno(){
		
		Random miRNG = new Random();
		// Esto le pone algunas celdas espaciales al borde de arriba
		for (int y = 0; y < DISTANCIA_BORDE; y++)
			for (int x = 0; x < _ancho; x++)
				if ((y == 0 || getTerreno(new Posicion(x, y-1)).getTipo() == TipoTerreno.ESPACIO) && miRNG.nextBoolean())
					mapa[x][y] = new Celda(x, y, new Espacio());
		
		
		// y esto al de abajo
		for (int y = _alto - 1; y > _alto - DISTANCIA_BORDE; y--)
			for (int x = 0; x < _ancho; x++)
				if ((((y == _alto - 1) || getTerreno(new Posicion(x, y-1)).getTipo() == TipoTerreno.ESPACIO) && miRNG.nextBoolean()))
					mapa[x][y] = new Celda(x, y, new Espacio());
		
		
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
	
	// Ubicar los recursos de forma aleatoria. Explico como:
	// Para cada base, elige un punto aleatorio dentro de la zona,
	// Y comienza a hacer caminito por ahi.
	private void ubicarRecursos(){
	
		for (Celda base : bases){
			
			ubicarMinerales(base);
			ubicarVolcan(base);
		}
	}

	private void ubicarMinerales(Celda base) {

		Posicion randPos;
		ArrayList<Celda> yaUbicados = new ArrayList<Celda>();

		while(yaUbicados.size() < MINERALES_POR_BASE){
			
			// Genero un punto aleatorio dentro de la base.
			randPos = celdaRandomEnBase(base);
			
			if (yaUbicados.isEmpty()){
				setRecurso(new Mineral(), randPos);
				yaUbicados.add(getCelda(randPos));
			}
			
			// La idea es que todos los nodos de mineral esten conectados.
			// Asi que pongo minerales en puntos aleatorios conectados a minerales ya
			// colocados.
			else if (getRecurso(randPos).getTipo() != TipoRecurso.MINERAL)
				for(Celda conMineral : yaUbicados)
					if (distancia(randPos, conMineral.getPosicion()) == 1){
						setRecurso(new Mineral(), randPos);
						yaUbicados.add(getCelda(randPos));
						break;
					}
		}
	}

	private Posicion celdaRandomEnBase(Celda base) {
		Random miRNG = new Random();
		int rand_x = base.getX() + miRNG.nextInt(2 * SEMILADO_BASE + 1) - SEMILADO_BASE;
		int rand_y = base.getY() + miRNG.nextInt(2 * SEMILADO_BASE + 1) - SEMILADO_BASE;
		return new Posicion(rand_x, rand_y);
	}

	private void ubicarVolcan(Celda base) {
		Posicion randPos;
		do 
			randPos = celdaRandomEnBase(base);
		while(getRecurso(randPos).getTipo() == TipoRecurso.MINERAL);
		setRecurso(new GasVespeno(), randPos);
	}
	
	public int distancia(Posicion pos1, Posicion pos2) {
		return mapa[pos1.getX()][pos1.getY()].distancia(mapa[pos2.getX()][pos2.getY()]);
	}
	
	public Celda obtenerBaseDeJugador(int numeroJugador) {
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
	
	private Celda getCelda(Posicion posicion){
		return mapa[posicion.getX()][posicion.getY()];
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
		
		int mineralesJ1 = 0;
		int mineralesJ2 = 0;
		int vespenoJ1 = 0;
		int vespenoJ2 = 0;

		Posicion posJ1 = obtenerBaseDeJugador(1).getPosicion();
		Posicion posJ2 = obtenerBaseDeJugador(2).getPosicion();
		
		Posicion pos;
		for(int y = 0; y < alto(); y++) {
			for (int x = 0; x < ancho(); x++) {
				pos = new Posicion(x, y);
				if (distancia(pos, posJ1) < 20) {
					if (getRecurso(pos) != null && getRecurso(pos).getTipo() == TipoRecurso.MINERAL)
						mineralesJ1++;
					else if (getRecurso(pos) != null && getRecurso(pos).getTipo() == TipoRecurso.VESPENO)
						vespenoJ1++;
				}
				else if (distancia(pos, posJ2) < 20) {
					if (getRecurso(pos) != null && getRecurso(pos).getTipo() == TipoRecurso.MINERAL)
						mineralesJ2++;
					else if (getRecurso(pos) != null && getRecurso(pos).getTipo() == TipoRecurso.VESPENO)
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
					elRecurso = getRecurso(new Posicion(x2, y2));
					if(elRecurso != null && elRecurso.getClass().equals(Recurso.class))
						System.out.print("R");
					else if(elRecurso != null && elRecurso.getClass().equals(GasVespeno.class))
						System.out.print("G");
					else if(elRecurso != null && elRecurso.getClass().equals(Mineral.class))
						System.out.print("M");
					else if(getTerreno(new Posicion(x2, y2)).getTipo() == TipoTerreno.ESPACIO)
						System.out.print(" ");
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

	public boolean esBase(Posicion pos) {
		return bases.contains(getCelda(pos));
	}
		

}
