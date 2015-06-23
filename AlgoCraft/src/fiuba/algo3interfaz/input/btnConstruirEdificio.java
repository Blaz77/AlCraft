package fiuba.algo3interfaz.input;

import java.awt.event.ActionEvent;

import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.excepciones.GasVespenoInsuficiente;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.OrdenConstruccionViolado;
import fiuba.algo3.excepciones.RecursoAusente;
import fiuba.algo3.excepciones.RecursoPresente;
import fiuba.algo3.excepciones.TerrenoInadecuado;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3interfaz.gfx.HudVista;

public class btnConstruirEdificio extends BotonBotonera {

	private AtributosEdificio atributos;
	private HudVista vista;
	private Jugador jugador;

	public btnConstruirEdificio(HudVista vista, Jugador jugador, AtributosEdificio atributos) {
		super(atributos.getTipo());
		this.atributos = atributos;
		this.vista = vista;
		this.jugador = jugador;
	}

	public void actionPerformed(ActionEvent event) {
		try {
			jugador.getEdificador().crearEdificio(jugador, vista.getCeldaSeleccionada(), atributos);
			vista.restablecerOpciones();
		}
		catch (RecursoAusente e) {
			if (atributos.debeOcuparMineral())
				vista.mostrarMensaje("Debe construir sobre un cristal");
			else if (atributos.debeOcuparGasVespeno())
				vista.mostrarMensaje("Debe construir sobre un volcan");
			else
				vista.mostrarMensaje("Su licencia ha caducado. Intente crackear nuevamente");
		}
		catch (TerrenoInadecuado|RecursoPresente|OrdenConstruccionViolado|MineralInsuficiente|GasVespenoInsuficiente e) {
			vista.mostrarMensaje(e.getMessage());
		}
		finally {
			vista.requestFocus();
		}	
	}
}
