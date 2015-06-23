package fiuba.algo3interfaz.input;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3interfaz.gfx.HudVista;

public class btnMover extends BotonBotonera implements UtilizadorDeCeldas {

	private HudVista vista;
	private Unidad movible; //proximamente: IMovimiento movible
	
	public btnMover(HudVista vista, BufferedImage icon) {
		super(new ImageIcon(icon), "MOVER!!");
		this.vista = vista;
	}

	public void actionPerformed(ActionEvent e) {
		vista.setUtilizadorDeCeldas(this);
		// si este casteo falla es un problema de la asociacion de botonera a tipo!
		movible = (Unidad) vista.getOcupanteSelecccionado();
	}

	public void actualizar() {
		try {
			movible.moverA(vista.getCeldaSeleccionada());
		} finally {
			vista.restablecerOpciones();
			vista.requestFocus();
		}	
	}
	
}
