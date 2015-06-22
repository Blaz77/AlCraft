package fiuba.algo3interfaz.input;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import fiuba.algo3interfaz.gfx.HudVista;

public class btnCancelarMouseListener extends BotonBotonera {

	private HudVista vista;

	public btnCancelarMouseListener(HudVista vista, BufferedImage icon) {
		super(new ImageIcon(icon), "CANCELAR!");
		this.vista = vista;
	}

	public void actionPerformed(ActionEvent e) {
		vista.restablecerOpciones();		
	}

}
