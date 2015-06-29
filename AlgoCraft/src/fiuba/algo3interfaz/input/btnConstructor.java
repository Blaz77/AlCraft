package fiuba.algo3interfaz.input;

import java.awt.event.ActionEvent;

import fiuba.algo3.excepciones.GasVespenoInsuficiente;
import fiuba.algo3.excepciones.MineralInsuficiente;
import fiuba.algo3.excepciones.SuministroInsuficiente;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.ocupantes.unidades.constructores.Constructor;
import fiuba.algo3interfaz.gfx.HudVista;

public class btnConstructor extends BotonBotonera {

	private Constructor constructor;
	private HudVista vista;
	
	public btnConstructor(HudVista vista, Jugador jugador, Constructor constructor){
		super(jugador, constructor.getTipo());
		this.constructor = constructor;
		this.vista = vista;
	}
	
	public void actionPerformed(ActionEvent event) {
		try {
			constructor.crear();
			vista.restablecerOpciones();
		}
		catch (SuministroInsuficiente|MineralInsuficiente|GasVespenoInsuficiente e) {
			vista.mostrarMensaje(e.getMessage());
		}
		finally {
			vista.requestFocus();
		}
	}

}
