package fiuba.algo3interfaz.input;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import fiuba.algo3.excepciones.GasVespenoInsuficiente;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.RecursoAusente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3interfaz.gfx.CopyOfSpriteSheet;
import fiuba.algo3interfaz.gfx.HudVista;

public class btnConstruirEdificioIncrementadorPoblacionMouseListener extends BotonBotonera {

	private HudVista vista;
	private Jugador jugador;

	public btnConstruirEdificioIncrementadorPoblacionMouseListener(HudVista vista, Jugador jugador) {
		super(jugador.getAtributos().getIncrementadorPoblacion().getTipo());
		this.vista = vista;
		this.jugador = jugador;
	}
	
	public void actionPerformed(ActionEvent event) {
		try {
			jugador.getEdificador().crearIncrementadorPoblacion(jugador, vista.getCeldaSeleccionada());
			vista.restablecerOpciones();
		}
		catch (TerrenoInadecuado e) {
			vista.mostrarMensaje("Debe construir sobre tierra");
		}
		catch (MineralInsuficiente e) {
			vista.mostrarMensaje("Mineral insuficiente");
		}
		catch (GasVespenoInsuficiente e) {
			vista.mostrarMensaje("Gas vespeno insuficiente");
		}
		finally {
			vista.requestFocus();
		}		
	}

}
