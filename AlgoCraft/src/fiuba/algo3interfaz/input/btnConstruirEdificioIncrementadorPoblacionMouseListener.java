package fiuba.algo3interfaz.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fiuba.algo3.excepciones.GasVespenoInsuficiente;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.RecursoAusente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3interfaz.gfx.HudVista;

public class btnConstruirEdificioIncrementadorPoblacionMouseListener implements MouseListener {

	private HudVista vista;
	private Jugador jugador;

	public btnConstruirEdificioIncrementadorPoblacionMouseListener(HudVista vista, Jugador jugador) {
		this.vista = vista;
		this.jugador = jugador;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		//vista.mostrarOpcionesColocacion();
		try {
			jugador.getEdificador().crearIncrementadorPoblacion(jugador, vista.getCeldaSeleccionada());
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
		vista.restablecerOpciones();
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
