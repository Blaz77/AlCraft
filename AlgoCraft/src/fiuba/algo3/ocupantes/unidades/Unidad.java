package fiuba.algo3.ocupantes.unidades;

import java.util.ArrayList;
import java.util.HashSet;

import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.IAtaque;
import fiuba.algo3.componentes.IMagia;
import fiuba.algo3.componentes.IMovimiento;
import fiuba.algo3.componentes.ITransporte;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.magia.MagiaAUnidad;
import fiuba.algo3.magia.MagiaDeAreaDeEfecto;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.TipoOcupante;

public class Unidad extends ObjetoVivo {
	
	private IMovimiento movimiento;
	private IAtaque ataque;
	private IMagia magia;
	private ITransporte transporte;
	//O hacer accionesRestantes y que p/ej 1 ataque cueste 2
	//									 y 1 movim. cueste 1
	
	
	public Unidad(Jugador propietario, Posicion posicion, AtributosUnidad atributos){
		super(propietario, posicion, atributos);
		this.ataque = atributos.getAtaque();
		this.ataque.setPortador(this);
		this.magia = atributos.getMagia();
		this.magia.setPortador(this);
		this.transporte = atributos.getTransporte();
		//this.transporte.setPortador(this);
		this.movimiento = atributos.getMovimiento();
		this.movimiento.setPortador(this);
	}

	@Override
	public void destruir() {
		super.destruir();
		this.propietario.removerUnidad(this);
	}
	
	/*
	 * 	MOVIMIENTO:
	 * 
	 * */	

	//Pensar:
	// - Habria que diferenciar los que no se pueden mover nunca
	// 	 de los que terminaron sus turnos?
	public boolean puedeMoverse(){
		return this.movimiento.puedeMoverse();
	}
	
	public boolean puedeMoverseA(Posicion destino){
		return this.movimiento.puedeMoverseA(destino);
	}
	
	public void moverA(Posicion destino){
		this.movimiento.moverA(destino);
	}

	//TODO: chequear q funcione!
	public HashSet<Posicion> getPosiblesMovimientos(Mapa mapa){
		return this.movimiento.getPosiblesMovimientos(mapa);
	}

	public int getRangoVision() {
		return this.movimiento.getRangoVision();
	}
	
	public int getCostoAlmacenamiento(){
		return this.movimiento.getCostoAlmacenamiento(); 
	}

	public boolean puedeSerAlmacenada(){ //puedeSerTransportada
		return this.movimiento.puedeSerAlmacenada();
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
