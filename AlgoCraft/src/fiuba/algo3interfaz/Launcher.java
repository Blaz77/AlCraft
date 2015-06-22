package fiuba.algo3interfaz;

import javax.swing.UIManager;

/* De seguir con esta linea, esta vendria a ser la main class, supongo*/ 
public class Launcher {

	private static final int ANCHO_VENTANA = 640;
	private static final int ALTO_VENTANA = 480;
		
	public static void main(String[] args){		
		Game game = new Game(ANCHO_VENTANA, ALTO_VENTANA);
		game.start();
	}
}
