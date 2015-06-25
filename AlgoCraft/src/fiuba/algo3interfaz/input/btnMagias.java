package fiuba.algo3interfaz.input;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import fiuba.algo3.componentes.IMagia;
import fiuba.algo3interfaz.gfx.Botonera;
import fiuba.algo3interfaz.gfx.HudVista;

public class btnMagias extends BotonBotonera {

	private HudVista vista;
	private Botonera botoneraMagias;

	public btnMagias(HudVista vista, BufferedImage icon) {
		super(new ImageIcon(icon), "MAGIAS!");
		this.vista = vista;
	}
	
	public void actionPerformed(ActionEvent e) {
		// si este casteo falla es un problema de asociacion de tipo con botoneras!
		IMagia magico = (IMagia) vista.getOcupanteSelecccionado();
		
		this.botoneraMagias = new Botonera(
					new btnMagiaAUnidad(vista, magico.getMagiaAUnidad()),
					new btnMagiaAOE(vista, magico.getMagiaDeAreaDeEfecto()));
		
		this.botoneraMagias.agregarCancelable(new btnCancelarMouseListener(vista, vista.getPicCancelar()));
		
		vista.setBotonera(botoneraMagias);		
	}

}
