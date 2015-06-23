package fiuba.algo3interfaz.input;

// DEPRECATED

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

	private int x = -1, y = -1;
	private boolean clicked = false;
	
	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public void tick() {

	}
		
	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}

	public void mouseClicked(MouseEvent e) {
		Point point = e.getPoint();

		x = point.x;
		y = point.y;
		
		setClicked(true);
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
		
	}


}
