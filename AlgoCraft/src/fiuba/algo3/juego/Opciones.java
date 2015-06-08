package fiuba.algo3.juego;

import java.util.ArrayList;

import fiuba.algo3.raza.TipoRaza;

public class Opciones {
	
	private final static String NOMBRE_DEFAULT_1 = "Jugador1";
	private final static String NOMBRE_DEFAULT_2 = "Jugador2";
	
	private final static TipoRaza RAZA_DEFAULT_1 = TipoRaza.TERRAN;
			
	private ArrayList<TipoRaza> razasJugadores = new ArrayList<TipoRaza>();
	private ArrayList<String> nombresJugadores = new ArrayList<String>();
	
	private int cantidadJugadores = 2;
	private int cantidadBases = 2;
	// otras opciones
	
	
	public Opciones() {
		
		
		
	}

	public Raza getRazaJugador(int n) {
		return razasJugadores.get(n - 1);
	}

	public String getNombreJugador(int n) {
		return nombresJugadores.get(n);
	}

	public int getCantidadBases() {
		return cantidadBases;
	}
	
	public void setDatosJugador(int n, Raza razaJugador, String nombreJugador) throws Exception {
		razasJugadores.set(n, razaJugador);
		// TODO: crear una buena para este.
		if (nombreJugador.length() < 4)
			throw new Exception();
		nombresJugadores.set(n, nombreJugador);
	}
	

	public void setCantidadBases(int cantidadBases) {
		this.cantidadBases = cantidadBases;
	}
	
}
