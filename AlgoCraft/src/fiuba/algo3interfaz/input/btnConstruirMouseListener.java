package fiuba.algo3interfaz.input;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import fiuba.algo3interfaz.gfx.HudVista;

public class btnConstruirMouseListener extends BotonBotonera {

	private HudVista vista;

	public btnConstruirMouseListener(HudVista vista, BufferedImage icon) {
		super(new ImageIcon(icon), "CONSTRUIR!");
		this.vista = vista;
	}
	
	public void actionPerformed(ActionEvent e) {
		vista.mostrarOpcionesConstruccion();		
	}

}
