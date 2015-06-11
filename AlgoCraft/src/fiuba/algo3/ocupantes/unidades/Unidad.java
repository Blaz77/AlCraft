package fiuba.algo3.ocupantes.unidades;

import java.util.ArrayList;
import java.util.HashSet;

import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.mapa.MapaProxy;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;
import fiuba.algo3.excepciones.MovimientoInvalido;

public abstract class Unidad extends ObjetoVivo {
	
	int movRestantes;
	//O hacer accionesRestantes y que p/ej 1 ataque cueste 2
	//									 y 1 movim. cueste 1
	
	
	public Unidad(Jugador propietario, Posicion posicion, AtributosUnidad atributos){
		super(propietario, posicion, atributos);
		//Hacer esto de abajo en los CONSTRUCTORES! NO ACA!:
		propietario.agregarUnidad(this);
		this.movRestantes = ((AtributosUnidad)this.atributos).getMovPorTurno();
	}
	
	public void pasarTurno(){
		super.pasarTurno();
		//Temp esto aca abajo, quizas puede estar mejor hecho
		// Un estado quizas ?
		this.movRestantes = ((AtributosUnidad)this.atributos).getMovPorTurno();
	}

	
	public TipoOcupante getTipo(){
		return TipoOcupante.UNIDAD;
	}

	public int getRangoVision() {
		return ((AtributosUnidad) atributos).getRangoVision();
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

	
	public boolean puedeAtacar(){
		return ((AtributosUnidad)this.atributos).puedeAtacar();
	}
	
	public boolean puedeHacerMagia(){
		return ((AtributosUnidad)this.atributos).puedeHacerMagia();
	}
	
	public boolean puedeSerAlmacenada(){ //puedeSerTransportada
		return ((AtributosUnidad)this.atributos).puedeSerAlmacenada();
	}
		
	public boolean puedeAlmacenar(){ //puedeTransportar()
		return ((AtributosUnidad)this.atributos).puedeAlmacenar();
	}

}
