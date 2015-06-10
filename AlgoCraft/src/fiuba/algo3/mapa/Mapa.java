package fiuba.algo3.mapa;

import java.util.ArrayList;
import java.util.Random;

import fiuba.algo3.excepciones.MovimientoInvalido;
import fiuba.algo3.ocupantes.Ocupante;
import fiuba.algo3.ocupantes.recurso.GasVespeno;
import fiuba.algo3.ocupantes.recurso.Mineral;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.terreno.Espacio;
import fiuba.algo3.terreno.Terreno;
import fiuba.algo3.terreno.Tierra;
import fiuba.algo3.terreno.TipoTerreno;


public class Mapa {
	final static int DISTANCIA_BORDE = 10;
	final static int DISTANCIA_ENTRE_BASES = 64;
	
	// La base es un territorio cuadrado, del q marcamos su centro
	final static int SEMILADO_BASE = 8; // El lado de una base sera entonces 2 * semilado + 1 (el centro)
	
	final static int MINERALES_POR_BASE = 6;
		
	private int ancho;
	private int alto;
	private Celda[][] mapa;
	private ArrayList<Celda> bases = new ArrayList<Celda>();
	private ArrayList<Celda> basesJugadores = new ArrayList<Celda>();

	
/**********************************************/
/**              INICIALIZACION              **/
/**********************************************/
	
	/* Genera un mapa aleatorio para 2 jugadores, con la cantidad 
	 * de bases especificada
	 */
	public Mapa(int cantidadBases){
		
		generarMapaVacio(cantidadBases);
		ubicarBases(cantidadBases);
		definirTerreno();
		ubicarRecursos();

}

	
	/* Genera la pieza central de la clase: la matriz de celdas.
	 * Las filas y columnas dependeran de la cant. de bases.
	 * A priori, esta estara compuesta integramente de celdas
	 * de tierra.
	 */
	private void generarMapaVacio(int cantidadBases) {
		// En particular, esto genera un chorizo estilo
		//...........       ...........
		//..P.....B....etc....B.....P..  P = Base de Jugador
		//...........       ...........  B = Base neutral.
		int lado = 2 * SEMILADO_BASE + 1;
		int bordes = 2 * DISTANCIA_BORDE;
		Posicion pos;
		ancho = bordes + cantidadBases * lado + DISTANCIA_ENTRE_BASES * (cantidadBases - 1);
		alto = bordes + lado;

		mapa = new Celda[ancho][alto];
		
		for (int x = 0; x < ancho; x++){
			for (int y = 0; y < alto; y++){
				pos = new Posicion(x, y);
				setCelda(pos, new Tierra());
			}
		}		
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
				setOcupante(new Mineral(randPos), randPos);
				yaUbicados.add(getCelda(randPos));
			}
			
			// La idea es que todos los nodos de mineral esten conectados.
			// Asi que pongo minerales en puntos aleatorios conectados a minerales ya
			// colocados.
			else if (getOcupante(randPos).getTipo() != TipoOcupante.MINERAL)
				for(Celda conMineral : yaUbicados)
					if (distancia(randPos, conMineral.getPosicion()) == 1){
						setOcupante(new Mineral(randPos), randPos);
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
		while(getOcupante(randPos).getTipo() == TipoOcupante.MINERAL);
		setOcupante(new GasVespeno(randPos), randPos);
	}
	
	/* Inyecta zonas espaciales al mapa.
	 */
	private void definirTerreno(){
		
		Posicion posAnterior, pos = null;
		Random miRNG = new Random();
		// Esto le pone algunas celdas espaciales al borde de arriba
		for (int y = 0; y < DISTANCIA_BORDE; y++)
			for (int x = 0; x < ancho; x++){
				posAnterior = new Posicion(x, y-1);
				pos = new Posicion(x, y);
				if (miRNG.nextBoolean() && (y == 0 || 
						getTerreno(posAnterior).getTipo() == TipoTerreno.ESPACIO))
					setCelda(pos, new Espacio());
			}		
		
		// y esto al de abajo
		for (int y = alto - 1; y > alto - DISTANCIA_BORDE; y--)
			for (int x = 0; x < ancho; x++){
				posAnterior = new Posicion(x, y+1);
				pos = new Posicion(x, y);
				if (miRNG.nextBoolean() && (((y == alto-1) || 
						getTerreno(posAnterior).getTipo() == TipoTerreno.ESPACIO)))
					setCelda(pos, new Espacio());
			}
		
		
	}
	
	/**********************************************/
	/**             SETTERS, GETTERS             **/
	/**********************************************/
	
	
	public int distancia(Posicion pos1, Posicion pos2) {
		return mapa[pos1.getX()][pos1.getY()].distancia(mapa[pos2.getX()][pos2.getY()]);
	}
	
	public Celda obtenerBaseDeJugador(int numeroJugador) {
		return basesJugadores.get(numeroJugador - 1);
	}
	
