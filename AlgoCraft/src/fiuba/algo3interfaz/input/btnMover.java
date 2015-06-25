package fiuba.algo3interfaz.input;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import fiuba.algo3.componentes.IMovimiento;
import fiuba.algo3.excepciones.FueraDelRangoPermitido;
import fiuba.algo3.excepciones.MovimientoInvalido;
import fiuba.algo3interfaz.gfx.HudVista;

public class btnMover extends BotonBotonera implements UtilizadorDeCeldas {

	private HudVista vista;
	private IMovimiento movible; //proximamente: IMovimiento movible
	
	public btnMover(HudVista vista, BufferedImage icon) {
		super(new ImageIcon(icon), "MOVER!!");
		this.vista = vista;
	}

	public void actionPerformed(ActionEvent e) {
		vista.setUtilizadorDeCeldas(this);
		// si este casteo falla es un problema de la asociacion de botonera a tipo!
		movible = (IMovimiento) vista.getOcupanteSelecccionado();
		vista.mostrarPosiblesMovimientos(movible);
		if (! movible.puedeMoverse()) {
			vista.mostrarMensaje("No hay movimientos posibles");
		}
		vista.requestFocus();
	}

	public void actualizar() {
		try {
			movible.moverA(vista.getCeldaSeleccionada());
		}
		catch (MovimientoInvalido|FueraDelRangoPermitido e) {
			// Simplemente no hace nada. Es como cancelar el movimiento
		}
		finally {
			vista.restablecerOpciones();
			vista.requestFocus();
		}	
	}
	
}
