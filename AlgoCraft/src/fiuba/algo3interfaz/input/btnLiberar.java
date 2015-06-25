package fiuba.algo3interfaz.input;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import fiuba.algo3.componentes.ITransporte;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.ocupantes.unidades.Pasajero;
import fiuba.algo3interfaz.gfx.Botonera;
import fiuba.algo3interfaz.gfx.HudVista;

public class btnLiberar extends BotonBotonera {

	private HudVista vista;
	private Botonera botoneraPasajeros;
	private Jugador jugador;

	public btnLiberar(HudVista vista, Jugador jugador, BufferedImage icon) {
		super(new ImageIcon(icon), "Liberar Pasajeros!");
		this.vista = vista;
		this.jugador = jugador;
	}
	
	public void actionPerformed(ActionEvent e) {
		// si este casteo falla es un problema de asociacion de tipo con botoneras!
		ITransporte transporte = (ITransporte) vista.getOcupanteSelecccionado();
		
		BotonBotonera[] botones = new BotonBotonera[transporte.getUnidadesAlmacenadas().size()];
		int i = 0;
		for (Pasajero pasajero : transporte.getUnidadesAlmacenadas()) {
			botones[i] = new btnPasajero(this, jugador, pasajero);
			i++;
		}
		
		this.botoneraPasajeros = new Botonera(botones);
		
		this.botoneraPasajeros.agregarCancelable(new btnCancelarMouseListener(vista, vista.getPicCancelar()));
		
		vista.setBotonera(botoneraPasajeros);		
	}

}
