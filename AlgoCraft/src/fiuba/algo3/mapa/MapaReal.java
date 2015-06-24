package fiuba.algo3.mapa;

import java.util.ArrayList;
import java.util.Random;

import fiuba.algo3.ocupantes.Ocupante;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.TipoOcupante;
import fiuba.algo3.ocupantes.recurso.GasVespeno;
import fiuba.algo3.ocupantes.recurso.Mineral;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.terreno.Terreno;

public class MapaReal implements Mapa {
	public static final int DISTANCIA_BORDE = 10;
	public static final int DISTANCIA_ENTRE_BASES = 5;
	
	// La base es un territorio cuadrado, del q marcamos su centro
	public static final int SEMILADO_BASE = 8; // El lado de una base sera entonces 2 * semilado + 1 (el centro)
	
	public static final int MINERALES_POR_BASE = 6;
		
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
	public MapaReal(int cantidadBases){
		
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
	
		// sea b las bases, d la distancia entre ellas y r el radio a tomar.
		// Imaginese una circunferencia y tomamos b puntos equidistantes de ella, partiendo del angulo 0.
		// Como la distancia entre estos puntos no debe ser inferior a d, debe cumplirse (2*pi*r)/b > d.
		// entonces, r > (b*d)/(2*pi). Uso los bordes y ese 1.25 para asegurarme q eso ocurra.
		
		// Un ejemplo de mapa con 5 bases entonces seria:
		
		// ...............................
		// ...............................
		// ...............................
		// .................B.............
		// ...............................
		// .....B.........................
		// ...............................
		// ...............................
		// .........................B.....
		// ...............................
		// ...............................
		// .....B.........................
		// ...............................
		// .................B.............
		// ...............................
		// ...............................
		
		int radioMapa = (int) ( (DISTANCIA_BORDE + SEMILADO_BASE*2 + 1.25 * cantidadBases * (SEMILADO_BASE * 2 + DISTANCIA_ENTRE_BASES)) / Math.PI);
		ancho = radioMapa * 2;
		alto = radioMapa * 2;
		mapa = new Celda[ancho][alto];

		for (int x = 0; x < ancho; x++){
			for (int y = 0; y < alto; y++){
				mapa[x][y] = new Celda(new Posicion(x,y), Terreno.TIERRA);
			}
		}
	}

	private void ubicarBases(int cantidadBases) {
		
		int radio = (int) (cantidadBases * (SEMILADO_BASE * 2 + DISTANCIA_ENTRE_BASES) / Math.PI);
		for (int i = 0; i < cantidadBases; i++){
			
			double angulo = 360 * i / cantidadBases;
			int base_x = (int)((ancho/2) + radio * Math.cos(Math.toRadians(angulo)) );
			int base_y = (int)((alto/2) + radio * Math.sin(Math.toRadians(angulo)) );
			bases.add(mapa[base_x][base_y]);
		}
		
		basesJugadores.add(bases.get(0));
		basesJugadores.add(bases.get(bases.size()/2));
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
			else if (getOcupante(randPos).getTipo() != Tipo.MINERAL)
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
		while(getOcupante(randPos).getTipo() == Tipo.MINERAL);
		setOcupante(new GasVespeno(randPos), randPos);
	}
	
	/* Inyecta zonas espaciales al mapa.
	 */
	private void definirTerreno(){
		
		Posicion posAnterior = new Posicion(0,0);
		Posicion pos = null;
		Random miRNG = new Random();
		// Esto le pone algunas celdas espaciales al mapa.
		for (int i = 0; i < (ancho/10); i++){
				esparcirTerreno(miRNG, i*10 , 0, true);
				esparcirTerreno(miRNG, 0 , i*10, true);
				esparcirTerreno(miRNG, i*10 , alto-1, true);
				esparcirTerreno(miRNG, ancho-1, i*10, true);
		}
		esparcirTerreno(miRNG, ancho/2, alto/2, true);
				/*
				if ( 
						(! celdaEnBase(pos)) && 
						miRNG.nextInt(10) == 0) 
					mapa[x][y].setTerreno(Terreno.ESPACIO);
				posAnterior = pos;
			}				
		*/
	}
	

	
	private void esparcirTerreno(Random miRNG, int x, int y, boolean firstTime){
		Posicion pos = new Posicion(x,y);
		if (celdaValida(pos)  &&  mapa[x][y].getTerreno() == Terreno.TIERRA &&
		( firstTime && miRNG.nextBoolean() ||
		(miRNG.nextInt(100) < 3*distanciaABases(pos)))) {
			mapa[x][y].setTerreno(Terreno.ESPACIO);
			esparcirTerreno(miRNG, x+1, y, false);
			esparcirTerreno(miRNG, x-1, y, false);
			esparcirTerreno(miRNG, x, y+1, false);
			esparcirTerreno(miRNG, x, y-1, false);
		}
	}
	
	
	private boolean celdaEnBase(Posicion pos) {
		// TODO Auto-generated method stub
		for (Celda base: bases){
			if (Math.abs(base.getX() - pos.getX()) <= SEMILADO_BASE && 
					Math.abs(base.getY() - pos.getY()) <= SEMILADO_BASE ) 
				return true;
		}
		return false;
	}

