package fiuba.algo3interfaz.input;

import java.awt.event.ActionEvent;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.ocupantes.unidades.Pasajero;
import fiuba.algo3.ocupantes.unidades.constructores.Constructor;

public class btnPasajero extends BotonBotonera {

	private Pasajero pasajero;
	private BotonBotonera btnOrigen;
	
	public btnPasajero(BotonBotonera btnOrigen, Jugador jugador, Pasajero pasajero){
		super(jugador, pasajero.getTipo());
		this.pasajero = pasajero;
		this.btnOrigen = btnOrigen;
	}
	
	public void actionPerformed(ActionEvent e) {
		pasajero.salir();
		btnOrigen.actionPerformed(null); //vuelva a armar una botonera actualizada!
	}

}
