package fiuba.algo3.juego;

import java.util.Scanner;

import fiuba.algo3.raza.TipoRaza;

public class InterfazJuego {

	private EstadoDeJuego estadoActual;
	private Opciones opciones = new Opciones();
	private Scanner scanner = new Scanner(System.in);
	
	private	void pantallaInicio(){
		System.out.println("TITLE SCREEN HERE");
		scanner.nextLine();
		System.out.println("TERMINA EL TITLE SCREEN");
		
		this.menuPrincipal();
	}
	
	private void menuPrincipal() {
		this.menuOpciones();		
	}


	private void menuOpciones() {
		String nombrePJ;
		TipoRaza razaPJ;
		Color colorPJ;
		for (int n = 1; n <= 2; n++){
			
			nombrePJ = obtenerNombreJugador(n);
			razaPJ = obtenerRazaJugador(n);
			colorPJ = obtenerColorJugador(n);
			
			opciones.setDatosJugador(n, nombrePJ, colorPJ, razaPJ);
		}
		comenzarJuego();
	}

	private void comenzarJuego() {
		System.out.println("Y aqui es donde pondria un juego... SI TUVIERA UNO!!");
		scanner.nextLine();
		
	}

	private Color obtenerColorJugador(int n) {
		return (Color) _obtenerXJugador(n, Color.values(), "Colores", "el color");
	}

	private TipoRaza obtenerRazaJugador(int n) {
		return (TipoRaza) _obtenerXJugador(n, TipoRaza.values(), "Razas", "la raza");
	}

	private Object _obtenerXJugador(int n, Object[] tipos, String str1, String str2) {
		String strRazaPJ;
		int i = 0;
		System.out.format("%s: %n", str1);
		for (Object tipo: tipos){
			i++;
			System.out.format("%d. %s%n", i, tipo);
		}
		while (true){
			System.out.format("Escribe %s del Jugador %d", str2, n);
			strRazaPJ = scanner.nextLine();
			if (!isDigit(strRazaPJ)) continue;
			i = Integer.parseInt(strRazaPJ) -1;
			if (i < tipos.length && i >= 0)
				return tipos[i];
		}
	}

	private String obtenerNombreJugador(int n) {
		String nombrePJ = "";
		while (nombrePJ.length() < 4){
			System.out.format("Escoge el nombre del Jugador %d (no menos de 4 caracteres)", n);
			nombrePJ = scanner.nextLine();
		}
		return nombrePJ;
	}


	private void iniciar() {
		pantallaInicio();		
	}	
	
	private boolean isDigit(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
	

	public static void main(String[] args) {
		InterfazJuego interfazJuego = new InterfazJuego();
		interfazJuego.iniciar();
	}


}
