package fiuba.algo3interfaz.input;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.swing.ImageIcon;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.ocupantes.unidades.constructores.Constructor;
import fiuba.algo3interfaz.gfx.Botonera;
import fiuba.algo3interfaz.gfx.HudVista;

public class btnEntrenar extends BotonBotonera {

	private HudVista vista;
	private Botonera botoneraUnidadesEntrenables;
	private Jugador jugador;

	public btnEntrenar(HudVista vista, Jugador jugador, BufferedImage icon) {
		super(new ImageIcon(icon), "ENTRENAR!");
		this.vista = vista;
		this.jugador = jugador;
	}
	
	public void actionPerformed(ActionEvent e) {
		Edificio entrenador = (Edificio) jugador.getMapa().getOcupante(vista.getCeldaSeleccionada());
		if (! entrenador.puedeEntrenarUnidades()) {
			vista.mostrarMensaje("No es posible entrenar unidades. Edificio en construccion");
			return;
		}
		
		BotonBotonera[] botones = new BotonBotonera[entrenador.getUnidadesEntrenables().size()];
		int i = 0;
		for (Constructor constructor : entrenador.getUnidadesEntrenables()) {
			botones[i] = new btnConstructor(jugador, constructor);
			i++;
		}
		
		this.botoneraUnidadesEntrenables = new Botonera(botones);
		
		this.botoneraUnidadesEntrenables.agregarCancelable(new btnCancelarMouseListener(vista, HudVista.picCancelar));
		
		vista.setBotonera(botoneraUnidadesEntrenables);		
	}
}
