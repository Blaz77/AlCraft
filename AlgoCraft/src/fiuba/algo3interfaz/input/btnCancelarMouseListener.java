package fiuba.algo3interfaz.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fiuba.algo3interfaz.gfx.HudVista;

public class btnCancelarMouseListener implements MouseListener {

	private HudVista vista;

	public btnCancelarMouseListener(HudVista vista) {
		this.vista = vista;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		vista.restablecerOpciones();
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