	/* Devuelve el ancho del mapa. */
	public int ancho() {
		return ancho;
	}
	
	/* Devuelve el alto del mapa. */
	public int alto() {
		return alto;
	}
	
	public ArrayList<Celda> getBases() {
		return new ArrayList<Celda>(bases);
	}
	
	private void setCelda(Posicion posicion, Terreno terreno){
		mapa[posicion.getX()][posicion.getY()] = new Celda(posicion, terreno);
	}
	
	private Celda getCelda(Posicion posicion){
		return mapa[posicion.getX()][posicion.getY()];
	}
	
	public void setOcupante(Ocupante ocupante, Posicion posicion) {
		this.getCelda(posicion).setOcupante(ocupante);
	}
	
	public Ocupante getOcupante(Posicion posicion) {
		return this.getCelda(posicion).getOcupante();
	}

	public Terreno getTerreno(Posicion posicion){
		return this.getCelda(posicion).getTerreno();
	}
	
	 public void mover(Unidad /*ObjetoVivo*/ unidad, Posicion destino){
		 if (getOcupante(destino).getTipo() != TipoOcupante.CELDA_VACIA)
			 throw new MovimientoInvalido();
		 unidad.moverA(destino);
	 }
	
	/**********************************************/
	/**                DEBUG AREA                **/
	/**********************************************/
	// A borrar cuando se borre el main
	final static int BASES_PARA_MAIN = 5;
	
	/* Imprime los puntos en formato "x, y: recurso". 
	 * Achicar constantes antes de usar. */
	private void imprimirRecursoPorCelda(){
		Celda celda;
		for (int y2 = 0; y2 < alto; y2++)
			for (int x2 = 0; x2 < ancho; x2++){
				celda = mapa[x2][y2];
			
				System.out.print(celda.getX());
				System.out.print(", ");
				System.out.print(celda.getY());
				System.out.print(": ");
				System.out.println(celda.getOcupante());
		}
	}

	/* Imprime en consola la cantidad de recursos por
	 * base de jugador. */
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
				if (distancia(pos, posJ1) < SEMILADO_BASE * 2) {
					if (getOcupante(pos) != null && getOcupante(pos).getTipo() == TipoOcupante.MINERAL)
						mineralesJ1++;
					else if (getOcupante(pos) != null && getOcupante(pos).getTipo() == TipoOcupante.VESPENO)
						vespenoJ1++;
				}
				else if (distancia(pos, posJ2) < SEMILADO_BASE * 2) {
					if (getOcupante(pos) != null && getOcupante(pos).getTipo() == TipoOcupante.MINERAL)
						mineralesJ2++;
					else if (getOcupante(pos) != null && getOcupante(pos).getTipo() == TipoOcupante.VESPENO)
						vespenoJ2++;
				}
			}
		}
		System.out.format("Minerales cerca del jugador 1: %d%n", mineralesJ1);		
		System.out.format("Geyseres cerca del jugador 1: %d%n", vespenoJ1);		
		System.out.format("Minerales cerca del jugador 2: %d%n", mineralesJ2);		
		System.out.format("Geyseres cerca del jugador 2: %d%n", vespenoJ2);	
	}	

	/* Imprime una version ASCII del mapa, con las sig. referencias:
	 * ' ' = Celda espacial
	 * '.' = Celda de tierra
	 * 'B' = Base neutral
	 * 'P' = Base de jugador
	 * 'M' = Cristal de minerales
	 * 'G' = Geyser de Gas Vespeno
	 * */
	private void imprimirMapaConReferencias() {
		Celda elPunto;
		Ocupante elRecurso;
		for (int y2 = 0; y2 < alto(); y2++){
			for (int x2 = 0; x2 < ancho(); x2++){
				elPunto = mapa[x2][y2];

				if (basesJugadores.contains(elPunto))
					System.out.print("P");
				else if (bases.contains(elPunto) )
					System.out.print("B");
				else {
					elRecurso = getOcupante(new Posicion(x2, y2));
					if(elRecurso != null && elRecurso.getTipo() == TipoOcupante.VESPENO)
						System.out.print("G");
					else if(elRecurso != null && elRecurso.getTipo() == TipoOcupante.MINERAL)
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

	/* Mostrar info del mapa de varias formas" */
	public static void main(String[] args){ //Renombrar a "main" para ejecutar.
		
		Mapa miMapa = new Mapa(BASES_PARA_MAIN);
		
		miMapa.imprimirMapaConReferencias();
		if (true)
			miMapa.imprimirMineralesCercaDeJugadores();
		if (args.length != 0) // false
			miMapa.imprimirRecursoPorCelda();
	}

	/* Para uso especifico de TestMapa */
	public boolean esBase(Posicion pos) {
		return bases.contains(getCelda(pos));
	}
		

}
