package fiuba.algo3interfaz.input;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BotonBotonera extends JButton{// implements ActionListener{
	
	public BotonBotonera(ImageIcon icon, MouseListener ml){
		setIcon(icon);
		addMouseListener(ml);
		//addActionListener(this);
		setVisible(true);
		setBorder(null);
		setFocusPainted(false);
		setContentAreaFilled(false);
		//mas opciones...
	}

}
