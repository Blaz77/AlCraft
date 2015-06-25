package fiuba.algo3interfaz.input;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import fiuba.algo3.componentes.IAtaque;
import fiuba.algo3.magia.MagiaDeAreaDeEfecto;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3interfaz.gfx.HudVista;
import fiuba.algo3interfaz.gfx.ImageLoader;
import fiuba.algo3interfaz.gfx.SpriteSheet;


public class btnMagiaAOE extends BotonBotonera implements UtilizadorDeCeldas{
	
	public static final int INDICE_SPRITE = 6;
	private HudVista vista;
	private MagiaDeAreaDeEfecto magia;
	
	
	public btnMagiaAOE(HudVista vista, MagiaDeAreaDeEfecto magia) {
		//hay que ver como agarrar la informacion de la magia: (otro tipo?)
		super(new ImageIcon(SpriteSheet.getSpritesAcciones(vista.getRaza()).get(INDICE_SPRITE)), "MagiaAOE");
		this.magia = magia;
		this.vista = vista;
	}
	
	public void actionPerformed(ActionEvent e) {
		vista.setUtilizadorDeCeldas(this);
	}

	public void actualizar() {
		try {
			magia.ejecutar(vista.getCeldaSeleccionada());
		//catch mas cosas!
		} finally {
			vista.restablecerOpciones();
			vista.requestFocus();
		}	
	}

}
