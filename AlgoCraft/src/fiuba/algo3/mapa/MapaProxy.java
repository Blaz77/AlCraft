package fiuba.algo3.mapa;

//import fiuba.algo3.excepciones.CeldaNoVisible;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.juego.Ocupante;
import fiuba.algo3.terreno.Terreno;

public class MapaProxy {

	Mapa mapa;
	Jugador jugador;
	Visibilidad[][] mapaVisibilidad;
	
/**********************************************/
/**              INICIALIZACION              **/
/**********************************************/
	
	/* Genera un mapa aleatorio para 2 jugadores, con la cantidad 
	 * de bases especificada
	 */
	public MapaProxy(Mapa mapa){
		int ancho, alto;
		
		this.mapa = mapa;
		
		ancho = mapa.ancho();
		alto = mapa.alto();
		
		this.mapaVisibilidad = new Visibilidad[ancho][alto];
		for (int x = 0; x < ancho; x++)
			for (int y = 0; y < alto; y++)
				mapaVisibilidad[x][y] = Visibilidad.DESCONOCIDO;
	
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

}