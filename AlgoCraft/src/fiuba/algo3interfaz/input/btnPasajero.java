package fiuba.algo3interfaz.input;

import java.awt.event.ActionEvent;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.ocupantes.unidades.Pasajero;
import fiuba.algo3.ocupantes.unidades.constructores.Constructor;

public class btnPasajero extends BotonBotonera {

	private Pasajero pasajero;
	
	public btnPasajero(Jugador jugador, Pasajero pasajero){
		super(jugador, pasajero.getTipo());
		this.pasajero = pasajero;
	}
	
	public void actionPerformed(ActionEvent e) {
		pasajero.salir();
		//va a quedar el pasajero mostrado aunque haya salido
		//restablecer la vista ? 
	}

}
