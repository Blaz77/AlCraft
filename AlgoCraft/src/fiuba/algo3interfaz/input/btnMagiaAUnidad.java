package fiuba.algo3interfaz.input;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import fiuba.algo3.magia.MagiaAUnidad;
import fiuba.algo3.magia.MagiaDeAreaDeEfecto;
import fiuba.algo3.ocupantes.unidades.Unidad;
import fiuba.algo3interfaz.gfx.HudVista;
import fiuba.algo3interfaz.gfx.ImageLoader;
import fiuba.algo3interfaz.gfx.SpriteSheet;


public class btnMagiaAUnidad extends BotonBotonera implements UtilizadorDeCeldas{

	public static final int INDICE_SPRITE = 7;
	private HudVista vista;
	private MagiaAUnidad magia;
	
	
	public btnMagiaAUnidad(HudVista vista, MagiaAUnidad magia) {
		//hay que ver como agarrar la informacion de la magia: (otro tipo?)
		super(new ImageIcon(SpriteSheet.getSpritesAcciones(vista.getRaza()).get(INDICE_SPRITE)), "MagiaAUnidad");
		this.magia = magia;
		this.vista = vista;
	}
	
	public void actionPerformed(ActionEvent e) {
		vista.setUtilizadorDeCeldas(this);
	}

	public void actualizar() {
		try {
			magia.ejecutar((Unidad)vista.getOcupanteSelecccionado());
		} catch (ClassCastException e) {
			vista.mostrarMensaje("No es aplicable para esta magia!");
		//catch mas cosas!
		} finally {
			vista.restablecerOpciones();
			vista.requestFocus();
		}	
	}

}