	private int distanciaABases(Posicion pos){
		int minDistancia = ancho;
		int dist;
		for (Celda base: bases){
			dist = Math.max( 	(Math.abs(base.getX() - pos.getX()) - SEMILADO_BASE),
								(Math.abs(base.getY() - pos.getY()) - SEMILADO_BASE) );
			minDistancia = Math.min(minDistancia, dist);
		}
	
		return minDistancia;
		
	}
	

	/**********************************************/
	/**             SETTERS, GETTERS             **/
	/**********************************************/
	
	public Posicion getPosicionInicial() {
		return new Posicion(0, 0);
	}
	
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
		
	private Celda getCelda(Posicion posicion){
		return mapa[posicion.getX()][posicion.getY()];
	}
	
	// Aca prohibo celdas negativas, no en posicion, para no perder la chance
	// de "sumar" para moverme en direcciones negativas 
	// Private?
	public boolean celdaValida(Posicion posicion) {

		return	(posicion.getX() >= 0) &&
				(posicion.getY() >= 0) &&
				(posicion.getX() < ancho) &&
				(posicion.getY() < alto);
	}
	
	//una opcion para que devuelva booleano
	public boolean puedeOcupar(Ocupante ocupante, Posicion posicion){
		return this.getCelda(posicion).puedeSerOcupada(ocupante);
	}
	
	//otra opcion para que lanze excepciones en caso de error, sino nada
	public void verificarOcupacion(Ocupante ocupante, Posicion posicion){
		this.getCelda(posicion).verificarOcupacion(ocupante);
	}
	
	public void setOcupante(Ocupante ocupante, Posicion posicion) {
		this.getCelda(posicion).setOcupante(ocupante);
	}
	
