package fiuba.algo3interfaz.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	
	private JFrame frame; // Ventana de windows?
	private Canvas canvas; // Lienzo.. queda mal xD
	
	// Necesita estos 3 el frame:
	private String titulo;
	private int ancho, alto; // En pixels.
	
	public Display(String titulo, int ancho, int alto){
		
		this.titulo = titulo;
		this.ancho = ancho;
		this.alto = alto;
		
		crearDisplay(); // Creamos el JFrame aca
	}
	
	private void crearDisplay() {
		
		// Creamos el objeto, obviamente... despues vienen las opciones
		frame = new JFrame(titulo);
		
		// Tamanio de la ventana, en pixeles x pixeles.
		frame.setSize(ancho, alto);
		
		// Permite cerrar la ventana con la 'X' IMPORTANTISIMA!
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Bloqueamos al usuario el poder cambiar el tamanio
		frame.setResizable(false);
		
		// Esto hace que aparezca la ventana en el centro
		frame.setLocationRelativeTo(null);
		
		// Parece bobo pero si no pones esto no se ve nada, x default
		frame.setVisible(true);
		
		//frame.etc muchas cosas mas se pueden hacer!
		
		// Un canvas es donde dibujamos cosas, luego lo metemos al frame.
		// Similar a como un pintor hace lo mismo.
		canvas = new Canvas();
		
		// Canvas se labura con dimension, lo quieran o no.
		canvas.setPreferredSize(new Dimension(ancho, alto));
		
		// Asi nos aseguramos q se quede siempre con este tamanio.
		canvas.setMaximumSize(new Dimension(ancho, alto));
		canvas.setMinimumSize(new Dimension(ancho, alto));
		
		frame.add(canvas);
		
		// Asegura q el frame muestre todo el canvas
		frame.pack();
	}
	
	public Canvas getCanvas(){
		return canvas;
	}

	public JFrame getFrame(){
		return frame;
	}
	
}
