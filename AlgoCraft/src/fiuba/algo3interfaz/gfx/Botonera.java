package fiuba.algo3interfaz.gfx;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fiuba.algo3interfaz.input.BotonBotonera;

public class Botonera extends JPanel {

	public Botonera(BotonBotonera... botones){
		setLayout(new InvisibleGridLayout(0, 3, 14, 8));
		setOpaque(false);
		setVisible(true);
		//otras opciones ...
		
		assert(getComponentCount() == 0);
		
		for (BotonBotonera boton : botones) {
			add(boton);
		}
		
		assert(getComponentCount() == botones.length);
	}
	
	public void agregarCancelable(BotonBotonera botonCancelar){
		/* Huecos para estilizar el layout */
		for (int i=getComponentCount(); i<8; i++) {
			JLabel label = new JLabel();
			label.setBorder(null);
			label.setVisible(true);
			add(label);
		}
		add(botonCancelar);
	}
	
}
