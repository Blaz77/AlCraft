package fiuba.algo3interfaz.input;

import java.awt.event.ActionEvent;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.ocupantes.unidades.constructores.Constructor;
import fiuba.algo3interfaz.gfx.HudVista;

public class btnConstructor extends BotonBotonera {

	private Constructor constructor;
	
	public btnConstructor(Jugador jugador, Constructor constructor){
		super(jugador, constructor.getTipo());
		this.constructor = constructor;
	}
	
	public void actionPerformed(ActionEvent e) {
		constructor.crear();
	}

}
