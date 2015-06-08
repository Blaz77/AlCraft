package fiuba.algo3.edificios;

import java.util.Iterator;
import java.util.LinkedList;

import fiuba.algo3.atributos.AtributosObjetoVivo;
import fiuba.algo3.componentes.Estado;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.excepciones.VidaEnCeroException;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.juego.Ocupante;
import fiuba.algo3.mapa.Posicion;

public abstract class ObjetoVivo extends Ocupante { //ObjetoVivo / ObjetoInteractuable / etc.

	protected Jugador propietario;
	protected IVida vida;
	protected LinkedList<Estado> estados;
	protected AtributosObjetoVivo atributos;

	public ObjetoVivo(Jugador propietario, Posicion posicion, AtributosObjetoVivo atributos) {
		this.propietario = propietario;
		this.posicion = posicion;
		this.atributos = atributos;
		this.vida = new Vida(atributos);
		this.estados = atributos.getEstadosIniciales();
		for (Estado estado : estados) {
			estado.activar(this);
		}
	}

	public Jugador getPropietario() {
		return this.propietario;
	}

	public Posicion getPosicion() {
		return this.posicion;  //quizas devolver copia!
	}

	public int getVida() {
		return this.vida.getVida();
	}

	public void regenerarVida(int puntos) {
		this.vida.regenerar(puntos);
	}

	public String getNombre() {
		return this.atributos.getNombre();
	}

	public boolean tieneEscudo(){
		return vida.tieneEscudo();
	}
	
	public int getEscudo(){
		return vida.getEscudo();
	}
	
	public void setVida(IVida vida) {
		this.vida = vida;		
	}
	
	public int getTurnosConstruccion() {
		return this.atributos.getTurnosConstruccion();
	}

	public int getVidaMaxima() {
		return this.atributos.getVidaMaxima();
	}
	
	public boolean esEnemigoDe(ObjetoVivo otro){
		return this.propietario != otro.propietario;
	}
	
	public void puedoRecibirDanioDe(ObjetoVivo objetoVivo){
		//return this.posicion.estaEnRango(objetoVivo.getPosicion(), 
			//							this.atributo.getRangoEfectivo(
				//								int RangoAire, int RangoTierra));
		// rangoAire y rangoTierra de la otra Unidad!
	}
	
	//Esto indica cual es el rango que debe usar mi enemigo para 
	//saber si me puede atacar.
	public int getRangoEfectivo(int rangoAire, int rangoTierra){
		return this.atributos.getRangoEfectivo(rangoAire, rangoTierra);
	}
	
	//Esto indica cual es el danio que debe usar mi enemigo para 
	//atacarme.
	public int getDanioEfectivo(int danioAire, int danioTierra){
		return this.atributos.getDanioEfectivo(danioAire,danioTierra);
	}

	public void recibirDanio(int puntos) {
		try {
			this.vida.recibirDanio(puntos);
		} 
		catch (VidaEnCeroException e) {
			this.destruir();
		}
	}
	
	public void destruir(){
		//Codigo para limpiar la existencia de Unidad o Edificio:
		//- eliminar del mapa
		//- eliminar de la lista del jugador
		//- Limpiezas internas:
		//	--p/ej: devolver las unidades transportadas al mapa
		//  --p/ej: devolver recursos en EntrenadorUnidades de construcciones no terminadas.
	}

	public void agregarEstado(Estado estado){
		this.estados.addLast(estado);
		estado.activar(this);
	}
	
	public void pasarTurno(){
		// Despues sacar ESTO AAAAA
		//this.vida = (IVida)this.vida.pasarTurno();
		
		LinkedList<Estado> aDesactivar = new LinkedList<Estado>();
		Iterator<Estado> iter = estados.iterator();
		
		while (iter.hasNext()){
			Estado estado = iter.next();
			try {
				estado.pasarTurno();
			} catch (Exception e) {
				iter.remove();
				aDesactivar.addLast(estado);
			}
		}
		
		//Hago esto porque estado al desactivar quizas mete nuevos estados!
		for (Estado estado : aDesactivar) {
			estado.desactivar();
		}
	}
}