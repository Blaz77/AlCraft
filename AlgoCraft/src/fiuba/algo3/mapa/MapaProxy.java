package fiuba.algo3.mapa;

import java.util.ArrayList;

import fiuba.algo3.juego.Color;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.juego.Ocupante;
import fiuba.algo3.raza.TipoRaza;
import fiuba.algo3.terreno.Terreno;
import fiuba.algo3.unidades.Unidad;

public class MapaProxy {

	Mapa mapa;
	ArrayList<Unidad> unidadesJugador;
	Visibilidad[][] mapaVisibilidad;
	
/**********************************************/
/**              INICIALIZACION              **/
/**********************************************/
	
	/* Genera un mapa aleatorio para 2 jugadores, con la cantidad 
	 * de bases especificada
	 */
	public MapaProxy(Mapa mapa, ArrayList<Unidad> unidades){
		int ancho, alto;
		
		this.mapa = mapa;
		this.unidadesJugador = unidades;
		ancho = mapa.ancho();
		alto = mapa.alto();
		
		this.mapaVisibilidad = new Visibilidad[ancho][alto];
		for (int x = 0; x < ancho; x++)
			for (int y = 0; y < alto; y++)
				mapaVisibilidad[x][y] = new Visibilidad();
		
		for (Unidad unidad: unidadesJugador)
			iluminar(unidad);
	
	}
	private void iluminar(Unidad unidad){
		_cambiarIluminacion(unidad, true);
	}
	
	private void oscurecer(Unidad unidad){
		_cambiarIluminacion(unidad, false);
	}	

	private void _cambiarIluminacion(Unidad unidad, boolean quieroIluminar){
		int vision = 4; // unidad.getVision();
		Posicion centro = unidad.getPosicion();
		int pos_x, pos_y;
		Posicion posActual;
		for (int x = -vision; x < vision + 1; x++)
			for (int y = -vision; y < vision + 1; y++){
				pos_x = centro.getX() + x;
				pos_y = centro.getY() + y;
				if (pos_x < 0 || pos_y < 0) 
					continue;
				posActual = new Posicion(pos_x, pos_y); 
				if (centro.distancia(posActual) > vision)
					continue;
				if (quieroIluminar)
					mapaVisibilidad[pos_x][pos_y].add(unidad);
				else
					mapaVisibilidad[pos_x][pos_y].remove(unidad);
			}
	}
	
	/**********************************************/
	/**             SETTERS, GETTERS             **/
	/**********************************************/
		
	public Visibilidad getVisibilidad(Posicion posicion){
		return mapaVisibilidad[posicion.getX()][posicion.getY()];
	}
	
	/* Devuelve el ancho del mapa. */
	public int ancho() {
		return mapa.ancho();
	}
	
	/* Devuelve el alto del mapa. */
	public int alto() {
		return mapa.alto();
	}
		
	public int distancia(Posicion pos1, Posicion pos2) {
		return mapa.distancia(pos1, pos2);
	}
	
	public void setOcupante(Ocupante ocupante, Posicion posicion) {
		//if (! (getVisibilidad(posicion)).verOcupante())
		//	throw new CeldaNoVisible();
		
		mapa.setOcupante(ocupante, posicion);
	}
	
	public Ocupante getOcupante(Posicion posicion) {
		//if (! (getVisibilidad(posicion)).verOcupante())
		//	throw new CeldaNoVisible();
		
		return mapa.getOcupante(posicion);
	}

	public Terreno getTerreno(Posicion posicion){
		//if (! (getVisibilidad(posicion)).verTerreno())
		//	throw new CeldaNoVisible();
			
		return mapa.getTerreno(posicion);
	}
	
	/*
	 * public posicionar(Edif/Unid, posicion):
	 * 	-	En el PROXY:
	 * 		//Des-Sombreado bruto:
			// - Desde el eje: posInicial todo el circulo/rombo que se forma 
			//		con el rangoVision
	 */
	
	/*
	 * public mover(Edif/Unid, destino):
	 * 	-	En el PROXY:
	 * 		//Des-Sombreado Inteligente:
			//	- Sabiendo la posicion anterior y la nueva posicion
			//		se puede des-sombrear solamente las posiciones necesarias!
			// 
	 */
	
	/**********************************************/
	/**                DEBUG AREA                **/
	/**********************************************/
	// A borrar cuando se borre el main
	final static int BASES_PARA_MAIN = 3;
	
	/* Imprime una version ASCII del mapa, con las sig. referencias:
	 * ' ' = Celda visible
	 * 'o' = Celda no visible, alguna vez vista.
	 * '#' = Celda nunca vista
	 * */
	private void imprimirMapaConReferencias() {
		Visibilidad visibilidad;
		for (int y = 0; y < alto(); y++){
			for (int x = 0; x < ancho(); x++){
				visibilidad = mapaVisibilidad[x][y];
				if (visibilidad.verOcupante())
					System.out.print(" ");
				else if (visibilidad.verTerreno())
					System.out.print("o");
				else
					System.out.print("#");
			}
			System.out.println("");
		}
	}
	
	public static void notMain(String args[]){ // Cambiar a "main" para ejecutar y probar
		
		Mapa mapa = new Mapa(3);
		Jugador jugador = new Jugador(TipoRaza.TERRAN, Color.AZUL, mapa);
		Unidad unidad1 = new Marine(jugador, new Posicion(4,0));
		Unidad unidad2 = new Marine(jugador, new Posicion(0,0));
		ArrayList<Unidad> unidades = new ArrayList<Unidad>();
		unidades.add(unidad1);
		MapaProxy mapaProxy = new MapaProxy(mapa, unidades);
		
		unidades.add(unidad2);
		mapaProxy = new MapaProxy(mapa, unidades);
			
		unidades.remove(unidad1);
		mapaProxy.oscurecer(unidad1);

		
		
		mapaProxy.imprimirMapaConReferencias();
	}

}