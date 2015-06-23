package fiuba.algo3interfaz.input;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import fiuba.algo3.componentes.IAtaque;
import fiuba.algo3.ocupantes.ObjetoVivo;
import fiuba.algo3interfaz.gfx.HudVista;

public class btnAtacar extends BotonBotonera implements UtilizadorDeCeldas{

	private HudVista vista;
	private IAtaque atacante; //proximamente: IAtaque atacante
	
	public btnAtacar(HudVista vista, BufferedImage icon) {
		super(new ImageIcon(icon), "ATACAR!!");
		this.vista = vista;
	}

	public void actionPerformed(ActionEvent e) {
		vista.setUtilizadorDeCeldas(this);
		// si este casteo falla es un problema de la asociacion de botonera a tipo!
		atacante = (IAtaque) vista.getOcupanteSelecccionado();
	}

	public void actualizar() {
		try {
			atacante.atacarA((ObjetoVivo)vista.getOcupanteSelecccionado());
		} catch (ClassCastException e) {
			vista.mostrarMensaje("No es un objeto atacable!");
		//catch mas cosas!
		} finally {
			vista.restablecerOpciones();
			vista.requestFocus();
		}	
	}
}
