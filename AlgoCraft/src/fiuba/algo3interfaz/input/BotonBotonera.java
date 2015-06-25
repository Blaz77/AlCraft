package fiuba.algo3interfaz.input;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3interfaz.gfx.SpriteSheet;

public abstract class BotonBotonera extends JButton implements ActionListener{// implements ActionListener{
	
	public BotonBotonera(ImageIcon icon, String toolTipText){
		setIcon(icon);
		setToolTipText(toolTipText);
		addActionListener(this);
		setVisible(true);
		setBorder(null);
		setFocusPainted(false);
		setContentAreaFilled(false);
		//mas opciones...
	}
	
	public BotonBotonera(Jugador jugador, Tipo tipo){
		this(new ImageIcon(SpriteSheet.getSpritesJugador(jugador).get(tipo.ordinal()))
			, tipo.getNombre());
	}

}
