package fiuba.algo3interfaz.display;

// DEPRECATED

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	
	private JFrame frame; // Ventana de windows?
	private Canvas canvas;
	
	// Necesita estos 3 el frame:
	private String title;
	private int width, height; // En pixels.
	
	public Display(String title, int width, int height){
		
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay(); // Creamos el JFrame aca
	}
	
	private void createDisplay() {
		
		// Creamos el objeto, obviamente... despues vienen las opciones
		frame = new JFrame(title);
		
		// Tamanio de la ventana, en pixeles x pixeles.
		frame.setSize(width, height);
		
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
		canvas.setPreferredSize(new Dimension(width, height));
		
		// Asi nos aseguramos q se quede siempre con este tamanio.
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false); // ni idea q hace
		
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