	private boolean puedeOcupar(Ocupante ocupante, int x, int y){
		try {
			return mapa[x][y].puedeSerOcupada(ocupante);
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public Posicion setOcupanteEnCercania(Ocupante ocupante, Posicion posicion){
		int x = posicion.getX();
		int y = posicion.getY();
		
		for(int offset = 0; true; offset++){
			
			//recorre los cuadrados
			//pasa dos veces por cada esquina por ahora (mala leche)
			
			for (int diff = -offset; diff <= offset; diff++){
				//linea vertical a la derecha
				if (puedeOcupar(ocupante, x+offset, y+diff)){
					mapa[x+offset][y+diff].setOcupante(ocupante);
					return new Posicion(x+offset, y+diff);
				}
				//linea vertical a la izquierda
				if (puedeOcupar(ocupante, x-offset, y+diff)){
					mapa[x-offset][y+diff].setOcupante(ocupante);
					return new Posicion(x-offset, y+diff);
				}
				//linea horizontal arriba
				if (puedeOcupar(ocupante, x+diff, y+offset)){
					mapa[x+diff][y+offset].setOcupante(ocupante);
					return new Posicion(x+diff, y+offset);
				}
				//linea horizontal abajo
				if (puedeOcupar(ocupante, x+diff, y-offset)){
					mapa[x+diff][y-offset].setOcupante(ocupante);
					return new Posicion(x+diff, y-offset);
				}
			}
			
		}
	}
	
	public Ocupante getOcupante(Posicion posicion) {
		return this.getCelda(posicion).getOcupante();
	}
	
	// pone por defecto celda vacia
	public Ocupante removerOcupante(Posicion posicion){
		return this.getCelda(posicion).removerOcupante();
	}
	
	// NO HACE CHEQUEOS DEL REEMPLAZANTE: (pensado para construcciones)
	// ver bien depues alguna mejor solucion
	public Ocupante reemplazar(Posicion posicion, Ocupante reemplazante){
		return this.getCelda(posicion).reemplazar(reemplazante);
	}
	
	public void mover(Unidad /*ObjetoVivo*/ unidad, Posicion destino){
		setOcupante(removerOcupante(unidad.getPosicion()), destino);
	}

	public Terreno getTerreno(Posicion posicion){

		return this.getCelda(posicion).getTerreno();
	}
		 
	// Tal vez es mucho para llamarlo getter, reubicar.
	 
	/* Recorre el mapa alrededor del centro especificado y devuelve las unidades 
	 * que se encuentran presentes alrededor dentro del circulo cuyo radio es la 
	 * distancia dada.
	 */
	public ArrayList<Unidad> getUnidadesEnRango(Posicion centro, int distancia) {
		if (!celdaValida(centro)) throw new RuntimeException(); // _Revisar_
		
		ArrayList<Unidad> unidades = new ArrayList<Unidad>();
		
		for (int y = centro.getY() - distancia; y < centro.getY() + distancia; y++) {
			for (int x = centro.getX() - distancia; x < centro.getX() + distancia; x++) {
				Posicion posActual = new Posicion(x, y);
				// Dentro del mapa y el circulo
				if (celdaValida(posActual) && distancia(centro, posActual) <= distancia) {
					if (mapa[x][y].getOcupante().getTipoOcupante() == TipoOcupante.UNIDAD) {
						unidades.add((Unidad) mapa[x][y].getOcupante());
					}
				}
			}
		}
		
		return unidades;
	}
	
	/**********************************************/
	/**                DEBUG AREA                **/
	/**********************************************/
	// A borrar cuando se borre el main
	final static int BASES_PARA_MAIN = 3;
	
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
					if (getOcupante(pos) != null && getOcupante(pos).getTipo() == Tipo.MINERAL)
						mineralesJ1++;
					else if (getOcupante(pos) != null && getOcupante(pos).getTipo() == Tipo.VESPENO)
						vespenoJ1++;
				}
				else if (distancia(pos, posJ2) < SEMILADO_BASE * 2) {
					if (getOcupante(pos) != null && getOcupante(pos).getTipo() == Tipo.MINERAL)
						mineralesJ2++;
					else if (getOcupante(pos) != null && getOcupante(pos).getTipo() == Tipo.VESPENO)
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
					if(elRecurso != null && elRecurso.getTipo() == Tipo.VESPENO)
						System.out.print(".");
					else if(elRecurso != null && elRecurso.getTipo() == Tipo.MINERAL)
							System.out.print(".");
					else if(getTerreno(new Posicion(x2, y2)) == Terreno.ESPACIO)
						System.out.print(" ");
					else
						System.out.print(".");
				}
			}
			System.out.println("");
		}
	}

	/* Mostrar info del mapa de varias formas" */
	/*public static void main(String[] args){ //Renombrar a "main" para ejecutar.
		
		MapaReal miMapa;
		for (int bases = 2; bases <= 8; bases++)
			for (int dist_base = 2; dist_base < 65; dist_base++)
				for (int semilado = 4; semilado < 10; semilado++)
					for (int borde = 2; borde < 20; borde++){
						DISTANCIA_BORDE = borde;
						SEMILADO_BASE = semilado;
						DISTANCIA_ENTRE_BASES = dist_base;
						//System.out.format("Bases: %d, DistBases: %d, Semilado: %d, Borde: %d %n", bases, dist_base, semilado, borde);
						miMapa = new MapaReal(bases);
		}
		
		MapaReal miMapa = new MapaReal(BASES_PARA_MAIN);
		
		miMapa.imprimirMapaConReferencias();
		if (true)
			miMapa.imprimirMineralesCercaDeJugadores();
		if (args.length != 0) // false
			miMapa.imprimirRecursoPorCelda();
	}*/

	/* Para uso especifico de TestMapa */
	public boolean esBase(Posicion pos) {
		return bases.contains(getCelda(pos));
	}
		

	
	
}
