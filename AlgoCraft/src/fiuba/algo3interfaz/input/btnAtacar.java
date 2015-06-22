package fiuba.algo3interfaz.input;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import fiuba.algo3.mapa.Posicion;
import fiuba.algo3interfaz.gfx.HudVista;

public class btnAtacar extends BotonBotonera implements UtilizadorDeCeldas{

	private HudVista vista;

	public btnAtacar(HudVista vista, BufferedImage icon) {
		super(new ImageIcon(icon), "ATACAR!!");
		this.vista = vista;
	}

	public void actionPerformed(ActionEvent e) {
		vista.setUtilizadorDeCeldas(this);		
	}

	public void actualizar() {
		Posicion pos = vista.getCeldaSeleccionada();
		System.out.format("Pichum! ataque la pos: (%d, %d). ",pos.getX(),pos.getY());
		System.out.println("Devuelvo el control a la vista!");
		vista.restablecerOpciones();
	}

	

}
