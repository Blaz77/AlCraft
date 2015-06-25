package fiuba.algo3.juego;

import java.util.ArrayList;

import fiuba.algo3.excepciones.NombreInvalido;
import fiuba.algo3.raza.TipoRaza;

public class Opciones {
	
	private final static Color COLOR_DEFAULT_1 = Color.ROJO;
	private final static Color COLOR_DEFAULT_2 = Color.AZUL;

	private final static TipoRaza RAZA_DEFAULT_1 = TipoRaza.TERRAN;
	private final static TipoRaza RAZA_DEFAULT_2 = TipoRaza.TERRAN;
	
	private final static String NOMBRE_DEFAULT_1 = COLOR_DEFAULT_1.getNombrePorDefecto(RAZA_DEFAULT_1); // "Luke Soro";
	private final static String NOMBRE_DEFAULT_2 =  COLOR_DEFAULT_2.getNombrePorDefecto(RAZA_DEFAULT_2); // "Paul Murck";
	
	private ArrayList<TipoRaza> razasJugadores = new ArrayList<TipoRaza>();
	private ArrayList<String> nombresJugadores = new ArrayList<String>();
	private ArrayList<Color> coloresJugadores = new ArrayList<Color>();
	
	private int cantidadBases = 6;
	// otras opciones
	
	public Opciones() {
		
		razasJugadores.add(RAZA_DEFAULT_1);
		razasJugadores.add(RAZA_DEFAULT_2);
		
		coloresJugadores.add(COLOR_DEFAULT_1);
		coloresJugadores.add(COLOR_DEFAULT_1);
		
		nombresJugadores.add(NOMBRE_DEFAULT_1);
		nombresJugadores.add(NOMBRE_DEFAULT_2);

	}

	public String getNombreJugador(int n) {
		return nombresJugadores.get(n - 1);
	}
	
	public Color getColorJugador(int n) {
		return coloresJugadores.get(n - 1);
	}
	
	public TipoRaza getRazaJugador(int n) {
		return razasJugadores.get(n - 1);
	}
	
	public int getCantidadBases() {
		return cantidadBases;
	}
	
	public void setDatosJugador(int n, String nombre, Color color, TipoRaza tipoRaza) {
		if (nombre.length() < 4)
			throw new NombreInvalido();
		nombresJugadores.set(n-1, nombre);
		coloresJugadores.set(n-1, color);
		razasJugadores.set(n-1, tipoRaza);
	}

	public void setCantidadBases(int cantidadBases) {
		this.cantidadBases = cantidadBases;
	}

	public int getCantidadJugadores() {
		return 2; // posiblemente despues permitir mas jugadores? no nos lo piden igual
	}
	
}
