package fiuba.algo3.ocupantes.unidades;

import java.util.ArrayList;
import java.util.HashSet;

import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.IAtaque;
import fiuba.algo3.componentes.IMagia;
import fiuba.algo3.componentes.ITransporte;
import fiuba.algo3.componentes.VoluntadDelSer;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.magia.MagiaAUnidad;
import fiuba.algo3.magia.MagiaDeAreaDeEfecto;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.mapa.MapaProxy;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;
import fiuba.algo3.excepciones.MovimientoInvalido;

public class Unidad extends ObjetoVivo {
	
	int movRestantes;
	private VoluntadDelSer voluntad;
	private IAtaque ataque;
	private IMagia magia;
	private ITransporte transporte;
	//O hacer accionesRestantes y que p/ej 1 ataque cueste 2
	//									 y 1 movim. cueste 1
	
	
	public Unidad(Jugador propietario, Posicion posicion, AtributosUnidad atributos){
		super(propietario, posicion, atributos);
		this.voluntad = atributos.getVoluntadDelSer();
		//Hacer esto de abajo en los CONSTRUCTORES! NO ACA!:
		propietario.agregarUnidad(this);
		this.movRestantes = voluntad.getMovPorTurno();
		this.ataque = atributos.getAtaque();
		this.ataque.setPortador(this);
		this.magia = atributos.getMagia();
		//this.magia.setPortador(this);
		this.transporte = atributos.getTransporte();
		//this.transporte.setPortador(this);
	}
	
	public void pasarTurno(){
		super.pasarTurno();
		//Temp esto aca abajo, quizas puede estar mejor hecho
		// Un estado quizas ?
		this.movRestantes = voluntad.getMovPorTurno();
	}

	
	public TipoOcupante getTipo(){
		return TipoOcupante.UNIDAD;
	}

	public int getRangoVision() {
		return voluntad.getRangoVision();
	}
	
	public int getCostoAlmacenamiento(){
		return ((AtributosUnidad) atributos).getCostoAlmacenamiento(); 
	}
	/*
	 * Lista temporal:
	 * 
	 * Atacar / Magia
	 * Moverse
	 * Descargar Unidades alojadas
	 * Meterse dentro de transporte
	 * */	
	
	/*
	 * 	MOVIMIENTO:
	 * 
	 * */	

	//Pensar:
	// - Habria que diferenciar los que no se pueden mover nunca
	// 	 de los que terminaron sus turnos?
	public boolean puedeMoverse(){
		return (this.movRestantes != 0);
	}
	
	public boolean puedeMoverseA(Posicion destino){
		return this.posicion.estaEnRango(destino, movRestantes);
		//chequear Terreno!!!!!
	}
	
	public void moverA(Posicion destino){
		if (!this.puedeMoverseA(destino)) throw new MovimientoInvalido();
		this.movRestantes -= this.posicion.distancia(destino);
		this.posicion = destino;
	}
	
	private int getMovPorTurno() {
		return 6; // TODO: DESPUES AGREGO ESTO
	}

	//TODO: chequear q funcione!
	public HashSet<Posicion> getPosiblesMovimientos(MapaProxy mapa){
		HashSet<Posicion> posiblesMov = new HashSet<Posicion>();
		int restantes = getMovPorTurno(); // TODO: DESPUES AGREGO ESTO
		_getPosiblesMovimientos(mapa, posiblesMov, this.posicion, restantes);
		return posiblesMov;
		
	}
	
	// TODO: chequear q funcione! Basicamente hago un BFS y agrego todas las unidades a las q llego
	public void _getPosiblesMovimientos(MapaProxy mapa, HashSet<Posicion> posiblesMov, Posicion posicionActual, int restantes){
		if (restantes == 0 || posiblesMov.contains(posicionActual)) return;
		restantes --;
		posiblesMov.add(posicionActual);
		ArrayList<Posicion> adyacentes = posicionActual.getAdyacentes();
		for (Posicion adyacente: adyacentes)
			if (mapa.celdaValida(adyacente) && mapa.puedeOcupar(this, adyacente))
				_getPosiblesMovimientos(mapa, posiblesMov, adyacente, restantes);
	}

/*
 * 	ATAQUE:
 * 
 * */	
	
	//Pensar:
	// - Habria que diferenciar los que no pueden atacar nunca
	// 	 de los que terminaron sus turnos?
	public boolean puedeAtacar(){
		return ataque.puedeAtacar();
	}
		
	public boolean puedeAtacarA(ObjetoVivo enemigo){
		return ataque.puedeAtacarA(enemigo);
	}
	
	public void atacarA(ObjetoVivo enemigo){
		ataque.atacarA(enemigo);
	}
		
	//esto es una idea nomas, no necesariamente hacerlo
	public ArrayList<ObjetoVivo> getPosiblesObjetivos(){
		return ataque.getPosiblesObjetivos();
	}
	
	/*
	 * 	MAGIA:
	 * 
	 * */	
	
	public boolean puedeHacerMagia() {
		return this.magia.puedeHacerMagia();
	}

	public int getEnergia() {
		return this.magia.getEnergia();
	}
	
	public int getEnergiaMaxima(){
		return this.magia.getEnergiaMaxima();
	}
	
	public int getEnergiaARegenerarPorTurno(){
		return this.magia.getEnergiaARegenerarPorTurno();
	}
	
	public boolean puedeDisminuirEnergia(int cantidad){
		return this.magia.puedeDisminuirEnergia(cantidad);
	}
	
	public void disminuirEnergia(int cantidad){
		this.magia.disminuirEnergia(cantidad);
	}
	
	public void regenerarEnergia(int cantRegenerada){
		this.magia.regenerarEnergia(cantRegenerada);
	}
	
	//Equivalente al rango de ataque pero para magia
	public int getRangoMagia(){
		return this.magia.getRangoMagia();
	}
	
	public boolean estaEnRangoDeMagia(Posicion otraPosicion){
		return this.magia.estaEnRangoDeMagia(this, otraPosicion);
	}
	
	public MagiaDeAreaDeEfecto getMagiaDeAreaDeEfecto(){
		return this.magia.getMagiaDeAreaDeEfecto(this);
	}
	
	public MagiaAUnidad getMagiaAUnidad(){
		return this.magia.getMagiaAUnidad(this);
	}
	
	/*
	 * TRANSPORTE:
	 * 
	 * */
	
	public boolean puedeSerAlmacenada(){ //puedeSerTransportada
		return ((AtributosUnidad)this.atributos).puedeSerAlmacenada();
	}
		
	public boolean puedeAlmacenar(){ //puedeTransportar()
		return this.transporte.puedeAlmacenar();
	}
	
	public boolean puedeAlmacenarA(Unidad unidad){
		return this.transporte.puedeAlmacenarA(this, unidad);
	}
	
	public void almacenarA(Unidad unidad){
		this.transporte.almacenarA(this, unidad);
	}
	
	public ArrayList<Pasajero> getUnidadesAlmacenadas(){
		return this.transporte.getUnidadesAlmacenadas();
	}
	
	public void liberarUnidad(Unidad unidadAlmacenada){
		this.transporte.liberarUnidad(unidadAlmacenada);
	}

}
