package fiuba.algo3.mapa;

import java.util.ArrayList;

import fiuba.algo3.excepciones.CeldaNoVisible;
import fiuba.algo3.ocupantes.Ocupante;
import fiuba.algo3.ocupantes.OcupanteDesconocido;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3.terreno.Terreno;

public class MapaProxy implements Mapa {

	private Mapa mapa;
	//private ArrayList<Unidad> unidadesJugador;
	private Visibilidad[][] mapaVisibilidad;
	private Posicion posicionInicial;
	
/**********************************************/
/**              INICIALIZACION              **/
/**********************************************/
	
	/* Genera un mapa aleatorio para 2 jugadores, con la cantidad 
	 * de bases especificada
	 */
	public MapaProxy(Mapa mapa, Posicion origen){
		int ancho, alto;
		
		this.posicionInicial = origen;
		this.mapa = mapa;
				
		//unidades.add(new Unidad(null, new Posicion(12,12), new AtributosMarine()));
		//unidades.add(new Unidad(null, new Posicion(20,12), new AtributosMarine()));
		//unidades.add(new Unidad(null, new Posicion(12,20), new AtributosMarine()));
		//unidades.add(new Unidad(null, new Posicion(20,20), new AtributosMarine()));
		//System.out.format("%s%n", mapa.getOcupante(new Posicion(12, 12)));
		
		ancho = mapa.ancho();
		alto = mapa.alto();
		
		this.mapaVisibilidad = new Visibilidad[ancho][alto];
		for (int x = 0; x < ancho; x++)
			for (int y = 0; y < alto; y++)
				mapaVisibilidad[x][y] = new Visibilidad();
		
		setPuntoOrigen(origen);
	
	}
	
	private void setPuntoOrigen(Posicion posicionInicial) {
		// Proximamente esto en el constructor
		final int semilado = MapaReal.SEMILADO_BASE; //Arbitrario
		
		this._visibilizar_area(new Posicion(posicionInicial.getX() - semilado, posicionInicial.getY() - semilado), 
							new Posicion(posicionInicial.getX() + semilado + 1, posicionInicial.getY() + semilado + 1));
	}
	
	private void iluminar(Unidad unidad){
		_cambiarIluminacion(unidad, true);
	}
	
	private void oscurecer(Unidad unidad){
		_cambiarIluminacion(unidad, false);
	}	

	private void _cambiarIluminacion(Unidad unidad, boolean quieroIluminar){
		int vision = unidad.getRangoVision();
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
	
	/* Visibiliza un cuadrado de mapa */
	private void _visibilizar_area(Posicion posInicial, Posicion posFinal) {
		for (int y=posInicial.getY(); y < posFinal.getY(); y++) {
			for (int x=posInicial.getX(); x < posFinal.getX(); x++) {
				mapaVisibilidad[x][y].forzarIluminacion();
			}
		}
	}
	
	/**********************************************/
	/**             SETTERS, GETTERS             **/
	/**********************************************/
	
	public Posicion getPosicionInicial() {
		return this.posicionInicial;
	}
	
	public boolean celdaValida(Posicion posicion) {
		return mapa.celdaValida(posicion);
	}
	
	public boolean puedeOcupar(Ocupante ocupante, Posicion posicion) {
		// Esto tiene mas sentido asumiendo que toda unidad puede ver mas o igual a lo q puede moverse.
		return getVisibilidad(posicion).verOcupante() && mapa.puedeOcupar(ocupante, posicion);
	}
	
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
		if (! (getVisibilidad(posicion)).verOcupante())
			throw new CeldaNoVisible();
		
		mapa.setOcupante(ocupante, posicion);
	}
	
	public Posicion setOcupanteEnCercania(Ocupante ocupante, Posicion posicion){
		return mapa.setOcupanteEnCercania(ocupante, posicion);
	}
	
	public Ocupante getOcupante(Posicion posicion) {
		if (! (getVisibilidad(posicion)).verOcupante())
			return OcupanteDesconocido.getInstance();
		
		return mapa.getOcupante(posicion);
	}

	public Terreno getTerreno(Posicion posicion){
		if (! (getVisibilidad(posicion)).verTerreno())
			return Terreno.SOMBRA;
			
		return mapa.getTerreno(posicion);
	}
	
	public Ocupante removerOcupante(Posicion posicion){
		return mapa.removerOcupante(posicion);
	}
	
	public Ocupante reemplazar(Posicion posicion, Ocupante reemplazante){
		return mapa.reemplazar(posicion, reemplazante);
	}

	public void mover(Unidad /*ObjetoVivo*/ unidad, Posicion destino){
		oscurecer(unidad);
		mapa.mover(unidad, destino);
		iluminar(unidad);
	}
	 
	public void verificarOcupacion(Ocupante ocupante, Posicion posicion) {
		this.mapa.verificarOcupacion(ocupante, posicion);		
	}
	public ArrayList<Unidad> getUnidadesEnRango(Posicion centro, int distancia) {
		// TODO Auto-generated method stub
		return null;
	}
	
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
	
	/*public static void main(String args[]){ // Cambiar a "main" para ejecutar y probar
		
		MapaReal mapa = new MapaReal(3);
		Jugador jugador = new Jugador("Carlitos", Color.AZUL, TipoRaza.TERRAN, mapa);
		Unidad unidad1 = new Unidad(jugador, new Posicion(4,0), new AtributosMarine());
		Unidad unidad2 = new Unidad(jugador, new Posicion(0,0), new AtributosMarine());
		ArrayList<Unidad> unidades = new ArrayList<Unidad>();
		unidades.add(unidad1);
		MapaProxy mapaProxy = new MapaProxy(mapa, unidades);
		
		unidades.add(unidad2);
		mapaProxy = new MapaProxy(mapa, unidades);
			
		unidades.remove(unidad1);
		mapaProxy.oscurecer(unidad1);

		
		
		mapaProxy.imprimirMapaConReferencias();
	}*/

}
