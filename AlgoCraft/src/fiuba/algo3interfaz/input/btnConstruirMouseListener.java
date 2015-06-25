package fiuba.algo3interfaz.input;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3interfaz.gfx.Botonera;
import fiuba.algo3interfaz.gfx.HudVista;

public class btnConstruirMouseListener extends BotonBotonera {

	private HudVista vista;
	private Botonera botoneraConstrucciones;

	public btnConstruirMouseListener(HudVista vista, Jugador jugador, BufferedImage icon) {
		super(new ImageIcon(icon), "CONSTRUIR!");
		this.vista = vista;
		this.botoneraConstrucciones = new Botonera(
				new btnConstruirEdificio(vista, jugador, jugador.getAtributos().getRecolectorMineral()),
				new btnConstruirEdificio(vista, jugador, jugador.getAtributos().getRecolectorGasVespeno()),
				new btnConstruirEdificio(vista, jugador, jugador.getAtributos().getIncrementadorPoblacion()),
				new btnConstruirEdificio(vista, jugador, jugador.getAtributos().getEntrenadorUnidadesBasicas()),
				new btnConstruirEdificio(vista, jugador, jugador.getAtributos().getEntrenadorUnidadesIntermedias()),
				new btnConstruirEdificio(vista, jugador, jugador.getAtributos().getEntrenadorUnidadesAvanzadas()));
		
		this.botoneraConstrucciones.agregarCancelable(new btnCancelarMouseListener(vista, vista.getPicCancelar()));
	}
	
	public void actionPerformed(ActionEvent e) {
		vista.setBotonera(botoneraConstrucciones);		
	}

}
